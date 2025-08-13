CREATE TABLE IF NOT EXISTS contact_messages (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(120)  NOT NULL,
    email      VARCHAR(255)  NOT NULL,
    phone      VARCHAR(40),
    subject    VARCHAR(200)  NOT NULL,
    message    VARCHAR(5000) NOT NULL,
    created_at TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

CREATE INDEX IF NOT EXISTS idx_contact_messages_created_at
    ON contact_messages(created_at);
