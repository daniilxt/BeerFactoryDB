create table beerstorage
(
    IdBeerKind int auto_increment
        primary key,
    Name       varchar(50) not null,
    Price      int         not null,
    Type       varchar(50) not null,
    Amount     int         not null,
    CookTime   int         not null,
    constraint Name
        unique (Name)
);

INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (1, 'Ale', 100, 'Our', 0, 50);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (2, 'Lager', 120, 'Our', 1, 100);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (3, 'VolkovskoeHoney', 150, 'Import', 6, 1);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (4, 'GarageLemon', 80, 'Import', 12, 1);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (5, 'GingerBeer', 90, 'Craft', 8, 30);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (6, 'BavarianWheat', 125, 'Our', 2, 40);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (7, 'Lejak', 110, 'Import', 4, 40);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (8, 'AfBrew ', 140, 'Import', 4, 40);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (9, 'Orval', 90, 'Import', 6, 40);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (10, 'DarkUnfiltered', 70, 'Our', 24, 40);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (11, 'AmberBeer', 77, 'Our', 7, 40);