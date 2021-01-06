create table technologistengineerresorderposition
(
    IdBarmanAlcOrderPosition       int auto_increment
        primary key,
    Number                         int not null,
    IdTechnologistEngineerResOrder int not null,
    IdResource                     int not null,
    constraint TechnologistEngineerResOrderPosition_ibfk_1
        foreign key (IdTechnologistEngineerResOrder) references technologistengineerresorders (IdTechnologistEngineerResOrder),
    constraint TechnologistEngineerResOrderPosition_ibfk_2
        foreign key (IdResource) references resourcestorage (IdResource)
);

INSERT INTO testwind.technologistengineerresorderposition (IdBarmanAlcOrderPosition, Number, IdTechnologistEngineerResOrder, IdResource) VALUES (1, 6, 3, 7);
INSERT INTO testwind.technologistengineerresorderposition (IdBarmanAlcOrderPosition, Number, IdTechnologistEngineerResOrder, IdResource) VALUES (2, 6, 4, 9);
INSERT INTO testwind.technologistengineerresorderposition (IdBarmanAlcOrderPosition, Number, IdTechnologistEngineerResOrder, IdResource) VALUES (3, 1, 5, 9);
INSERT INTO testwind.technologistengineerresorderposition (IdBarmanAlcOrderPosition, Number, IdTechnologistEngineerResOrder, IdResource) VALUES (4, 1, 6, 9);
INSERT INTO testwind.technologistengineerresorderposition (IdBarmanAlcOrderPosition, Number, IdTechnologistEngineerResOrder, IdResource) VALUES (5, 1, 7, 9);
INSERT INTO testwind.technologistengineerresorderposition (IdBarmanAlcOrderPosition, Number, IdTechnologistEngineerResOrder, IdResource) VALUES (6, 2, 8, 7);
INSERT INTO testwind.technologistengineerresorderposition (IdBarmanAlcOrderPosition, Number, IdTechnologistEngineerResOrder, IdResource) VALUES (7, 6, 9, 9);