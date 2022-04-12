CREATE TABLE IF NOT EXISTS account
(
    account_number INTEGER PRIMARY KEY NOT NULL CHECK (account_number > 0),
    balance        DECIMAL(10, 2)      NOT NULL,
    institution    VARCHAR(10)         NOT NULL,
    account_name   VARCHAR(20)         NOT NULL,
    FOREIGN KEY (account_name) REFERENCES account_type (account_name)
);


CREATE TABLE IF NOT EXISTS account_type
(
    account_name VARCHAR(20) PRIMARY KEY NOT NULL,
    account_type TEXT                    NOT NULL
        CHECK (account_type in ('asset', 'liability', 'investment', 'income'))
);

CREATE TABLE IF NOT EXISTS txn_type
(
    transaction_category VARCHAR(20) PRIMARY KEY NOT NULL,
    transaction_type     VARCHAR(10)             NOT NULL
        CHECK (transaction_type in ('income', 'transfer', 'expense'))
);

CREATE TABLE IF NOT EXISTS txn
(
    transaction_number   INTEGER          not null primary key autoincrement,
    account_number       INTEGER          NOT NULL CHECK (account_number > 0),
    amount               DECIMAL(10, 2)   NOT NULL,
    created_at           TEXT             NOT NULL,
    transaction_category TEXT VARCHAR(10) NOT NULL,
    FOREIGN KEY (transaction_category) REFERENCES txn_type (transaction_category),
    FOREIGN KEY (account_number) REFERENCES account (account_number)
);

CREATE VIEW IF NOT EXISTS vw_accounts_with_type
AS
SELECT account.*, account_type.account_type as type
FROM account
         JOIN account_type
              ON account.account_name = account_type.account_name;

CREATE VIEW IF NOT EXISTS vw_transactions_with_type
AS
SELECT txn.*, txn_type.transaction_type as type
FROM txn
         JOIN txn_type
              ON txn.transaction_category = txn_type.transaction_category;