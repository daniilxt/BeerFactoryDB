create table orders
(
    IdOrder   int auto_increment
        primary key,
    IdClient  int         not null,
    IdManager int         not null,
    Date      date        not null,
    Status    varchar(50) not null,
    constraint Orders_ibfk_1
        foreign key (IdClient) references clientlist (IdClient),
    constraint Orders_ibfk_2
        foreign key (IdManager) references manager (IdManager)
);

create index IdClient
    on orders (IdClient);

create index IdManager
    on orders (IdManager);

INSERT INTO testwind.orders (IdOrder, IdClient, IdManager, Date, Status) VALUES (1, 34, 1, '2020-01-01', 'process');
INSERT INTO testwind.orders (IdOrder, IdClient, IdManager, Date, Status) VALUES (2, 34, 2, '2020-01-01', 'process');
INSERT INTO testwind.orders (IdOrder, IdClient, IdManager, Date, Status) VALUES (5, 6, 1, '2020-04-01', 'process');
INSERT INTO testwind.orders (IdOrder, IdClient, IdManager, Date, Status) VALUES (7, 34, 2, '2020-12-14', 'process');
INSERT INTO testwind.orders (IdOrder, IdClient, IdManager, Date, Status) VALUES (8, 34, 1, '2020-12-14', 'process');
INSERT INTO testwind.orders (IdOrder, IdClient, IdManager, Date, Status) VALUES (13, 34, 1, '2020-12-14', 'process');
INSERT INTO testwind.orders (IdOrder, IdClient, IdManager, Date, Status) VALUES (15, 34, 2, '2020-12-14', 'process');
INSERT INTO testwind.orders (IdOrder, IdClient, IdManager, Date, Status) VALUES (16, 34, 1, '2020-12-14', 'process');
INSERT INTO testwind.orders (IdOrder, IdClient, IdManager, Date, Status) VALUES (17, 34, 1, '2020-12-15', 'process');
INSERT INTO testwind.orders (IdOrder, IdClient, IdManager, Date, Status) VALUES (18, 35, 1, '2020-12-15', 'process');