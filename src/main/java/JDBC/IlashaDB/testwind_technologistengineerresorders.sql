create table technologistengineerresorders
(
    IdTechnologistEngineerResOrder int auto_increment
        primary key,
    IdTechnologistEngineer         int         not null,
    IdManager                      int         null,
    Date                           date        not null,
    Status                         varchar(50) not null,
    constraint TechnologistEngineerResOrders_ibfk_1
        foreign key (IdTechnologistEngineer) references technologistengineer (IdTechnologistEngineer),
    constraint technologistengineerresorders_manager_IdManager_fk
        foreign key (IdManager) references manager (IdManager)
);

INSERT INTO testwind.technologistengineerresorders (IdTechnologistEngineerResOrder, IdTechnologistEngineer, IdManager,
                                                    Date, Status)
VALUES (3, 1, 1, '2020-12-16', 'done');
INSERT INTO testwind.technologistengineerresorders (IdTechnologistEngineerResOrder, IdTechnologistEngineer, IdManager,
                                                    Date, Status)
VALUES (4, 1, 3, '2020-12-17', 'done');
INSERT INTO testwind.technologistengineerresorders (IdTechnologistEngineerResOrder, IdTechnologistEngineer, IdManager,
                                                    Date, Status)
VALUES (5, 1, 4, '2020-12-18', 'done');
INSERT INTO testwind.technologistengineerresorders (IdTechnologistEngineerResOrder, IdTechnologistEngineer, IdManager,
                                                    Date, Status)
VALUES (6, 1, 4, '2020-12-18', 'done');
INSERT INTO testwind.technologistengineerresorders (IdTechnologistEngineerResOrder, IdTechnologistEngineer, IdManager,
                                                    Date, Status)
VALUES (7, 1, 4, '2020-12-18', 'process');
INSERT INTO testwind.technologistengineerresorders (IdTechnologistEngineerResOrder, IdTechnologistEngineer, IdManager,
                                                    Date, Status)
VALUES (8, 1, 1, '2020-12-19', 'process');
INSERT INTO testwind.technologistengineerresorders (IdTechnologistEngineerResOrder, IdTechnologistEngineer, IdManager,
                                                    Date, Status)
VALUES (9, 1, 1, '2020-12-19', 'done');