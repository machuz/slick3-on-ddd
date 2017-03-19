create table users (
    id VARCHAR(50) NOT NULL,
    name varchar(255) NOT NULL
);
ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY(id);
CREATE UNIQUE INDEX IF NOT EXISTS users_idx ON users (name);
