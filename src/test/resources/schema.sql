CREATE SCHEMA IF NOT EXISTS boss;

CREATE TABLE IF NOT EXISTS boss.loan
(
    id            INT         NOT NULL AUTO_INCREMENT UNIQUE,
    loan_number   VARCHAR(64) NOT NULL UNIQUE,
    type_id       TINYINT     NOT NULL,
    user_id       INT         NOT NULL,
    branch_id     INT         NOT NULL,
    amount        FLOAT       NOT NULL,
    interest_rate FLOAT       NOT NULL,
    taken_at      DATETIME    NOT NULL,
    due_by        DATE        NOT NULL,
    amount_due    FLOAT       NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS boss.card
(
    id              INT      UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
    type_id         TINYINT  UNSIGNED NOT NULL,
    number_hash     CHAR(64)          NOT NULL UNIQUE,
    last_four       CHAR(4)           NOT NULL,
    account_id      INT      UNSIGNED NOT NULL,
    created         BIGINT   UNSIGNED NOT NULL,
    active_since    BIGINT   UNSIGNED NOT NULL,
    expiration_date BIGINT   UNSIGNED NOT NULL,
    pin             CHAR(4)           NOT NULL,
    cvv             CHAR(3)           NOT NULL,
    confirmed       BIT               NOT NULL,
    active          BIT               NOT NULL,
    stolen          BIT               NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS boss.user
(
    id          INT          UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
    type_id     TINYINT      UNSIGNED NOT NULL,
    branch_id   INT          UNSIGNED NOT NULL,
    username    VARCHAR(16)           NOT NULL UNIQUE,
    email       VARCHAR(128)          NOT NULL UNIQUE,
    password    CHAR(64)              NOT NULL,
    created     BIGINT       UNSIGNED NOT NULL,
    deleted     BIGINT       UNSIGNED     NULL,
    enabled     BIT                   NOT NULL,
    locked      BIT                   NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS boss.confirmation
(
    id               INT      NOT NULL AUTO_INCREMENT UNIQUE,
    type_id          TINYINT  NOT NULL,
    confirmable_id   INT      NOT NULL,
    confirmable_hash CHAR(36) NOT NULL UNIQUE,
    good_until       BIGINT   NOT NULL
);

CREATE TABLE IF NOT EXISTS boss.account_holder
(
    user_id       INT         UNSIGNED NOT NULL UNIQUE,
    full_name	  VARCHAR(64)          NOT NULL,
    dob           DATE                 NOT NULL,
    ssn           CHAR(64)             NOT NULL UNIQUE,
    address       CHAR(255)            NOT NULL,
    city          CHAR(64)             NOT NULL,
    state         CHAR(32)             NOT NULL,
    zip           INT         UNSIGNED NOT NULL,
    phone	      CHAR(16)             NOT NULL,

    FOREIGN KEY (user_id) REFERENCES boss.user (id)
);
