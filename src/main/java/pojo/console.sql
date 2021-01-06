create or replace table BeerStorage
(
    IdBeerKind int auto_increment
        primary key,
    Name       varchar(50) not null,
    Price      int         not null,
    Type       varchar(50) not null,
    Amount     int         not null,
    CookTime   int         not null,
    constraint Name
        unique (Name)
);

create or replace table ClientList
(
    IdClient         int auto_increment
        primary key,
    NameClient       varchar(50) not null,
    SecondNameClient varchar(50) not null,
    MiddleNameClient varchar(50) not null,
    PhoneClient      varchar(50) not null,
    Age              date        not null,
    DateJoin         date        not null,
    constraint PhoneClient
        unique (PhoneClient)
);

create or replace table ResourceStorage
(
    IdResource int auto_increment
        primary key,
    Name       varchar(50) not null,
    Amount     int         not null,
    Price      int         not null,
    Unit       varchar(50) not null,
    constraint Name
        unique (Name)
);

create or replace table RecipeList
(
    idRecipe   int auto_increment
        primary key,
    IdBeerKind int not null,
    IdResource int not null,
    Amount     int not null,
    constraint RecipeList_ibfk_1
        foreign key (IdBeerKind) references BeerStorage (IdBeerKind),
    constraint RecipeList_ibfk_2
        foreign key (IdResource) references ResourceStorage (IdResource)
);

create or replace index IdBeerKind
    on RecipeList (IdBeerKind);

create or replace index IdResource
    on RecipeList (IdResource);

create or replace table User
(
    IdUser   int auto_increment
        primary key,
    Login    varchar(30) not null,
    Password varchar(30) not null,
    Role     varchar(50) not null,
    constraint Login
        unique (Login)
);

create or replace table BarMan
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
    IdUser      int         not null,
    constraint BarMan_ibfk_1
        foreign key (IdUser) references User (IdUser)
);

create or replace index IdUser
    on BarMan (IdUser);

create or replace table LoaderMan
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
    IdUser      int         not null,
    constraint LoaderMan_ibfk_1
        foreign key (IdUser) references User (IdUser)
);

create or replace index IdUser
    on LoaderMan (IdUser);

create or replace table Manager
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
    IdUser      int         not null,
    constraint Manager_ibfk_1
        foreign key (IdUser) references User (IdUser)
);

create or replace table ImportAlcBuy
(
    IdImportAlcBuy int auto_increment
        primary key,
    IdBarman       int         not null,
    IdManager      int         not null,
    Date           date        not null,
    Status         varchar(50) not null,
    constraint ImportAlcBuy_ibfk_1
        foreign key (IdBarman) references BarMan (IdBarMan),
    constraint ImportAlcBuy_ibfk_2
        foreign key (IdManager) references Manager (IdManager)
);

create or replace index IdBarman
    on ImportAlcBuy (IdBarman);

create or replace index IdManager
    on ImportAlcBuy (IdManager);

create or replace table ImportAlcBuyPosition
(
    IdImportAlcPosition int auto_increment
        primary key,
    Number              int not null,
    IdImportAlcBuy      int not null,
    IdBeerKind          int not null,
    constraint ImportAlcBuyPosition_ibfk_1
        foreign key (IdImportAlcBuy) references ImportAlcBuy (IdImportAlcBuy),
    constraint ImportAlcBuyPosition_ibfk_2
        foreign key (IdBeerKind) references BeerStorage (IdBeerKind)
);

create or replace index IdBeerKind
    on ImportAlcBuyPosition (IdBeerKind);

create or replace index IdImportAlcBuy
    on ImportAlcBuyPosition (IdImportAlcBuy);

create or replace index IdUser
    on Manager (IdUser);

create or replace table Orders
(
    IdOrder   int auto_increment
        primary key,
    IdClient  int         not null,
    IdManager int         not null,
    Date      date        not null,
    Status    varchar(50) not null,
    constraint Orders_ibfk_1
        foreign key (IdClient) references ClientList (IdClient),
    constraint Orders_ibfk_2
        foreign key (IdManager) references Manager (IdManager)
);

create or replace table OrderPosition
(
    IdOrderPosition int auto_increment
        primary key,
    Number          int not null,
    IdOrder         int not null,
    IdBeerKind      int not null,
    constraint OrderPosition_ibfk_1
        foreign key (IdOrder) references Orders (IdOrder),
    constraint OrderPosition_ibfk_2
        foreign key (IdBeerKind) references BeerStorage (IdBeerKind)
);

create or replace index IdBeerKind
    on OrderPosition (IdBeerKind);

create or replace index IdOrder
    on OrderPosition (IdOrder);

create or replace index IdClient
    on Orders (IdClient);

create or replace index IdManager
    on Orders (IdManager);

create or replace table StaffManager
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
    IdUser         int         not null,
    constraint StaffManager_ibfk_1
        foreign key (IdUser) references User (IdUser)
);

create or replace index IdUser
    on StaffManager (IdUser);

create or replace table TechnologistEngineer
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
    IdUser                 int         not null,
    constraint TechnologistEngineer_ibfk_1
        foreign key (IdUser) references User (IdUser)
);

create or replace table ResBuy
(
    IdResBuy   int auto_increment
        primary key,
    IdEngineer int         not null,
    IdManager  int         not null,
    Date       date        not null,
    Status     varchar(50) not null,
    constraint ResBuy_ibfk_1
        foreign key (IdEngineer) references TechnologistEngineer (IdTechnologistEngineer),
    constraint ResBuy_ibfk_2
        foreign key (IdManager) references Manager (IdManager)
);

create or replace table LoaderTask
(
    IdLoaderTask int auto_increment
        primary key,
    IdLoaderMan  int         not null,
    IdResBuy     int         not null,
    IdImportAlc  int         not null,
    Date         date        not null,
    Status       varchar(50) not null,
    constraint LoaderTask_ibfk_1
        foreign key (IdLoaderMan) references LoaderMan (IdLoaderMan),
    constraint LoaderTask_ibfk_2
        foreign key (IdResBuy) references ResBuy (IdResBuy),
    constraint LoaderTask_ibfk_3
        foreign key (IdImportAlc) references ImportAlcBuy (IdImportAlcBuy)
);

create or replace index IdImportAlc
    on LoaderTask (IdImportAlc);

create or replace index IdLoaderMan
    on LoaderTask (IdLoaderMan);

create or replace index IdResBuy
    on LoaderTask (IdResBuy);

create or replace index IdEngineer
    on ResBuy (IdEngineer);

create or replace index IdManager
    on ResBuy (IdManager);

create or replace table ResBuyPosition
(
    IdResBuyPosition int auto_increment
        primary key,
    Number           int not null,
    IdResBuy         int not null,
    IdResource       int not null,
    constraint ResBuyPosition_ibfk_1
        foreign key (IdResBuy) references ResBuy (IdResBuy),
    constraint ResBuyPosition_ibfk_2
        foreign key (IdResource) references ResourceStorage (IdResource)
);

create or replace index IdResBuy
    on ResBuyPosition (IdResBuy);

create or replace index IdResource
    on ResBuyPosition (IdResource);

create or replace table Task
(
    IdTask                  int auto_increment
        primary key,
    IdTechnologicalEngineer int         not null,
    IdBeerKind              int         not null,
    Date                    date        not null,
    Status                  varchar(50) not null,
    Amount                  int         not null,
    constraint Task_ibfk_1
        foreign key (IdTechnologicalEngineer) references TechnologistEngineer (IdTechnologistEngineer),
    constraint Task_ibfk_2
        foreign key (IdBeerKind) references BeerStorage (IdBeerKind)
);

create or replace table CylindricallyConicalTank
(
    IdCCT     int auto_increment
        primary key,
    IdTask    int         not null,
    DateStart date        not null,
    DateEnd   date        null,
    StatusCCT varchar(20) not null,
    constraint CylindricallyConicalTank_ibfk_1
        foreign key (IdTask) references Task (IdTask)
);

create or replace index IdTask
    on CylindricallyConicalTank (IdTask);

create or replace index IdBeerKind
    on Task (IdBeerKind);

create or replace index IdTechnologicalEngineer
    on Task (IdTechnologicalEngineer);

create or replace index IdUser
    on TechnologistEngineer (IdUser);

create or replace
    definer = root@localhost procedure MyProcWithPar(IN p1 int, IN p2 int, OUT res int)
begin

    set res = (select sum(r.Amount - p1)
               from ResourceStorage r
               where r.IdResource = p2);

end;

create or replace
    definer = root@localhost procedure validateResources(IN p1 int, IN p2 int, OUT res int)
begin

    set res = (select sum(r.Amount - p1)
               from ResourceStorage r
               where r.IdResource = p2);

end;

INSERT INTO BeerStorage (IdBeerKind, Name, Price, Type, Amount, CookTime)
VALUES (1, 'Ale', 100, 'Our', 10, 50),
       (2, 'Lager', 120, 'Our', 2, 100),
       (3, 'VolkovskoeHoney', 150, 'Import', 4, 1),
       (4, 'GarageLemon', 80, 'Import', 3, 1),
       (5, 'GingerBeer', 90, 'Craft', 10, 30),
       (6, 'BavarianWheat', 125, 'Our', 2, 40);
INSERT INTO CylindricallyConicalTank (IdCCT, IdTask, DateStart, DateEnd, StatusCCT)
VALUES (1, 1, '1970-01-01', '1970-02-10', 'WORK'),
       (2, 1, '1970-01-01', NULL, 'FREE'),
       (3, 1, '1970-01-01', NULL, 'FREE');
INSERT INTO RecipeList (idRecipe, IdBeerKind, IdResource, Amount)
VALUES (1, 1, 1, 5),
       (2, 1, 2, 4),
       (3, 1, 3, 2),
       (4, 1, 8, 1),
       (5, 6, 1, 5),
       (6, 6, 2, 4),
       (7, 6, 6, 1),
       (8, 6, 7, 1),
       (9, 2, 1, 5),
       (10, 2, 2, 4),
       (11, 2, 5, 1);
INSERT INTO ResourceStorage (IdResource, Name, Amount, Price, Unit)
VALUES (1, 'Water', 1000, 1, 'liters'),
       (2, 'HotWater', 1000, 2, 'liters'),
       (3, 'Hop', 250, 4, 'piece'),
       (4, 'Sugar', 20000, 3, 'kg'),
       (5, 'Wheat', 1000, 2, 'kg'),
       (6, 'MaltPilsher', 200, 4, 'kg'),
       (7, 'MunichMalt', 150, 3, 'kg'),
       (8, 'Malt', 50, 1, 'kg'),
       (9, 'AscorbicAcid', 5, 1, 'kg');
INSERT INTO Task (IdTask, IdTechnologicalEngineer, IdBeerKind, Date, Status, Amount)
VALUES (1, 1, 1, '2020-01-14', 'Active', 3),
       (3, 2, 2, '2020-01-04', 'Done', 4),
       (4, 1, 6, '2020-01-15', 'Active', 5);
INSERT INTO TechnologistEngineer (IdTechnologistEngineer, Name, SecondName, MiddleName, Phone, DateJoin,
                                    DateDismiss, Salary, IdUser)
VALUES (1, 'Novikov', 'Alexey', 'Igorevich', '8(952)344-24-15', '2020-12-01', NULL, 20000, 1),
       (2, 'Ptushkin', 'Anton', 'Olegovich', '8(911)234-123-33', '2020-12-02', NULL, 20000, 2);
INSERT INTO User (IdUser, Login, Password, Role)
VALUES (1, 'w_eng1', 'd19lbmcx', 'engineer'),
       (2, 'w_eng2', 'd19lbmcy', 'engineer'),
       (3, 'w_man1', 'd19tYW4x', 'staff_manager'),
       (4, 'w_bar1', 'd19iYXIx', 'barman'),
       (5, 'w_load1', 'd19sb2FkMQ==', 'loader'),
       (6, 'w_man2', 'd19tYW4y', 'manager'),
       (7, 'w_staff1', 'd19zdGFmZjE=', 'staff_manager'),
       (8, 'w_man3', 'd19tYW4z', 'manager');

create procedure validateResources(IN p1 int, IN p2 varchar(50), OUT res int)
begin
    set res = (select sum(r.Amount - p1)
               from ResourceStorage r
               where r.Name = p2);

end;
set @num = 0;
call validateResources(300, 'Hop', @num);
select @num;

set @num = 0;
call validateResources(300, 'Water', @num);
select @num;


select sum(r.Amount - 100)
from ResourceStorage r
where r.Name = 'Hop'

create view BeerMenu as
select BeerStorage.Name, BeerStorage.Type, BeerStorage.Amount, BeerStorage.Price
from BeerStorage
where BeerStorage.Amount > 0;

select *
from BeerMenu

select *
from loadertask LT
where LT.IdLoaderMan = 1

select RS.Name, RP.Number, LT.Date
from ResBuyPosition RP
         inner join LoaderTask LT on RP.IdResBuy = LT.IdResBuy
         inner join ResourceStorage RS on RP.IdResource = RS.IdResource

select *
from TechnologistEngineer t
         inner join User on t.IdTechnologistEngineer = User.IdUser
where User.Login = 'w_eng1'



alter view Workers as
    select US.IdUser,
           US.Login,
           TE.Name,
           TE.SecondName,
           TE.MiddleName,
           TE.Phone,
           TE.DateJoin,
           TE.IdTechnologistEngineer as ID_WORKER
    from User US,
         TechnologistEngineer TE
    where TE.IdUser = US.IdUser
    union
    select US.IdUser,
           US.Login,
           LM.Name,
           LM.SecondName,
           LM.MiddleName,
           LM.Phone,
           LM.DateJoin,
           LM.IdLoaderMan as ID_WORKER
    from User US,
         LoaderMan LM
    where LM.IdUser = US.IdUser
    union
    select US.IdUser,
           US.Login,
           BM.Name,
           BM.SecondName,
           Bm.MiddleName,
           BM.Phone,
           BM.DateJoin,
           BM.IdBarMan as ID_WORKER
    from User US,
         BarMan BM
    where BM.IdUser = US.IdUser
    union
    select US.IdUser,
           US.Login,
           SM.Name,
           SM.SecondName,
           Sm.MiddleName,
           SM.Phone,
           SM.DateJoin,
           SM.IdStaffManager
    from User US,
         StaffManager SM
    where SM.IdUser = US.IdUser
    union
    select US.IdUser,
           US.Login,
           MN.Name,
           MN.SecondName,
           MN.MiddleName,
           MN.Phone,
           MN.DateJoin,
           MN.IdManager as ID_WORKER
    from User US,
         Manager MN
    where MN.IdUser = US.IdUser;

select *
from Workers WK
where WK.Login = 'w_eng2'

select *
from ResBuy RB
where RB.Status != 'system'
  and RB.IdResBuy = 3

select *
from ImportAlcBuy IAB
where IAB.Status != 'system'
  and IAB.IdImportAlcBuy = 2

select RB.IdResBuy, Name, RBP.Number, Date
from ResBuyPosition RBP
         inner join ResBuy RB on RBP.IdResBuy = RB.IdResBuy
         inner join ResourceStorage RS on RBP.IdResource = RS.IdResource
where RB.IdResBuy = 3
select I.IdImportAlcBuy, Name, IAB.Number, Date
from ImportAlcBuyPosition IAB
         inner join ImportAlcBuy I on IAB.IdImportAlcBuy = I.IdImportAlcBuy
         inner join beerstorage b on IAB.IdBeerKind = b.IdBeerKind
where I.IdImportAlcBuy = 2

UPDATE ResourceStorage
SET Amount=Amount - 2
WHERE Name = 'Hop'


create procedure changeResCount(IN p1 int, IN p2 varchar(50))
begin
    UPDATE ResourceStorage SET Amount=Amount + p1 WHERE Name = p2;

end;
create procedure changeBeerCount(IN p1 int, IN p2 varchar(50))
begin
    UPDATE BeerStorage SET Amount=Amount + p1 WHERE Name = p2;

end;
call changeResCount(2, 'Hop')
call changeBeerCount(2, 'Lager')
select * from ResourceStorage
select * from BeerStorage


create procedure changeResTaskStatus(IN p1 varchar(50), IN p2 int)
begin
    UPDATE ResBuy SET Status=p1 WHERE IdResBuy = p2;
    UPDATE LoaderTask SET Status=p1 WHERE IdResBuy = p2;

end;
call changeResTaskStatus('process',3)
































-0--------------

create or replace table BeerStorage
(
    IdBeerKind int auto_increment
        primary key,
    Name       varchar(50) not null,
    Price      int         not null,
    Type       varchar(50) not null,
    Amount     int         not null,
    CookTime   int         not null,
    constraint Name
        unique (Name)
);

create or replace table ClientList
(
    IdClient         int auto_increment
        primary key,
    NameClient       varchar(50) not null,
    SecondNameClient varchar(50) not null,
    MiddleNameClient varchar(50) not null,
    PhoneClient      varchar(50) not null,
    Age              date        not null,
    DateJoin         date        not null,
    constraint PhoneClient
        unique (PhoneClient)
);

create or replace table ResourceStorage
(
    IdResource int auto_increment
        primary key,
    Name       varchar(50) not null,
    Amount     int         not null,
    Price      int         not null,
    Unit       varchar(50) not null,
    constraint Name
        unique (Name)
);

create or replace table RecipeList
(
    idRecipe   int auto_increment
        primary key,
    IdBeerKind int not null,
    IdResource int not null,
    Amount     int not null,
    constraint RecipeList_ibfk_1
        foreign key (IdBeerKind) references BeerStorage (IdBeerKind),
    constraint RecipeList_ibfk_2
        foreign key (IdResource) references ResourceStorage (IdResource)
);

create or replace index IdBeerKind
    on RecipeList (IdBeerKind);

create or replace index IdResource
    on RecipeList (IdResource);

create or replace table User
(
    IdUser   int auto_increment
        primary key,
    Login    varchar(30) not null,
    Password varchar(30) not null,
    Role     varchar(50) not null,
    constraint Login
        unique (Login)
);

create or replace table BarMan
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
    IdUser      int         not null,
    constraint BarMan_ibfk_1
        foreign key (IdUser) references User (IdUser)
);

create or replace index IdUser
    on BarMan (IdUser);

create or replace table LoaderMan
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
    IdUser      int         not null,
    constraint LoaderMan_ibfk_1
        foreign key (IdUser) references User (IdUser)
);

create or replace index IdUser
    on LoaderMan (IdUser);

create or replace table Manager
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
    IdUser      int         not null,
    constraint Manager_ibfk_1
        foreign key (IdUser) references User (IdUser)
);

create or replace table ImportAlcBuy
(
    IdImportAlcBuy int auto_increment
        primary key,
    IdBarman       int         not null,
    IdManager      int         not null,
    Date           date        not null,
    Status         varchar(50) not null,
    constraint ImportAlcBuy_ibfk_1
        foreign key (IdBarman) references BarMan (IdBarMan),
    constraint ImportAlcBuy_ibfk_2
        foreign key (IdManager) references Manager (IdManager)
);

create or replace index IdBarman
    on ImportAlcBuy (IdBarman);

create or replace index IdManager
    on ImportAlcBuy (IdManager);

create or replace table ImportAlcBuyPosition
(
    IdImportAlcPosition int auto_increment
        primary key,
    Number              int not null,
    IdImportAlcBuy      int not null,
    IdBeerKind          int not null,
    constraint ImportAlcBuyPosition_ibfk_1
        foreign key (IdImportAlcBuy) references ImportAlcBuy (IdImportAlcBuy),
    constraint ImportAlcBuyPosition_ibfk_2
        foreign key (IdBeerKind) references BeerStorage (IdBeerKind)
);

create or replace index IdBeerKind
    on ImportAlcBuyPosition (IdBeerKind);

create or replace index IdImportAlcBuy
    on ImportAlcBuyPosition (IdImportAlcBuy);

create or replace index IdUser
    on Manager (IdUser);

create or replace table Orders
(
    IdOrder   int auto_increment
        primary key,
    IdClient  int         not null,
    IdManager int         not null,
    Date      date        not null,
    Status    varchar(50) not null,
    constraint Orders_ibfk_1
        foreign key (IdClient) references ClientList (IdClient),
    constraint Orders_ibfk_2
        foreign key (IdManager) references Manager (IdManager)
);

create or replace table OrderPosition
(
    IdOrderPosition int auto_increment
        primary key,
    Number          int not null,
    IdOrder         int not null,
    IdBeerKind      int not null,
    constraint OrderPosition_ibfk_1
        foreign key (IdOrder) references Orders (IdOrder),
    constraint OrderPosition_ibfk_2
        foreign key (IdBeerKind) references BeerStorage (IdBeerKind)
);

create or replace index IdBeerKind
    on OrderPosition (IdBeerKind);

create or replace index IdOrder
    on OrderPosition (IdOrder);

create or replace index IdClient
    on Orders (IdClient);

create or replace index IdManager
    on Orders (IdManager);

create or replace table StaffManager
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
    IdUser         int         not null,
    constraint StaffManager_ibfk_1
        foreign key (IdUser) references User (IdUser)
);

create or replace index IdUser
    on StaffManager (IdUser);

create or replace table TechnologistEngineer
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
    IdUser                 int         not null,
    constraint TechnologistEngineer_ibfk_1
        foreign key (IdUser) references User (IdUser)
);

create or replace table ResBuy
(
    IdResBuy   int auto_increment
        primary key,
    IdEngineer int         not null,
    IdManager  int         not null,
    Date       date        not null,
    Status     varchar(50) not null,
    constraint ResBuy_ibfk_1
        foreign key (IdEngineer) references TechnologistEngineer (IdTechnologistEngineer),
    constraint ResBuy_ibfk_2
        foreign key (IdManager) references Manager (IdManager)
);

create or replace table LoaderTask
(
    IdLoaderTask int auto_increment
        primary key,
    IdLoaderMan  int         not null,
    IdResBuy     int         not null,
    IdImportAlc  int         not null,
    Date         date        not null,
    Status       varchar(50) not null,
    constraint LoaderTask_ibfk_1
        foreign key (IdLoaderMan) references LoaderMan (IdLoaderMan),
    constraint LoaderTask_ibfk_2
        foreign key (IdResBuy) references ResBuy (IdResBuy),
    constraint LoaderTask_ibfk_3
        foreign key (IdImportAlc) references ImportAlcBuy (IdImportAlcBuy)
);

create or replace index IdImportAlc
    on LoaderTask (IdImportAlc);

create or replace index IdLoaderMan
    on LoaderTask (IdLoaderMan);

create or replace index IdResBuy
    on LoaderTask (IdResBuy);

create or replace index IdEngineer
    on ResBuy (IdEngineer);

create or replace index IdManager
    on ResBuy (IdManager);

create or replace table ResBuyPosition
(
    IdResBuyPosition int auto_increment
        primary key,
    Number           int not null,
    IdResBuy         int not null,
    IdResource       int not null,
    constraint ResBuyPosition_ibfk_1
        foreign key (IdResBuy) references ResBuy (IdResBuy),
    constraint ResBuyPosition_ibfk_2
        foreign key (IdResource) references ResourceStorage (IdResource)
);

create or replace index IdResBuy
    on ResBuyPosition (IdResBuy);

create or replace index IdResource
    on ResBuyPosition (IdResource);

create or replace table Task
(
    IdTask                  int auto_increment
        primary key,
    IdTechnologicalEngineer int         not null,
    IdBeerKind              int         not null,
    Date                    date        not null,
    Status                  varchar(50) not null,
    Amount                  int         not null,
    constraint Task_ibfk_1
        foreign key (IdTechnologicalEngineer) references TechnologistEngineer (IdTechnologistEngineer),
    constraint Task_ibfk_2
        foreign key (IdBeerKind) references BeerStorage (IdBeerKind)
);

create or replace table CylindricallyConicalTank
(
    IdCCT     int auto_increment
        primary key,
    IdTask    int         not null,
    DateStart date        not null,
    DateEnd   date        null,
    StatusCCT varchar(20) not null,
    constraint CylindricallyConicalTank_ibfk_1
        foreign key (IdTask) references Task (IdTask)
);

create or replace index IdTask
    on CylindricallyConicalTank (IdTask);

create or replace index IdBeerKind
    on Task (IdBeerKind);

create or replace index IdTechnologicalEngineer
    on Task (IdTechnologicalEngineer);

create or replace index IdUser
    on TechnologistEngineer (IdUser);

create or replace
    definer = root@localhost procedure MyProcWithPar(IN p1 int, IN p2 int, OUT res int)
begin

    set res = (select sum(r.Amount - p1)
               from ResourceStorage r
               where r.IdResource = p2);

end;

create or replace
    definer = root@localhost procedure validateResources(IN p1 int, IN p2 int, OUT res int)
begin

    set res = (select sum(r.Amount - p1)
               from ResourceStorage r
               where r.IdResource = p2);

end;

INSERT INTO BeerStorage (IdBeerKind, Name, Price, Type, Amount, CookTime)
VALUES (1, 'Ale', 100, 'Our', 10, 50),
       (2, 'Lager', 120, 'Our', 2, 100),
       (3, 'VolkovskoeHoney', 150, 'Import', 4, 1),
       (4, 'GarageLemon', 80, 'Import', 3, 1),
       (5, 'GingerBeer', 90, 'Craft', 10, 30),
       (6, 'BavarianWheat', 125, 'Our', 2, 40);
INSERT INTO CylindricallyConicalTank (IdCCT, IdTask, DateStart, DateEnd, StatusCCT)
VALUES (1, 1, '1970-01-01', '1970-02-10', 'WORK'),
       (2, 1, '1970-01-01', NULL, 'FREE'),
       (3, 1, '1970-01-01', NULL, 'FREE');
INSERT INTO RecipeList (idRecipe, IdBeerKind, IdResource, Amount)
VALUES (1, 1, 1, 5),
       (2, 1, 2, 4),
       (3, 1, 3, 2),
       (4, 1, 8, 1),
       (5, 6, 1, 5),
       (6, 6, 2, 4),
       (7, 6, 6, 1),
       (8, 6, 7, 1),
       (9, 2, 1, 5),
       (10, 2, 2, 4),
       (11, 2, 5, 1);
INSERT INTO ResourceStorage (IdResource, Name, Amount, Price, Unit)
VALUES (1, 'Water', 1000, 1, 'liters'),
       (2, 'HotWater', 1000, 2, 'liters'),
       (3, 'Hop', 250, 4, 'piece'),
       (4, 'Sugar', 20000, 3, 'kg'),
       (5, 'Wheat', 1000, 2, 'kg'),
       (6, 'MaltPilsher', 200, 4, 'kg'),
       (7, 'MunichMalt', 150, 3, 'kg'),
       (8, 'Malt', 50, 1, 'kg'),
       (9, 'AscorbicAcid', 5, 1, 'kg');
INSERT INTO Task (IdTask, IdTechnologicalEngineer, IdBeerKind, Date, Status, Amount)
VALUES (1, 1, 1, '2020-01-14', 'Active', 3),
       (3, 2, 2, '2020-01-04', 'Done', 4),
       (4, 1, 6, '2020-01-15', 'Active', 5);
INSERT INTO TechnologistEngineer (IdTechnologistEngineer, Name, SecondName, MiddleName, Phone, DateJoin,
                                    DateDismiss, Salary, IdUser)
VALUES (1, 'Novikov', 'Alexey', 'Igorevich', '8(952)344-24-15', '2020-12-01', NULL, 20000, 1),
       (2, 'Ptushkin', 'Anton', 'Olegovich', '8(911)234-123-33', '2020-12-02', NULL, 20000, 2);
INSERT INTO User (IdUser, Login, Password, Role)
VALUES (1, 'w_eng1', 'd19lbmcx', 'engineer'),
       (2, 'w_eng2', 'd19lbmcy', 'engineer'),
       (3, 'w_man1', 'd19tYW4x', 'staff_manager'),
       (4, 'w_bar1', 'd19iYXIx', 'barman'),
       (5, 'w_load1', 'd19sb2FkMQ==', 'loader'),
       (6, 'w_man2', 'd19tYW4y', 'manager'),
       (7, 'w_staff1', 'd19zdGFmZjE=', 'staff_manager'),
       (8, 'w_man3', 'd19tYW4z', 'manager');

create procedure validateResources(IN p1 int, IN p2 varchar(50), OUT res int)
begin
    set res = (select sum(r.Amount - p1)
               from ResourceStorage r
               where r.Name = p2);

end;
set @num = 0;
call validateResources(300, 'Hop', @num);
select @num;

set @num = 0;
call validateResources(300, 'Water', @num);
select @num;


select sum(r.Amount - 100)
from ResourceStorage r
where r.Name = 'Hop'

create view BeerMenu as
select BeerStorage.Name, BeerStorage.Type, BeerStorage.Amount, BeerStorage.Price
from BeerStorage
where BeerStorage.Amount > 0;

select *
from BeerMenu

select *
from loadertask LT
where LT.IdLoaderMan = 1

select RS.Name, RP.Number, LT.Date
from ResBuyPosition RP
         inner join LoaderTask LT on RP.IdResBuy = LT.IdResBuy
         inner join ResourceStorage RS on RP.IdResource = RS.IdResource

select *
from TechnologistEngineer t
         inner join User on t.IdTechnologistEngineer = User.IdUser
where User.Login = 'w_eng1'



alter view Workers as
    select US.IdUser,
           US.Login,
           TE.Name,
           TE.SecondName,
           TE.MiddleName,
           TE.Phone,
           TE.DateJoin,
           TE.IdTechnologistEngineer as ID_WORKER
    from User US,
         TechnologistEngineer TE
    where TE.IdUser = US.IdUser
    union
    select US.IdUser,
           US.Login,
           LM.Name,
           LM.SecondName,
           LM.MiddleName,
           LM.Phone,
           LM.DateJoin,
           LM.IdLoaderMan as ID_WORKER
    from User US,
         LoaderMan LM
    where LM.IdUser = US.IdUser
    union
    select US.IdUser,
           US.Login,
           BM.Name,
           BM.SecondName,
           Bm.MiddleName,
           BM.Phone,
           BM.DateJoin,
           BM.IdBarMan as ID_WORKER
    from User US,
         BarMan BM
    where BM.IdUser = US.IdUser
    union
    select US.IdUser,
           US.Login,
           SM.Name,
           SM.SecondName,
           Sm.MiddleName,
           SM.Phone,
           SM.DateJoin,
           SM.IdStaffManager
    from User US,
         StaffManager SM
    where SM.IdUser = US.IdUser
    union
    select US.IdUser,
           US.Login,
           MN.Name,
           MN.SecondName,
           MN.MiddleName,
           MN.Phone,
           MN.DateJoin,
           MN.IdManager as ID_WORKER
    from User US,
         Manager MN
    where MN.IdUser = US.IdUser;

select *
from Workers WK
where WK.Login = 'w_eng2'

select *
from ResBuy RB
where RB.Status != 'system'
  and RB.IdResBuy = 3

select *
from ImportAlcBuy IAB
where IAB.Status != 'system'
  and IAB.IdImportAlcBuy = 2

select RB.IdResBuy, Name, RBP.Number, Date
from ResBuyPosition RBP
         inner join ResBuy RB on RBP.IdResBuy = RB.IdResBuy
         inner join ResourceStorage RS on RBP.IdResource = RS.IdResource
where RB.IdResBuy = 3
select I.IdImportAlcBuy, Name, IAB.Number, Date
from ImportAlcBuyPosition IAB
         inner join ImportAlcBuy I on IAB.IdImportAlcBuy = I.IdImportAlcBuy
         inner join beerstorage b on IAB.IdBeerKind = b.IdBeerKind
where I.IdImportAlcBuy = 2

UPDATE ResourceStorage
SET Amount=Amount - 2
WHERE Name = 'Hop'


create procedure changeResCount(IN p1 int, IN p2 varchar(50))
begin
    UPDATE ResourceStorage SET Amount=Amount + p1 WHERE Name = p2;

end;
create procedure changeBeerCount(IN p1 int, IN p2 varchar(50))
begin
    UPDATE BeerStorage SET Amount=Amount + p1 WHERE Name = p2;

end;
call changeResCount(2, 'Hop')
call changeBeerCount(2, 'Lager')
select *
from ResourceStorage
select *
from BeerStorage
select *
from BeerStorage


create procedure changeResTaskStatus(IN p1 varchar(50), IN p2 int)
begin
    UPDATE ResBuy SET Status=p1 WHERE IdResBuy = p2;
    UPDATE LoaderTask SET Status=p1 WHERE IdResBuy = p2;

end;
create procedure changeAlcTaskStatus(IN p1 varchar(50), IN p2 int)
begin
    UPDATE ImportAlcBuy SET Status=p1 WHERE IdImportAlcBuy = p2;
    UPDATE LoaderTask SET Status=p1 WHERE IdImportAlc = p2;

end;
call changeResTaskStatus('process', 3)
call changeAlcTaskStatus('process', 2)

select *
from User US
where us.Login = 'w_eng12'

INSERT INTO ClientList (NameClient, SecondNameClient, MiddleNameClient, PhoneClient, Age, DateJoin, IdUser)
VALUES ('Boris3', 'Moiseev', 'Mihailovich', '8(954)902-52-87', '1992-05-15', '2019-07-06', '3');

INSERT INTO User (Login, Password, Role)
VALUES ('w_test', 'dad', 'client')

select IdUser
from User
where Login = 'w_eng1'
create procedure deleteUserByLogin(IN p1 varchar(50))
begin
    DELETE FROM User WHERE Login = p1;

end;
DELETE
FROM User
where IdUser =

call deleteUserByLogin('w_del2')

CREATE TRIGGER deleteClient
    AFTER DELETE
    on ClientList
    FOR EACH ROW
BEGIN
    DELETE
    FROM User
    WHERE User.IdUser = old.IdUser;
END;

select NameClient, SecondNameClient, MiddleNameClient, PhoneClient, Age
from ClientList CL
         inner join User US on CL.IdUser = US.IdUser
where US.Login = 'a'

set @num = 0;
call validateResources(300, 'Hop', @num);
select @num;

set @id = 0;
SET @id = (select IdBeerKind
           from BeerStorage
           where Name = 'Ale');
select @id;

INSERT INTO OrderPosition (Number, IdOrder, IdBeerKind)
VALUES (4, 1, 2);

INSERT INTO Orders (IdClient, IdManager, Date, Status)
VALUES (6, 1, '2020-04-01', 'process');

select count(IdManager)
from manager

select IdOrder
from Orders
where IdClient = 34

select BS.Name, BS.Type, OP.Number, sum(BS.Price * OP.Number)
from OrderPosition OP
         inner join Orders O on O.IdOrder = OP.IdOrder
         inner join BeerStorage BS on OP.IdBeerKind = BS.IdBeerKind
where IdClient = 34
  and O.IdOrder = 8
group by BS.Name

select *
from ClientList

create procedure deleteClient(IN p1 varchar(50), p2 Date)
begin
    UPDATE ClientList
    SET DateDismiss= p2,
        IdUser     = null
    WHERE IdClient = p1;
end;
call deleteClient('35', '2020-01-01')
select Login
from User
         inner join clientlist c on User.IdUser = c.IdUser
where IdClient = 35

select *
from User
WHERE User.Login = 'aa';

select *
from User

delimiter //
