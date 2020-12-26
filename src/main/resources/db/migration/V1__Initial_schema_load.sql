CREATE TABLE CARS
(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    MAKE VARCHAR(255),
    MODEL VARCHAR(255),
    MANUFACTUREYEAR INT
);

INSERT INTO CARS(ID, MAKE, MODEL, MANUFACTUREYEAR) VALUES(1, 'Chevrolet', 'Tahoe', 2013);
INSERT INTO CARS(ID, MAKE, MODEL, MANUFACTUREYEAR) VALUES(2, 'Volkswagen', 'Tiguan', 2014);
