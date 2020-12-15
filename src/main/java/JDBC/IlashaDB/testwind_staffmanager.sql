create table staffmanager
(
    IdStaffManager int auto_increment
        primary key,
    Name           varchar(50) not null,
    SecondName     varchar(50) not null,
    MiddleName     varchar(50) not null,
    Phone          varchar(50) not null,
    DateJoin       date        not null,
    DateDismiss    date        null,
    Salary         int         not null,
    IdUser         int         null,
    constraint StaffManager_ibfk_1
        foreign key (IdUser) references user (IdUser)
);

create index IdUser
    on staffmanager (IdUser);

