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

INSERT INTO testwind.user (IdUser, Login, Password, Role)
VALUES (1, 'w_eng1', 'd19lbmcx', 'engineer');
INSERT INTO testwind.user (IdUser, Login, Password, Role)
VALUES (2, 'w_eng2', 'd19lbmcy', 'engineer');
INSERT INTO testwind.user (IdUser, Login, Password, Role)
VALUES (3, 'w_man1', 'd19tYW4x', 'staff_manager');
INSERT INTO testwind.user (IdUser, Login, Password, Role)
VALUES (4, 'w_bar1', 'd19iYXIx', 'barman');
INSERT INTO testwind.user (IdUser, Login, Password, Role)
VALUES (5, 'w_load1', 'd19sb2FkMQ==', 'loader');
INSERT INTO testwind.user (IdUser, Login, Password, Role)
VALUES (6, 'w_man2', 'd19tYW4y', 'manager');
INSERT INTO testwind.user (IdUser, Login, Password, Role)
VALUES (50, 'aa', 'YWFh', 'client');
INSERT INTO testwind.user (IdUser, Login, Password, Role)
VALUES (51, 'aaaa', 'YWE=', 'barman');
INSERT INTO testwind.user (IdUser, Login, Password, Role)
VALUES (65, 'w_man3', 'd19tYW4z', 'manager');
INSERT INTO testwind.user (IdUser, Login, Password, Role)
VALUES (66, 'dxt', 'ZGFuaWlseHQ=', 'client');
INSERT INTO testwind.user (IdUser, Login, Password, Role)
VALUES (83, 'c_1', 'Y18x', 'client');
INSERT INTO testwind.user (IdUser, Login, Password, Role)
VALUES (87, 'c_oleg', 'MTIzNA==', 'client');
INSERT INTO testwind.user (IdUser, Login, Password, Role)
VALUES (88, 'w_eng5', 'MTIzNDU2', 'engineer');