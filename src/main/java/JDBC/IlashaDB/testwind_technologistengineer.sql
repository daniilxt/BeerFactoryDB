create table technologistengineer
(
    IdTechnologistEngineer int auto_increment
        primary key,
    Name                   varchar(50) not null,
    SecondName             varchar(50) not null,
    MiddleName             varchar(50) not null,
    Phone                  varchar(50) not null,
    DateJoin               date        not null,
    DateDismiss            date        null,
    Salary                 int         not null,
    IdUser                 int         null,
    constraint TechnologistEngineer_ibfk_1
        foreign key (IdUser) references user (IdUser)
);

create index IdUser
    on technologistengineer (IdUser);

INSERT INTO technologistengineer (Name, SecondName, MiddleName, Phone, DateJoin,
                                           DateDismiss, Salary, IdUser)
VALUES ('Alexey', 'Novikov', 'Igorevich', '8(952)344-24-15', '2020-12-01', null, 20000, 1);
INSERT INTO testwind.technologistengineer (IdTechnologistEngineer, Name, SecondName, MiddleName, Phone, DateJoin,
                                           DateDismiss, Salary, IdUser)
VALUES (2, 'Anton', 'Ptushkin', 'Olegovich', '8(911)234-123-33', '2020-12-02', null, 20000, 2);