create definer = root@localhost view workers as
select `us`.`IdUser`                                 AS `IdUser`,
       `us`.`Login`                                  AS `Login`,
       `te`.`Name`                                   AS `Name`,
       `te`.`SecondName`                             AS `SecondName`,
       `te`.`MiddleName`                             AS `MiddleName`,
       `te`.`Phone`                                  AS `Phone`,
       `te`.`DateJoin`                               AS `DateJoin`,
       `te`.`DateDismiss`                            AS `DateDismiss`,
       `te`.`IdTechnologistEngineer`                 AS `ID_WORKER`,
       to_days(curdate()) - to_days(`te`.`DateJoin`) AS `WorksDays`
from (`testwind`.`technologistengineer` `te`
         left join `testwind`.`user` `us` on (`us`.`IdUser` = `te`.`IdUser`))
union
select `us`.`IdUser`                                 AS `IdUser`,
       `us`.`Login`                                  AS `Login`,
       `lm`.`Name`                                   AS `Name`,
       `lm`.`SecondName`                             AS `SecondName`,
       `lm`.`MiddleName`                             AS `MiddleName`,
       `lm`.`Phone`                                  AS `Phone`,
       `lm`.`DateJoin`                               AS `DateJoin`,
       `lm`.`DateDismiss`                            AS `DateDismiss`,
       `lm`.`IdLoaderMan`                            AS `ID_WORKER`,
       to_days(curdate()) - to_days(`lm`.`DateJoin`) AS `WorksDays`
from (`testwind`.`loaderman` `lm`
         left join `testwind`.`user` `us` on (`us`.`IdUser` = `lm`.`IdUser`))
union
select `us`.`IdUser`                                 AS `IdUser`,
       `us`.`Login`                                  AS `Login`,
       `bm`.`Name`                                   AS `Name`,
       `bm`.`SecondName`                             AS `SecondName`,
       `bm`.`MiddleName`                             AS `MiddleName`,
       `bm`.`Phone`                                  AS `Phone`,
       `bm`.`DateJoin`                               AS `DateJoin`,
       `bm`.`DateDismiss`                            AS `DateDismiss`,
       `bm`.`IdBarMan`                               AS `ID_WORKER`,
       to_days(curdate()) - to_days(`bm`.`DateJoin`) AS `WorksDays`
from (`testwind`.`barman` `bm`
         left join `testwind`.`user` `us` on (`us`.`IdUser` = `bm`.`IdUser`))
union
select `us`.`IdUser`                                 AS `IdUser`,
       `us`.`Login`                                  AS `Login`,
       `sm`.`Name`                                   AS `Name`,
       `sm`.`SecondName`                             AS `SecondName`,
       `sm`.`MiddleName`                             AS `MiddleName`,
       `sm`.`Phone`                                  AS `Phone`,
       `sm`.`DateJoin`                               AS `DateJoin`,
       `sm`.`DateDismiss`                            AS `DateDismiss`,
       `sm`.`IdStaffManager`                         AS `IdStaffManager`,
       to_days(curdate()) - to_days(`sm`.`DateJoin`) AS `WorksDays`
from (`testwind`.`staffmanager` `sm`
         left join `testwind`.`user` `us` on (`us`.`IdUser` = `sm`.`IdUser`))
union
select `us`.`IdUser`                                 AS `IdUser`,
       `us`.`Login`                                  AS `Login`,
       `mn`.`Name`                                   AS `Name`,
       `mn`.`SecondName`                             AS `SecondName`,
       `mn`.`MiddleName`                             AS `MiddleName`,
       `mn`.`Phone`                                  AS `Phone`,
       `mn`.`DateJoin`                               AS `DateJoin`,
       `mn`.`DateDismiss`                            AS `DateDismiss`,
       `mn`.`IdManager`                              AS `ID_WORKER`,
       to_days(curdate()) - to_days(`mn`.`DateJoin`) AS `WorksDays`
from (`testwind`.`manager` `mn`
         left join `testwind`.`user` `us` on (`us`.`IdUser` = `mn`.`IdUser`));

INSERT INTO testwind.workers (IdUser, Login, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, ID_WORKER, WorksDays) VALUES (1, 'w_eng1', 'Alexey', 'Novikov', 'Igorevich', '8(952)344-24-15', '2020-12-01', null, 1, 14);
INSERT INTO testwind.workers (IdUser, Login, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, ID_WORKER, WorksDays) VALUES (2, 'w_eng2', 'Anton', 'Ptushkin', 'Olegovich', '8(911)234-123-33', '2020-12-02', null, 2, 13);
INSERT INTO testwind.workers (IdUser, Login, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, ID_WORKER, WorksDays) VALUES (5, 'w_load1', 'Grih', 'Tolstik', 'Nikolaevuch', '8(921)-765-67-87', '2019-05-06', null, 1, 589);
INSERT INTO testwind.workers (IdUser, Login, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, ID_WORKER, WorksDays) VALUES (4, 'w_bar1', 'Artur', 'Mkrtchan', 'Papikovich', '8(951)567-65-32', '2019-04-03', null, 1, 622);
INSERT INTO testwind.workers (IdUser, Login, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, ID_WORKER, WorksDays) VALUES (null, null, 'f', 'f', 'f', 'f', '2020-12-11', null, 3, 4);
INSERT INTO testwind.workers (IdUser, Login, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, ID_WORKER, WorksDays) VALUES (null, null, 'a', 'f', 'f', 'f', '2020-12-10', '2020-12-15', 4, 5);
INSERT INTO testwind.workers (IdUser, Login, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, ID_WORKER, WorksDays) VALUES (50, 'aaa', 'f', 'a', 'f', 'f', '2020-12-09', null, 5, 6);
INSERT INTO testwind.workers (IdUser, Login, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, ID_WORKER, WorksDays) VALUES (51, 'aaaa', 'f', 'f', 'a', 'f', '2020-12-08', null, 6, 7);
INSERT INTO testwind.workers (IdUser, Login, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, ID_WORKER, WorksDays) VALUES (6, 'w_man2', 'Alexandr', 'Shpak', 'Kringevich', '8(921)-924-09-43', '2019-01-01', null, 1, 714);
INSERT INTO testwind.workers (IdUser, Login, Name, SecondName, MiddleName, Phone, DateJoin, DateDismiss, ID_WORKER, WorksDays) VALUES (null, null, 'Alisher', 'Morgen', 'Tagirovich', '8(999)-945-99-45', '2019-02-02', '2020-12-15', 2, 682);