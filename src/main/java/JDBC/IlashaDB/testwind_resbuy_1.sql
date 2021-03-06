create table resbuy
(
    IdResBuy   int auto_increment
        primary key,
    IdEngineer int         not null,
    IdManager  int         not null,
    Date       date        not null,
    Status     varchar(50) not null,
    constraint ResBuy_ibfk_1
        foreign key (IdEngineer) references technologistengineer (IdTechnologistEngineer),
    constraint ResBuy_ibfk_2
        foreign key (IdManager) references manager (IdManager)
);

create index IdEngineer
    on resbuy (IdEngineer);

create index IdManager
    on resbuy (IdManager);

INSERT INTO testwind.resbuy (IdResBuy, IdEngineer, IdManager, Date, Status) VALUES (3, 1, 1, '2020-01-01', 'done');
INSERT INTO testwind.resbuy (IdResBuy, IdEngineer, IdManager, Date, Status) VALUES (4, 1, 1, '2020-01-02', 'done');
INSERT INTO testwind.resbuy (IdResBuy, IdEngineer, IdManager, Date, Status) VALUES (5, 1, 1, '2020-03-04', 'system');