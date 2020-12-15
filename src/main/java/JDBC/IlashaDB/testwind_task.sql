create table task
(
    IdTask                  int auto_increment
        primary key,
    IdTechnologicalEngineer int         not null,
    IdBeerKind              int         not null,
    Date                    date        not null,
    Status                  varchar(50) not null,
    Amount                  int         not null,
    constraint Task_ibfk_1
        foreign key (IdTechnologicalEngineer) references technologistengineer (IdTechnologistEngineer),
    constraint Task_ibfk_2
        foreign key (IdBeerKind) references beerstorage (IdBeerKind)
);

create index IdBeerKind
    on task (IdBeerKind);

create index IdTechnologicalEngineer
    on task (IdTechnologicalEngineer);

INSERT INTO testwind.task (IdTask, IdTechnologicalEngineer, IdBeerKind, Date, Status, Amount) VALUES (1, 1, 1, '2020-01-14', 'Active', 3);
INSERT INTO testwind.task (IdTask, IdTechnologicalEngineer, IdBeerKind, Date, Status, Amount) VALUES (3, 2, 2, '2020-01-04', 'Done', 4);
INSERT INTO testwind.task (IdTask, IdTechnologicalEngineer, IdBeerKind, Date, Status, Amount) VALUES (4, 1, 6, '2020-01-15', 'Active', 5);