CREATE TABLE DISPATCHES
(
    ID BIGSERIAL NOT NULL,
    out_time timestamp,
    in_time timestamp,
    dispatch_out boolean,
    dispatch_in boolean,
    out_mileage INT,
    in_mileage INT,
    driver VARCHAR(25),
    out_tech VARCHAR(25),
    in_tech VARCHAR(25),
    out_inspection boolean,
    in_inspection boolean,
    PRIMARY KEY (id)
);

CREATE TABLE SERVICES
(
    ID BIGSERIAL NOT NULL,
    out_time timestamp,
    in_time timestamp,
    service_out boolean,
    service_in boolean,
    out_mileage INT,
    in_mileage INT,
    out_tech VARCHAR(25),
    in_tech VARCHAR(25),
    type VARCHAR(25),
    PRIMARY KEY (id),
    CONSTRAINT fk_service
        FOREIGN KEY(id)
            REFERENCES cars(id)

);
