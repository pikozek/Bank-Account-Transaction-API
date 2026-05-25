-- V1__create_accounts_table.sql
CREATE TABLE accounts (
    id          BIGSERIAL PRIMARY KEY,
    owner_name  VARCHAR(100) NOT NULL,
    account_number VARCHAR(20) NOT NULL UNIQUE,
    balance    NUMERIC(19, 4) NOT NULL DEFAULT 0.00,
    currency VARCHAR(3) NOT NULL DEFAULT 'KZT',
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_accounts_account_number ON accounts(account_number);