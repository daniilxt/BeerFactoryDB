create table manager
(
    IdManager   int auto_increment
        primary key,
    Name        varchar(50) not null,
    SecondName  varchar(50) not null,
    MiddleName  varchar(50) not null,
    Phone       varchar(50) not null,
    DateJoin    date        not null,
    DateDismiss date        null,
    Salary      int         not null,
    IdUser      int         null,
    constraint Manager_ibfk_1
        foreign key (IdUser) references user (IdUser)
);

create index IdUser
    on manager (IdUser);

INSERT INTO testwind.manager (IdManager, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, Salary, IdUser) VALUES (1, 'Alexandr', 'Shpak', 'Kringevich', '8(921)-924-09-43', '2019-01-01', null, 22000, 6);
INSERT INTO testwind.manager (IdManager, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, Salary, IdUser) VALUES (2, 'Alisher', 'Morgen', 'Tagirovich', '8(999)-945-99-45', '2019-02-02', '2020-12-15', 22000, null);
INSERT INTO testwind.manager (IdManager, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, Salary, IdUser) VALUES (3, 'Alexey', 'Navalny', 'Anatolievich', '8(900)540-34-55', '2019-01-05', null, 22000, 65);
INSERT INTO testwind.manager (IdManager, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, Salary, IdUser) VALUES (4, 'm4', 'm4', 'm4', 'm4', '2020-12-01', '2020-12-19', 22000, null);