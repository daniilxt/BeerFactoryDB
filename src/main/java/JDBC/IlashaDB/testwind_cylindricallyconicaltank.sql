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

INSERT INTO testwind.cylindricallyconicaltank (IdCCT, IdTask, DateStart, DateEnd, StatusCCT)
VALUES (1, 1, '1970-01-01', '1970-02-10', 'WORK');
INSERT INTO testwind.cylindricallyconicaltank (IdCCT, IdTask, DateStart, DateEnd, StatusCCT)
VALUES (2, 1, '1970-01-01', null, 'FREE');
INSERT INTO testwind.cylindricallyconicaltank (IdCCT, IdTask, DateStart, DateEnd, StatusCCT)
VALUES (3, 1, '1970-01-01', null, 'FREE');