CREATE TABLE drivers
(
    DRIVER_ID BIGSERIAL NOT NULL,
    NAME VARCHAR(30),
    PRIMARY KEY (DRIVER_ID)
);

CREATE TABLE techs
(
    TECH_ID BIGSERIAL NOT NULL,
    NAME VARCHAR(30),
    PRIMARY KEY (TECH_ID)
);

INSERT INTO drivers(driver_id, name)
VALUES
(1, 'James Smith'),
(2, 'Burt Jones'),
(3, 'Gerry Davies'),
(4, 'Mark Cranston'),
(5, 'Jerry Green');

INSERT INTO techs(tech_id, name)
VALUES
(1, 'Ben Benson'),
(2, 'Jim Green'),
(3, 'Jose Davies'),
(4, 'Mark Jefferson'),
(5, 'Jason Jones');
