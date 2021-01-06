create table orderposition
(
    IdOrderPosition int auto_increment
        primary key,
    Number          int not null,
    IdOrder         int not null,
    IdBeerKind      int not null,
    constraint OrderPosition_ibfk_1
        foreign key (IdOrder) references orders (IdOrder),
    constraint OrderPosition_ibfk_2
        foreign key (IdBeerKind) references beerstorage (IdBeerKind)
);

create index IdBeerKind
    on orderposition (IdBeerKind);

create index IdOrder
    on orderposition (IdOrder);

INSERT INTO orderposition (Number, IdOrder, IdBeerKind)
VALUES (3, 1, 3);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (2, 5, 1, 4);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (3, 4, 1, 1);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (4, 4, 1, 2);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (5, 2, 7, 1);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (6, 1, 7, 2);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (7, 10, 8, 4);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (8, 2, 8, 3);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (9, 11, 13, 1);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (10, 15, 13, 3);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (11, 5, 13, 5);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (12, 8, 15, 4);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (13, 7, 15, 2);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (14, 10, 15, 1);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (15, 6, 15, 3);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (16, 8, 15, 6);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (17, 10, 15, 5);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (18, 8, 16, 1);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (19, 6, 16, 2);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (20, 1, 16, 4);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (21, 1, 16, 3);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (22, 1, 17, 6);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (23, 2, 18, 1);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (24, 4, 18, 2);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (25, 2, 19, 1);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (26, 1, 19, 2);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (27, 7, 20, 3);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (28, 2, 20, 4);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (29, 5, 20, 5);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (30, 6, 21, 1);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (31, 2, 21, 2);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (32, 1, 21, 3);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (33, 2, 21, 5);
INSERT INTO testwind.orderposition (IdOrderPosition, Number, IdOrder, IdBeerKind)
VALUES (34, 1, 21, 4);