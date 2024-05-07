CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users
(
    id       UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    email    VARCHAR(255) NOT NULL ,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE otps
(
    otp_id     UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    otp_code   VARCHAR(10) NOT NULL,
    issued_at  TIMESTAMP   NOT NULL,
    expiration TIMESTAMP   NOT NULL,
    verify     BOOLEAN     NOT NULL,
    user_id    UUID        NOT NULL,
    CONSTRAINT users_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE
);
drop table otps;
