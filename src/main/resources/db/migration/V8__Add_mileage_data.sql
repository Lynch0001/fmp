ALTER TABLE cars
    ADD COLUMN mileage INT;

UPDATE cars
SET mileage=10
WHERE bumper='B002';

UPDATE cars
SET mileage=10
WHERE bumper='A002';

UPDATE cars
SET mileage=10
WHERE bumper='A004';

UPDATE cars
SET mileage=10
WHERE bumper='B004';

UPDATE cars
SET mileage=10
WHERE bumper='A005';

UPDATE cars
SET mileage=10
WHERE bumper='B001';

UPDATE cars
SET mileage=10
WHERE bumper='A001';

UPDATE cars
SET mileage=10
WHERE bumper='A003';

UPDATE cars
SET mileage=10
WHERE bumper='B003';
