create table importalcbuyposition
(
    IdImportAlcPosition int auto_increment
        primary key,
    Number              int not null,
    IdImportAlcBuy      int not null,
    IdBeerKind          int not null,
    constraint ImportAlcBuyPosition_ibfk_1
        foreign key (IdImportAlcBuy) references importalcbuy (IdImportAlcBuy),
    constraint ImportAlcBuyPosition_ibfk_2
        foreign key (IdBeerKind) references beerstorage (IdBeerKind)
);

create index IdBeerKind
    on importalcbuyposition (IdBeerKind);

create index IdImportAlcBuy
    on importalcbuyposition (IdImportAlcBuy);

INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (1, 5, 2, 3);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (2, 7, 2, 4);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (3, 2, 3, 4);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (4, 44, 3, 7);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (5, 8, 3, 3);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (9, 5, 5, 3);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (10, 2, 6, 4);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (11, 44, 6, 7);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (12, 8, 6, 3);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (13, 2, 7, 4);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (14, 44, 7, 7);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (15, 8, 7, 3);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (16, 4, 7, 7);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (17, 6, 10, 9);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (18, 6, 11, 4);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (19, 6, 12, 4);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (20, 4, 13, 3);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind) VALUES (21, 3, 13, 4);