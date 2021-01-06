create table cylindricallyconicaltank
(
    IdCCT     int auto_increment
        primary key,
    IdTask    int         not null,
    DateStart date        not null,
    DateEnd   date        null,
    StatusCCT varchar(20) not null,
    constraint CylindricallyConicalTank_ibfk_1
        foreign key (IdTask) references task (IdTask)
);

create index IdTask
    on cylindricallyconicaltank (IdTask);

INSERT INTO testwind.cylindricallyconicaltank (IdCCT, IdTask, DateStart, DateEnd, StatusCCT) VALUES (1, 1, '1970-01-01', '1970-02-10', 'WORK');
INSERT INTO testwind.cylindricallyconicaltank (IdCCT, IdTask, DateStart, DateEnd, StatusCCT) VALUES (2, 6, '2020-12-19', '2021-01-28', 'WORK');
INSERT INTO testwind.cylindricallyconicaltank (IdCCT, IdTask, DateStart, DateEnd, StatusCCT) VALUES (3, 6, '1970-01-01', null, 'FREE');
INSERT INTO testwind.cylindricallyconicaltank (IdCCT, IdTask, DateStart, DateEnd, StatusCCT) VALUES (4, 1, '2020-12-19', '2021-01-28', 'WORK');
INSERT INTO testwind.cylindricallyconicaltank (IdCCT, IdTask, DateStart, DateEnd, StatusCCT) VALUES (5, 1, '2020-12-19', '2021-01-28', 'WORK');
INSERT INTO testwind.cylindricallyconicaltank (IdCCT, IdTask, DateStart, DateEnd, StatusCCT) VALUES (6, 9, '1970-01-01', null, 'FREE');