create table loadertask
(
    IdLoaderTask int auto_increment
        primary key,
    IdLoaderMan  int         not null,
    IdResBuy     int         not null,
    IdImportAlc  int         not null,
    Date         date        not null,
    Status       varchar(50) not null,
    constraint LoaderTask_ibfk_1
        foreign key (IdLoaderMan) references loaderman (IdLoaderMan),
    constraint LoaderTask_ibfk_2
        foreign key (IdResBuy) references resbuy (IdResBuy),
    constraint LoaderTask_ibfk_3
        foreign key (IdImportAlc) references importalcbuy (IdImportAlcBuy)
);

create index IdImportAlc
    on loadertask (IdImportAlc);

create index IdLoaderMan
    on loadertask (IdLoaderMan);

create index IdResBuy
    on loadertask (IdResBuy);

INSERT INTO testwind.loadertask (IdLoaderTask, IdLoaderMan, IdResBuy, IdImportAlc, Date, Status) VALUES (1, 1, 3, 1, '2020-01-01', 'done');
INSERT INTO testwind.loadertask (IdLoaderTask, IdLoaderMan, IdResBuy, IdImportAlc, Date, Status) VALUES (2, 1, 4, 1, '2020-01-01', 'done');
INSERT INTO testwind.loadertask (IdLoaderTask, IdLoaderMan, IdResBuy, IdImportAlc, Date, Status) VALUES (3, 1, 5, 2, '2020-03-12', 'done');