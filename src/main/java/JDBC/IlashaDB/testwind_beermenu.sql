create definer = root@localhost view beermenu as
select `testwind`.`beerstorage`.`Name`   AS `Name`,
       `testwind`.`beerstorage`.`Type`   AS `Type`,
       `testwind`.`beerstorage`.`Amount` AS `Amount`,
       `testwind`.`beerstorage`.`Price`  AS `Price`
from `testwind`.`beerstorage`
where `testwind`.`beerstorage`.`Amount` > 0;

INSERT INTO testwind.beermenu (Name, Type, Amount, Price) VALUES ('Ale', 'Our', 10, 100);
INSERT INTO testwind.beermenu (Name, Type, Amount, Price) VALUES ('Lager', 'Our', 4, 120);
INSERT INTO testwind.beermenu (Name, Type, Amount, Price) VALUES ('VolkovskoeHoney', 'Import', 48, 150);
INSERT INTO testwind.beermenu (Name, Type, Amount, Price) VALUES ('GarageLemon', 'Import', 24, 80);
INSERT INTO testwind.beermenu (Name, Type, Amount, Price) VALUES ('GingerBeer', 'Craft', 10, 90);
INSERT INTO testwind.beermenu (Name, Type, Amount, Price) VALUES ('BavarianWheat', 'Our', 2, 125);