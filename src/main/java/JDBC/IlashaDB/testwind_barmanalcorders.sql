create table barmanalcorders
(
    IdBarmanAlcOrder int auto_increment
        primary key,
    IdBarman         int         not null,
    IdManager        int         null,
    Date             date        not null,
    Status           varchar(50) not null,
    constraint BarmanAlcOrders_ibfk_1
        foreign key (IdBarman) references barman (IdBarMan),
    constraint barmanalcorders_manager_IdManager_fk
        foreign key (IdManager) references manager (IdManager)
);

INSERT INTO testwind.barmanalcorders (IdBarmanAlcOrder, IdBarman, IdManager, Date, Status) VALUES (1, 1, 1, '2020-12-16', 'process');
INSERT INTO testwind.barmanalcorders (IdBarmanAlcOrder, IdBarman, IdManager, Date, Status) VALUES (2, 1, 1, '2020-12-15', 'done');
INSERT INTO testwind.barmanalcorders (IdBarmanAlcOrder, IdBarman, IdManager, Date, Status) VALUES (3, 1, 3, '2020-12-17', 'done');
INSERT INTO testwind.barmanalcorders (IdBarmanAlcOrder, IdBarman, IdManager, Date, Status) VALUES (4, 6, 4, '2020-12-18', 'done');
INSERT INTO testwind.barmanalcorders (IdBarmanAlcOrder, IdBarman, IdManager, Date, Status) VALUES (5, 1, 4, '2020-12-19', 'done');