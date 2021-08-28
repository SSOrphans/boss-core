CREATE SCHEMA IF NOT EXISTS boss;

CREATE TABLE IF NOT EXISTS boss.confirmation
(
    id          BIGINT UNSIGNED  NOT NULL UNIQUE AUTO_INCREMENT,
    type        TINYINT UNSIGNED NOT NULL,
    confirmable BIGINT UNSIGNED  NOT NULL,
    hash        CHAR(36)         NOT NULL UNIQUE,
    until       BIGINT           NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS boss.user
(
    -- Base holder information
    id                 BIGINT UNSIGNED   NOT NULL UNIQUE AUTO_INCREMENT,
    type               TINYINT UNSIGNED  NOT NULL,
    branch             BIGINT UNSIGNED   NOT NULL,
    flags              TINYINT UNSIGNED  NOT NULL,

    -- Embedded UserProfile
    username           NVARCHAR(16)      NOT NULL UNIQUE,
    email              NVARCHAR(128)     NOT NULL UNIQUE,
    password           VARCHAR(64)       NOT NULL,    -- HASHED
    created            BIGINT            NOT NULL,
    deleted            BIGINT            NULL,

    -- Embedded UserProfile -> UserSettings
    transaction_alerts INT UNSIGNED      NULL,
    balance_alerts     INT UNSIGNED      NULL,

    -- Embedded UserInfo
    full_name          NVARCHAR(255)     NULL,
    identification     CHAR(64)          NULL UNIQUE, -- National Identification code, HASHED.
    dob                DATE              NULL,

    -- Embedded UserInfo -> UserAddress
    -- May need to be separated into table if multiple can exist.
    country            CHAR(2)           NULL,        -- UK, US, CA, etc.
    postal             NVARCHAR(10)      NULL,        -- Postal/Zip code
    region             NVARCHAR(64)      NULL,        -- State, Province, Region
    city               NVARCHAR(64)      NULL,
    street             NVARCHAR(64)      NULL,
    premise            NVARCHAR(64)      NULL,        -- Apartment, Suite, Box number, etc.

    -- Embedded UserInfo -> UserCellular
    -- May need to be separated into table if multiple can exist.
    presentation       SMALLINT UNSIGNED NULL,
    number             CHAR(15)          NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS boss.account
(
    id              BIGINT UNSIGNED  NOT NULL,
    type            TINYINT UNSIGNED NOT NULL,
    branch          BIGINT UNSIGNED  NOT NULL, -- The branch the account belongs to.

    -- Embedded AccountInfo
    name            NVARCHAR(16)     NULL,
    balance         FLOAT UNSIGNED   NOT NULL,
    pending_balance FLOAT UNSIGNED   NOT NULL,
    opened          TIMESTAMP        NOT NULL,
    closed          TIMESTAMP        NULL,
    flags           TINYINT UNSIGNED NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS boss.transaction
(
    id          BIGINT UNSIGNED  NOT NULL UNIQUE AUTO_INCREMENT,
    type        TINYINT UNSIGNED NOT NULL,
    account     BIGINT UNSIGNED  NOT NULL,
    overdraft   BIGINT UNSIGNED  NULL, -- not currently implemented
    atm         BIGINT UNSIGNED  NULL, -- not currently implemented

    -- Embedded TransactionInfo
    merchant    NVARCHAR(32)     NOT NULL,
    amount      FLOAT            NOT NULL,
    new_balance FLOAT            NOT NULL,
    date        TIMESTAMP        NOT NULL,
    flags       TINYINT          NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_transaction_account
        FOREIGN KEY (account) REFERENCES boss.account (id)
--    CONSTRAINT fk_transaction_overdraft
--        FOREIGN KEY (overdraft) REFERENCES boss.overdraft (id),
--    CONSTRAINT fk_transaction_atm
--        FOREIGN KEY (atm) REFERENCES boss.atm (id)
);

CREATE TABLE IF NOT EXISTS boss.loan
(
    id       BIGINT UNSIGNED  NOT NULL UNIQUE AUTO_INCREMENT,
    type     TINYINT UNSIGNED NOT NULL,
    number   VARCHAR(64)      NOT NULL UNIQUE,
    user     BIGINT UNSIGNED  NOT NULL,
    account  BIGINT UNSIGNED  NOT NULL UNIQUE,

    -- Embedded LoanInfo
    total    FLOAT            NOT NULL,
    interest FLOAT            NOT NULL,
    taken_at TIMESTAMP        NOT NULL,
    due_by   DATE             NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_loan_user
        FOREIGN KEY (user) REFERENCES boss.user (id),
    CONSTRAINT fk_loan_account
        FOREIGN KEY (account) REFERENCES boss.account (id)
);

CREATE TABLE IF NOT EXISTS boss.user_accounts
(
    user    BIGINT UNSIGNED NOT NULL,
    account BIGINT UNSIGNED NOT NULL,

    PRIMARY KEY (user),
    PRIMARY KEY (account),
    CONSTRAINT fk_user_accounts_user
        FOREIGN KEY (user) REFERENCES boss.user (id),
    CONSTRAINT fk_user_accounts_account
        FOREIGN KEY (account) REFERENCES boss.account (id)
);

CREATE TABLE IF NOT EXISTS boss.rewards_program
(
    id          INT UNSIGNED     NOT NULL UNIQUE AUTO_INCREMENT,
    type        TINYINT UNSIGNED NOT NULL,
    name        VARCHAR(16)      NOT NULL,
    distributor VARCHAR(16)      NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS boss.card_level
(
    id          SMALLINT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    name        VARCHAR(16)       NOT NULL,
    distributor VARCHAR(16)       NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS boss.card
(
    -- Base cardEntity information.
    id      BIGINT UNSIGNED  NOT NULL UNIQUE AUTO_INCREMENT,
    type    TINYINT UNSIGNED NOT NULL,
    account BIGINT UNSIGNED  NULL,            -- Backing account ID.
    holder  BIGINT UNSIGNED  NULL,            -- Backing account holder ID.

    -- Embedded PhysicalCardInfo
    number  CHAR(19)         NOT NULL UNIQUE, -- UNHASHED
    cvv     CHAR(64)         NOT NULL,        -- HASHED
    created BIGINT UNSIGNED  NOT NULL,
    expiry  BIGINT UNSIGNED  NOT NULL,

    -- Embedded LogicalCardInfo
    pin     CHAR(64)         NULL,            -- HASHED
    since   BIGINT UNSIGNED  NULL,            -- Last active, since
    flags   TINYINT UNSIGNED NOT NULL,        -- Card flags: confirmed, active, stolen, etc.

    PRIMARY KEY (id),
    CONSTRAINT fk_card_account
        FOREIGN KEY (account) REFERENCES boss.account (id),
    CONSTRAINT fk_card_holder
        FOREIGN KEY (holder) REFERENCES boss.user (id)
);

CREATE TABLE IF NOT EXISTS boss.debit_card
(
    backing BIGINT UNSIGNED NOT NULL UNIQUE,
    -- No other information to store here currently.

    PRIMARY KEY (backing)
);

CREATE TABLE IF NOT EXISTS boss.card_rewards_programs
(
    card    BIGINT UNSIGNED NOT NULL,
    program BIGINT UNSIGNED NOT NULL,
    points  INT             NOT NULL,

    PRIMARY KEY (card),
    PRIMARY KEY (program),
    CONSTRAINT fk_card_rewards_programs_card
        FOREIGN KEY (card) REFERENCES boss.credit_card (backing),
    CONSTRAINT fk_card_rewards_programs_program
        FOREIGN KEY (program) REFERENCES boss.rewards_program (id)
);

CREATE TABLE IF NOT EXISTS boss.credit_card
(
    backing BIGINT UNSIGNED   NOT NULL UNIQUE, -- Card backing ID.
    level   SMALLINT UNSIGNED NOT NULL,        -- The type of credit cardEntity this is.
    apr     FLOAT             NOT NULL,        -- Annual percentage rate
    ceiling FLOAT             NOT NULL,        -- The maximum allowance on the cardEntity.

    -- APR / 12 gives monthly interest rate on outstanding balance.

    PRIMARY KEY (backing),
    CONSTRAINT fk_credit_card_backing
        FOREIGN KEY (backing) REFERENCES boss.card (id),
    CONSTRAINT fk_credit_card_level
        FOREIGN KEY (level) REFERENCES boss.card_level (id)
);
