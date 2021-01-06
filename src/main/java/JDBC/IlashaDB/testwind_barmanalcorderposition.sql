create table barmanalcorderposition
(
    IdBarmanAlcOrderPosition int auto_increment
        primary key,
    Number                   int not null,
    IdBarmanAlcOrder         int not null,
    IdBeerKind               int not null,
    constraint BarmanAlcOrderPosition_ibfk_1
        foreign key (IdBarmanAlcOrder) references barmanalcorders (IdBarmanAlcOrder),
    constraint BarmanAlcOrderPosition_ibfk_2
        foreign key (IdBeerKind) references beerstorage (IdBeerKind)
);

INSERT INTO testwind.barmanalcorderposition (IdBarmanAlcOrderPosition, Number, IdBarmanAlcOrder, IdBeerKind) VALUES (1, 5, 1, 3);
INSERT INTO testwind.barmanalcorderposition (IdBarmanAlcOrderPosition, Number, IdBarmanAlcOrder, IdBeerKind) VALUES (3, 2, 2, 4);
INSERT INTO testwind.barmanalcorderposition (IdBarmanAlcOrderPosition, Number, IdBarmanAlcOrder, IdBeerKind) VALUES (4, 44, 2, 7);
INSERT INTO testwind.barmanalcorderposition (IdBarmanAlcOrderPosition, Number, IdBarmanAlcOrder, IdBeerKind) VALUES (5, 8, 2, 3);
INSERT INTO testwind.barmanalcorderposition (IdBarmanAlcOrderPosition, Number, IdBarmanAlcOrder, IdBeerKind) VALUES (6, 4, 3, 7);
INSERT INTO testwind.barmanalcorderposition (IdBarmanAlcOrderPosition, Number, IdBarmanAlcOrder, IdBeerKind) VALUES (7, 6, 4, 9);
INSERT INTO testwind.barmanalcorderposition (IdBarmanAlcOrderPosition, Number, IdBarmanAlcOrder, IdBeerKind) VALUES (8, 4, 5, 3);
INSERT INTO testwind.barmanalcorderposition (IdBarmanAlcOrderPosition, Number, IdBarmanAlcOrder, IdBeerKind) VALUES (9, 3, 5, 4);