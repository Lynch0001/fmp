CREATE TABLE USERS
(
    USER_ID BIGSERIAL NOT NULL,
    USERNAME VARCHAR(255),
    PASSWORD VARCHAR(255),
    PRIMARY KEY (USER_ID)
);