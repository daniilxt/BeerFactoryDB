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

INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (1, 'Ale', 100, 'Our', 10, 50);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (2, 'Lager', 120, 'Our', 4, 100);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (3, 'VolkovskoeHoney', 150, 'Import', 48, 1);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (4, 'GarageLemon', 80, 'Import', 24, 1);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (5, 'GingerBeer', 90, 'Craft', 10, 30);
INSERT INTO testwind.beerstorage (IdBeerKind, Name, Price, Type, Amount, CookTime) VALUES (6, 'BavarianWheat', 125, 'Our', 2, 40);