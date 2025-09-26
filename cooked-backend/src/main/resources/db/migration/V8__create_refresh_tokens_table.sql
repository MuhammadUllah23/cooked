CREATE TABLE refresh_tokens (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    device_id UUID NOT NULL DEFAULT gen_random_uuid(),
    token TEXT NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Unique index for quick lookup by token
CREATE UNIQUE INDEX idx_refresh_token_token ON refresh_tokens(token);

-- Index for deleting all tokens by user_id efficiently
CREATE INDEX idx_refresh_token_user_id ON refresh_tokens(user_id);

-- Unique constraint: one refresh token per user per device
ALTER TABLE refresh_tokens
ADD CONSTRAINT unique_user_device UNIQUE (user_id, device_id);