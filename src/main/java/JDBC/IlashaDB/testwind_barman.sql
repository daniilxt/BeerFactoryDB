create table barman
(
    IdBarMan    int auto_increment
        primary key,
    Name        varchar(50) not null,
    SecondName  varchar(50) not null,
    MiddleName  varchar(50) not null,
    Phone       varchar(50) not null,
    DateJoin    date        not null,
    DateDismiss date        null,
    Salary      int         not null,
    IdUser      int         null,
    constraint BarMan_ibfk_1
        foreign key (IdUser) references user (IdUser)
);

create index IdUser
    on barman (IdUser);

INSERT INTO testwind.barman (IdBarMan, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, Salary, IdUser) VALUES (1, 'Artur', 'Mkrtchan', 'Papikovich', '8(951)567-65-32', '2019-04-03', null, 21000, 4);
INSERT INTO testwind.barman (IdBarMan, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, Salary, IdUser) VALUES (3, 'f', 'f', 'f', 'f', '2020-12-11', null, 343434, null);
INSERT INTO testwind.barman (IdBarMan, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, Salary, IdUser) VALUES (4, 'a', 'f', 'f', 'f', '2020-12-10', '2020-12-15', 343434, null);
INSERT INTO testwind.barman (IdBarMan, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, Salary, IdUser) VALUES (5, 'f', 'a', 'f', 'f', '2020-12-09', null, 343434, 50);
INSERT INTO testwind.barman (IdBarMan, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, Salary, IdUser) VALUES (6, 'f', 'f', 'a', 'f', '2020-12-08', null, 343434, 51);