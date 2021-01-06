create table resourcestorage
(
    IdResource int auto_increment
        primary key,
    Name       varchar(50) not null,
    Amount     int         not null,
    Price      int         not null,
    Unit       varchar(50) not null,
    constraint Name
        unique (Name)
);

INSERT INTO testwind.resourcestorage (IdResource, Name, Amount, Price, Unit) VALUES (1, 'Water', 785, 1, 'liters');
INSERT INTO testwind.resourcestorage (IdResource, Name, Amount, Price, Unit) VALUES (2, 'HotWater', 828, 2, 'liters');
INSERT INTO testwind.resourcestorage (IdResource, Name, Amount, Price, Unit) VALUES (3, 'Hop', 12, 4, 'piece');
INSERT INTO testwind.resourcestorage (IdResource, Name, Amount, Price, Unit) VALUES (4, 'Sugar', 20000, 3, 'kg');
INSERT INTO testwind.resourcestorage (IdResource, Name, Amount, Price, Unit) VALUES (5, 'Wheat', 912, 2, 'kg');
INSERT INTO testwind.resourcestorage (IdResource, Name, Amount, Price, Unit) VALUES (6, 'MaltPilsher', 200, 4, 'kg');
INSERT INTO testwind.resourcestorage (IdResource, Name, Amount, Price, Unit) VALUES (7, 'MunichMalt', 4, 3, 'kg');
INSERT INTO testwind.resourcestorage (IdResource, Name, Amount, Price, Unit) VALUES (8, 'Malt', 22, 1, 'kg');
INSERT INTO testwind.resourcestorage (IdResource, Name, Amount, Price, Unit) VALUES (9, 'AscorbicAcid', 1, 1, 'kg');
INSERT INTO testwind.resourcestorage (IdResource, Name, Amount, Price, Unit) VALUES (10, 'Yeat', 148, 1, 'kg');