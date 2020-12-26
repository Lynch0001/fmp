ALTER TABLE cars
    ADD COLUMN bumper VARCHAR(5);

ALTER TABLE cars
    ADD COLUMN lastservice date;

ALTER TABLE cars
    ADD COLUMN division VARCHAR(25);

UPDATE cars
SET bumper = 'A001', lastservice = '2020-10-02', division = 'A'
WHERE id=1;

UPDATE cars
SET bumper = 'B001', lastservice = '2020-03-06', division = 'B'
WHERE id=2;

UPDATE cars
SET bumper = 'B002', lastservice = '2020-01-12', division = 'B'
WHERE id=3;

UPDATE cars
SET bumper = 'B003', lastservice = '2019-11-15', division = 'B'
WHERE id=4;

UPDATE cars
SET bumper = 'A002', lastservice = '2020-11-02', division = 'A'
WHERE id=5;

UPDATE cars
SET bumper = 'A003', lastservice = '2019-12-02', division = 'A'
WHERE id=6;

UPDATE cars
SET bumper = 'A004', lastservice = '2020-01-23', division = 'A'
WHERE id=7;

UPDATE cars
SET bumper = 'B004', lastservice = '2020-06-02', division = 'B'
WHERE id=8;

UPDATE cars
SET bumper = 'A005', lastservice = '2020-02-15', division = 'A'
WHERE id=9;

UPDATE cars
SET make = 'Ford', model = 'E350'
WHERE id=1;

UPDATE cars
SET make = 'Ford', model = 'E350'
WHERE id=2;
