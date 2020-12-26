ALTER TABLE cars
ADD COLUMN color VARCHAR(25);

ALTER TABLE cars
ADD COLUMN vin VARCHAR(25);

UPDATE cars
    SET color = 'white', vin = 'XXXX1234'
WHERE id=1;

UPDATE cars
SET color = 'white', vin = 'XXXX2345'
WHERE id=2;
