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

INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind) VALUES (1, 2, 1, 2);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind) VALUES (2, 1, 1, 6);
INSERT INTO testwind.barmanorderposition (IdBarmanOrderPosition, Number, IdBarmanOrder, IdBeerKind) VALUES (3, 3, 2, 3);