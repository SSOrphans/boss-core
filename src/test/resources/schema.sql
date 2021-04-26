create schema if not exists boss;
create table if not exists boss.user
(
    id        INT          NOT NULL AUTO_INCREMENT UNIQUE,
    type_id   TINYINT      NOT NULL,
    branch_id INT          NOT NULL,
    username  VARCHAR(16)  NOT NULL UNIQUE,
    email     VARCHAR(128) NOT NULL UNIQUE,
    password  CHAR(64)     NOT NULL,
    created   DATETIME     NOT NULL,
    deleted   DATETIME,
    enabled   BIT          NOT NULL,
    locked    BIT          NOT NULL,
    PRIMARY KEY (id)
);
create table if not exists boss.loan
(
    id            INT      NOT NULL AUTO_INCREMENT UNIQUE,
    type_id       TINYINT  NOT NULL,
    user_id       INT      NOT NULL,
    branch_id     INT      NOT NULL,
    amount        FLOAT    NOT NULL,
    interest_rate FLOAT    NOT NULL,
    taken_at      DATETIME NOT NULL,
    due_by        DATE     NOT NULL,
    amount_due    FLOAT    NOT NULL,
    PRIMARY KEY (id)
);
create table if not exists boss.card
(
    id              INT      NOT NULL AUTO_INCREMENT UNIQUE,
    type_id         TINYINT  NOT NULL,
    number_hash     char(64) NOT NULL,
    account_id      int      NOT NULL,
    created         DATETIME NOT NULL,
    active_since    DATE     NOT NULL,
    expiration_date DATE     NOT NULL,
    pin             smallint NOT NULL,
    cvv             tinyint  NOT NULL,
    confirmed       bit(1)   NOT NULL,
    active          bit(1)   NOT NULL,
    stolen          bit(1)   NOT NULL,
    PRIMARY KEY (id)
);
