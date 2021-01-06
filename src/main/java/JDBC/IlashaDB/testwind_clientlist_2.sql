create table clientlist
(
    IdClient         int auto_increment
        primary key,
    NameClient       varchar(50) not null,
    SecondNameClient varchar(50) not null,
    MiddleNameClient varchar(50) not null,
    PhoneClient      varchar(50) not null,
    Age              date        not null,
    DateJoin         date        not null,
    IdUser           int         null,
    DateDismiss      date        null,
    constraint PhoneClient
        unique (PhoneClient),
    constraint clientlist_user_IdUser_fk
        foreign key (IdUser) references user (IdUser)
);

INSERT INTO testwind.clientlist (IdClient, NameClient, SecondNameClient, MiddleNameClient, PhoneClient, Age, DateJoin, IdUser, DateDismiss) VALUES (1, 'Jiga', 'Jeegun', 'Alekseevich', '8(954)212-01-23', '1970-01-03', '2020-01-01', 83, '2020-12-18');
INSERT INTO testwind.clientlist (IdClient, NameClient, SecondNameClient, MiddleNameClient, PhoneClient, Age, DateJoin, IdUser, DateDismiss) VALUES (2, 'Egor', 'Miami', 'Eldarovich', '8(999)-145-22-01', '1989-05-12', '2020-02-01', null, '2020-12-18');
INSERT INTO testwind.clientlist (IdClient, NameClient, SecondNameClient, MiddleNameClient, PhoneClient, Age, DateJoin, IdUser, DateDismiss) VALUES (3, 'Sergey', 'Ivanov', 'Artemovich', '8(999)-745-42-51', '1991-05-02', '2020-02-21', null, '2020-12-18');
INSERT INTO testwind.clientlist (IdClient, NameClient, SecondNameClient, MiddleNameClient, PhoneClient, Age, DateJoin, IdUser, DateDismiss) VALUES (4, 'Alla', 'Duhova', 'Vladimirovna', '8(954)647-01-23', '1996-05-04', '2020-05-21', null, '2020-12-19');
INSERT INTO testwind.clientlist (IdClient, NameClient, SecondNameClient, MiddleNameClient, PhoneClient, Age, DateJoin, IdUser, DateDismiss) VALUES (6, 'Boris', 'Moiseev', 'Mihailovich', '8(954)902-51-77', '1992-05-15', '2019-07-06', 50, null);
INSERT INTO testwind.clientlist (IdClient, NameClient, SecondNameClient, MiddleNameClient, PhoneClient, Age, DateJoin, IdUser, DateDismiss) VALUES (34, 'a', 'a', 'a', '8(954)903-51-77', '2020-12-03', '2020-12-14', null, null);
INSERT INTO testwind.clientlist (IdClient, NameClient, SecondNameClient, MiddleNameClient, PhoneClient, Age, DateJoin, IdUser, DateDismiss) VALUES (35, 'aa', 'aa', 'aa', '8(954)903-54-17', '2020-12-03', '2020-12-17', null, '2020-12-18');
INSERT INTO testwind.clientlist (IdClient, NameClient, SecondNameClient, MiddleNameClient, PhoneClient, Age, DateJoin, IdUser, DateDismiss) VALUES (40, 'Daniil', 'Firsov', 'Anat', '8(956)345-56-66', '2000-01-17', '2020-12-16', 66, null);
INSERT INTO testwind.clientlist (IdClient, NameClient, SecondNameClient, MiddleNameClient, PhoneClient, Age, DateJoin, IdUser, DateDismiss) VALUES (42, 'y', 'y', 'y', 'y', '2020-12-10', '2020-12-18', null, '2020-12-18');
INSERT INTO testwind.clientlist (IdClient, NameClient, SecondNameClient, MiddleNameClient, PhoneClient, Age, DateJoin, IdUser, DateDismiss) VALUES (46, 'Oleg', 'Tinkoff', 'Olegovich', '8(999)333-12-05', '1991-12-13', '2020-12-19', 87, null);