CREATE SCHEMA IF NOT EXISTS boss;
CREATE TABLE IF NOT EXISTS boss.loan
(
    id
                  INT
                              NOT
                                  NULL
        AUTO_INCREMENT
        UNIQUE,
    loan_number
                  VARCHAR(64) NOT NULL UNIQUE,
    type_id       TINYINT     NOT NULL,
    user_id       INT         NOT NULL,
    branch_id     INT         NOT NULL,
    amount        FLOAT       NOT NULL,
    interest_rate FLOAT       NOT NULL,
    taken_at      DATETIME    NOT NULL,
    due_by        DATE        NOT NULL,
    amount_due    FLOAT       NOT NULL,
    PRIMARY KEY
        (
         id
            )
);
CREATE TABLE IF NOT EXISTS boss.card
(
    id
                    INT
                             NOT
                                 NULL
        AUTO_INCREMENT
        UNIQUE,
    type_id
                    TINYINT
                             NOT
                                 NULL,
    number_hash
                    CHAR(64) NOT NULL,
    account_id      INT      NOT NULL,
    created         DATETIME NOT NULL,
    active_since    DATE     NOT NULL,
    expiration_date DATE     NOT NULL,
    pin             SMALLINT NOT NULL,
    cvv             TINYINT  NOT NULL,
    confirmed       BIT(1)   NOT NULL,
    active          BIT(1)   NOT NULL,
    stolen          BIT(1)   NOT NULL,
    PRIMARY KEY
        (
         id
            )
);
CREATE TABLE IF NOT EXISTS boss.user
(
    id
             INT
                          NOT
                              NULL
        AUTO_INCREMENT
        UNIQUE,
    type_id
             TINYINT
                          NOT
                              NULL,
    branch_id
             INT
                          NOT
                              NULL,
    username
             VARCHAR(16)  NOT NULL UNIQUE,
    email    VARCHAR(128) NOT NULL UNIQUE,
    password CHAR(64)     NOT NULL,
    created  BIGINT       NOT NULL,
    deleted  BIGINT,
    enabled  BIT          NOT NULL,
    locked   BIT          NOT NULL,
    PRIMARY KEY
        (
         id
            )
);

