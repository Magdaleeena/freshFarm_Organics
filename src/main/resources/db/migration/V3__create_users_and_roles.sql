CREATE TABLE users (
                       id            UUID PRIMARY KEY,
                       email         VARCHAR(255) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       enabled       BOOLEAN      NOT NULL DEFAULT TRUE,
                       created_at    TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE roles (
                       name VARCHAR(50) PRIMARY KEY
);

-- seed roles
INSERT INTO roles(name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

CREATE TABLE user_roles (
                            user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                            role_name VARCHAR(50) NOT NULL REFERENCES roles(name) ON DELETE CASCADE,
                            PRIMARY KEY (user_id, role_name)
);
