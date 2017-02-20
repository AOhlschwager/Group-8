USE uscities;

TRUNCATE table citiesrevised;

LOAD DATA LOCAL INFILE 'C:/Users/Shaya/Desktop/Senior Design/citiesIn.txt' 
INTO TABLE citiesrevised 
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
ESCAPED BY '"'
LINES TERMINATED BY 'Active';

SELECT id, name, stateid FROM citiesrevised
WHERE target != 'Postal Code' 
AND stateid != 0;