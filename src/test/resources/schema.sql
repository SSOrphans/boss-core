create schema if not exists boss;
create table if not exists boss.loan
(
    id            INT      NOT NULL AUTO_INCREMENT UNIQUE,
    loan_number VARCHAR(64) NOT NULL UNIQUE,
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
create table if not exists boss.user
(
    id        INT          NOT NULL AUTO_INCREMENT UNIQUE,
    type_id   TINYINT      NOT NULL,
    branch_id INT          NOT NULL,
    username  VARCHAR(16)  NOT NULL UNIQUE,
    email     VARCHAR(128) NOT NULL UNIQUE,
    password  CHAR(64)     NOT NULL,
    created   BIGINT       NOT NULL,
    deleted   BIGINT,
    enabled   BIT          NOT NULL,
    locked    BIT          NOT NULL,
    PRIMARY KEY (id)
);
create table if not exists boss.confirmation
(
    id               INT      NOT NULL AUTO_INCREMENT UNIQUE,
    type_id          TINYINT  NOT NULL,
    confirmable_id   INT      NOT NULL,
    confirmable_hash CHAR(36) NOT NULL UNIQUE,
    good_until       BIGINT   NOT NULL
);
create table if not exists boss.account_holder
(
  user_id		INT UNSIGNED	NOT NULL,
  full_name		VARCHAR(64)		NOT NULL,
  dob			DATE			NOT NULL,
  ssn			CHAR(64)		NOT NULL,
  address		CHAR(255)		NOT NULL,
  city			CHAR(64) 		NOT NULL,
  state			CHAR(32)		NOT NULL,
  zip			INT				NOT NULL,
  phone			CHAR(16)		NOT NULL,

  PRIMARY KEY	(user_id),
  FOREIGN KEY	(user_id) REFERENCES boss.user (id)
);

