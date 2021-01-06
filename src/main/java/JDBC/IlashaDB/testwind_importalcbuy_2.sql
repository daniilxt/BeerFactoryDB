create table importalcbuy
(
    IdImportAlcBuy int auto_increment
        primary key,
    IdBarman       int         not null,
    IdManager      int         not null,
    Date           date        not null,
    Status         varchar(50) not null,
    constraint ImportAlcBuy_ibfk_1
        foreign key (IdBarman) references barman (IdBarMan),
    constraint ImportAlcBuy_ibfk_2
        foreign key (IdManager) references manager (IdManager)
);

create index IdBarman
    on importalcbuy (IdBarman);

create index IdManager
    on importalcbuy (IdManager);

INSERT INTO testwind.importalcbuy (IdImportAlcBuy, IdBarman, IdManager, Date, Status) VALUES (1, 1, 1, '2020-01-02', 'system');
INSERT INTO testwind.importalcbuy (IdImportAlcBuy, IdBarman, IdManager, Date, Status) VALUES (2, 1, 1, '2020-02-03', 'done');
INSERT INTO testwind.importalcbuy (IdImportAlcBuy, IdBarman, IdManager, Date, Status) VALUES (3, 1, 1, '2020-12-16', 'process');
INSERT INTO testwind.importalcbuy (IdImportAlcBuy, IdBarman, IdManager, Date, Status) VALUES (5, 1, 1, '2020-12-16', 'done');
INSERT INTO testwind.importalcbuy (IdImportAlcBuy, IdBarman, IdManager, Date, Status) VALUES (6, 1, 1, '2020-12-16', 'done');
INSERT INTO testwind.importalcbuy (IdImportAlcBuy, IdBarman, IdManager, Date, Status) VALUES (7, 1, 1, '2020-12-16', 'done');
INSERT INTO testwind.importalcbuy (IdImportAlcBuy, IdBarman, IdManager, Date, Status) VALUES (10, 6, 4, '2020-12-18', 'done');
INSERT INTO testwind.importalcbuy (IdImportAlcBuy, IdBarman, IdManager, Date, Status) VALUES (11, 1, 1, '2020-12-19', 'done');
INSERT INTO testwind.importalcbuy (IdImportAlcBuy, IdBarman, IdManager, Date, Status) VALUES (12, 1, 1, '2020-12-19', 'done');
INSERT INTO testwind.importalcbuy (IdImportAlcBuy, IdBarman, IdManager, Date, Status) VALUES (13, 1, 4, '2020-12-19', 'done');