ALTER TABLE refresh_tokens
ADD COLUMN device_id UUID NOT NULL DEFAULT gen_random_uuid();

ALTER TABLE refresh_tokens
ADD CONSTRAINT unique_user_device UNIQUE (user_id, device_id);