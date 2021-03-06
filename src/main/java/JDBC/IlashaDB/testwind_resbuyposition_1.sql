create table resbuyposition
(
    IdResBuyPosition int auto_increment
        primary key,
    Number           int not null,
    IdResBuy         int not null,
    IdResource       int not null,
    constraint ResBuyPosition_ibfk_1
        foreign key (IdResBuy) references resbuy (IdResBuy),
    constraint ResBuyPosition_ibfk_2
        foreign key (IdResource) references resourcestorage (IdResource)
);

create index IdResBuy
    on resbuyposition (IdResBuy);

create index IdResource
    on resbuyposition (IdResource);

INSERT INTO testwind.resbuyposition (IdResBuyPosition, Number, IdResBuy, IdResource) VALUES (1, 3, 3, 3);
INSERT INTO testwind.resbuyposition (IdResBuyPosition, Number, IdResBuy, IdResource) VALUES (2, 7, 3, 8);
INSERT INTO testwind.resbuyposition (IdResBuyPosition, Number, IdResBuy, IdResource) VALUES (4, 11, 4, 5);