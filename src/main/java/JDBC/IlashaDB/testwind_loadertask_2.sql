create table loadertask
(
    IdLoaderTask int auto_increment
        primary key,
    IdLoaderMan  int         not null,
    IdResBuy     int         not null,
    IdImportAlc  int         not null,
    Date         date        not null,
    Status       varchar(50) not null,
    IdManager    int         null,
    constraint LoaderTask_ibfk_1
        foreign key (IdLoaderMan) references loaderman (IdLoaderMan),
    constraint LoaderTask_ibfk_2
        foreign key (IdResBuy) references resbuy (IdResBuy),
    constraint LoaderTask_ibfk_3
        foreign key (IdImportAlc) references importalcbuy (IdImportAlcBuy),
    constraint loadertask_manager_IdManager_fk
        foreign key (IdManager) references manager (IdManager)
);

create index IdImportAlc
    on loadertask (IdImportAlc);

create index IdLoaderMan
    on loadertask (IdLoaderMan);

create index IdResBuy
    on loadertask (IdResBuy);

INSERT INTO loadertask (IdLoaderMan, IdResBuy, IdImportAlc, Date, Status, IdManager)
VALUES ( 1, 3, 1, '2020-01-01', 'done', 1);
INSERT INTO testwind.loadertask (IdLoaderTask, IdLoaderMan, IdResBuy, IdImportAlc, Date, Status, IdManager)
VALUES (2, 1, 4, 1, '2020-01-01', 'process', 1);
INSERT INTO testwind.loadertask (IdLoaderTask, IdLoaderMan, IdResBuy, IdImportAlc, Date, Status, IdManager)
VALUES (3, 1, 5, 2, '2020-03-12', 'process', 1);
INSERT INTO testwind.loadertask (IdLoaderTask, IdLoaderMan, IdResBuy, IdImportAlc, Date, Status, IdManager)
VALUES (11, 1, 5, 6, '2020-12-16', 'process', 1);
INSERT INTO testwind.loadertask (IdLoaderTask, IdLoaderMan, IdResBuy, IdImportAlc, Date, Status, IdManager)
VALUES (12, 1, 5, 7, '2020-12-16', 'process', 1);
INSERT INTO testwind.loadertask (IdLoaderTask, IdLoaderMan, IdResBuy, IdImportAlc, Date, Status, IdManager)
VALUES (15, 1, 12, 1, '2020-12-18', 'process', 4);
INSERT INTO testwind.loadertask (IdLoaderTask, IdLoaderMan, IdResBuy, IdImportAlc, Date, Status, IdManager)
VALUES (16, 1, 5, 10, '2020-12-18', 'done', 4);
INSERT INTO testwind.loadertask (IdLoaderTask, IdLoaderMan, IdResBuy, IdImportAlc, Date, Status, IdManager)
VALUES (17, 1, 13, 1, '2020-12-18', 'done', 4);
INSERT INTO testwind.loadertask (IdLoaderTask, IdLoaderMan, IdResBuy, IdImportAlc, Date, Status, IdManager)
VALUES (18, 1, 5, 11, '2020-12-19', 'process', 1);
INSERT INTO testwind.loadertask (IdLoaderTask, IdLoaderMan, IdResBuy, IdImportAlc, Date, Status, IdManager)
VALUES (19, 1, 5, 12, '2020-12-19', 'done', 1);
INSERT INTO testwind.loadertask (IdLoaderTask, IdLoaderMan, IdResBuy, IdImportAlc, Date, Status, IdManager)
VALUES (20, 1, 5, 13, '2020-12-19', 'done', 4);
INSERT INTO testwind.loadertask (IdLoaderTask, IdLoaderMan, IdResBuy, IdImportAlc, Date, Status, IdManager)
VALUES (21, 1, 14, 1, '2020-12-19', 'done', 1);