-- V2__create_transactions_table.sql
CREATE TABLE transactions (
    id             BIGSERIAL PRIMARY KEY,
    account_id      BIGINT NOT NULL REFERENCES accounts(id),
    amount          NUMERIC(19, 4) NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    description     VARCHAR(255),
    created_at     TIMESTAMP NOT NULL DEFAULT NOW()
);
CREATE INDEX idx_transactions_account_id ON transactions(account_id);