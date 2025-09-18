CREATE TABLE refresh_tokens (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    token TEXT NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ip_address VARCHAR(45) NULL
);

-- Unique index for quick lookup by token
CREATE UNIQUE INDEX idx_refresh_token_token ON refresh_tokens(token);

-- Index for deleting all tokens by user_id efficiently
CREATE INDEX idx_refresh_token_user_id ON refresh_tokens(user_id);