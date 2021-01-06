create table barmanorders
(
    IdBarmanOrder int auto_increment
        primary key,
    IdBarman      int         not null,
    Date          date        not null,
    Status        varchar(50) not null,
    constraint BarmanOrders_ibfk_1
        foreign key (IdBarman) references barman (IdBarMan)
);

INSERT INTO testwind.barmanorders (IdBarmanOrder, IdBarman, Date, Status)
VALUES (1, 1, '2020-12-15', 'done');
INSERT INTO testwind.barmanorders (IdBarmanOrder, IdBarman, Date, Status)
VALUES (2, 1, '2020-12-15', 'done');