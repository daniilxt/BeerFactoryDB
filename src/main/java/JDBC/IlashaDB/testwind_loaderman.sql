create table loaderman
(
    IdLoaderMan int auto_increment
        primary key,
    Name        varchar(50) not null,
    SecondName  varchar(50) not null,
    MiddleName  varchar(50) not null,
    Phone       varchar(50) not null,
    DateJoin    date        not null,
    DateDismiss date        null,
    Salary      int         not null,
    IdUser      int         null,
    constraint LoaderMan_ibfk_1
        foreign key (IdUser) references user (IdUser)
);

create index IdUser
    on loaderman (IdUser);

INSERT INTO testwind.loaderman (IdLoaderMan, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, Salary, IdUser) VALUES (1, 'Grih', 'Tolstik', 'Nikolaevuch', '8(921)-765-67-87', '2019-05-06', null, 20000, 5);