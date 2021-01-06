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

INSERT INTO testwind.importalcbuyposition (Number, IdImportAlcBuy, IdBeerKind)
VALUES (5, 2, 3);
INSERT INTO testwind.importalcbuyposition (IdImportAlcPosition, Number, IdImportAlcBuy, IdBeerKind)
VALUES (2, 7, 2, 4);