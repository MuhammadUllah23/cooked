DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'user_role') THEN
        CREATE TYPE user_role AS ENUM ('USER');
    END IF;
END
$$;

ALTER TABLE users
ADD COLUMN role user_role NOT NULL DEFAULT 'USER';