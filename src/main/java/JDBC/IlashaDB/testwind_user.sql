create table user
(
    IdUser   int auto_increment
        primary key,
    Login    varchar(30) not null,
    Password varchar(30) not null,
    Role     varchar(50) not null,
    constraint Login
        unique (Login)
);

INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (1, 'w_eng1', 'd19lbmcx', 'engineer');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (2, 'w_eng2', 'd19lbmcy', 'engineer');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (3, 'w_man1', 'd19tYW4x', 'staff_manager');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (4, 'w_bar1', 'd19iYXIx', 'barman');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (5, 'w_load1', 'd19sb2FkMQ==', 'loader');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (6, 'w_man2', 'd19tYW4y', 'manager');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (7, 'w_staff1', 'd19zdGFmZjE=', 'staff_manager');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (9, 'c_man1', '1', 'client');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (13, 'c_man2', '1', 'client');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (14, 'c_man3', '1', 'client');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (15, 'c_man4', '1', 'client');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (17, 'w_test', 'dad', 'client');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (50, 'aaa', 'aaa', 'client');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (51, 'aaaa', 'aaaa', 'client');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (63, 'dfd', 'dfd', 'fdd');
INSERT INTO testwind.user (IdUser, Login, Password, Role) VALUES (64, 'sds', 'sdsd', 'sds');