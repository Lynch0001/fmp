CREATE TYPE VehicleStatus AS ENUM('AVAILABLE', 'NOTAVAILABLE_M', 'NOTAVAILABLE_S', 'NOTAVAILABLE_P', 'NOTAVAILABLE_D', 'NOTAVAILABLE_X', 'NOTAVAILABLE_A');

alter table cars add column current_status varchar(25);

UPDATE cars
SET current_status='NOTAVAILABLE_X'
WHERE bumper='B003';

UPDATE cars
SET current_status='NOTAVAILABLE_P'
WHERE bumper='A003';
