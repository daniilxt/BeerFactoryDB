create table barmanorderposition
(
    IdBarmanOrderPosition int auto_increment
        primary key,
    Number                int not null,
    IdBarmanOrder         int not null,
    IdBeerKind            int not null,
    constraint BarmanOrderPosition_ibfk_1
        foreign key (IdBarmanOrder) references barmanorders (IdBarmanOrder),
    constraint BarmanOrderPosition_ibfk_2
        foreign key (IdBeerKind) references beerstorage (IdBeerKind)
);

INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (1, 2, 1, 2);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (2, 1, 1, 6);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (3, 3, 2, 3);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (4, 4, 3, 1);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (5, 4, 4, 1);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (6, 5, 4, 2);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (7, 3, 4, 8);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (8, 7, 5, 11);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (9, 2, 6, 1);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (10, 2, 7, 11);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (11, 1, 8, 10);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (12, 1, 9, 9);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (13, 2, 10, 2);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (14, 3, 10, 1);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (15, 1, 11, 3);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (16, 1, 11, 7);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (17, 1, 11, 8);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind)
VALUES (18, 2, 11, 9);