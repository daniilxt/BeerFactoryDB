create table recipelist
(
    idRecipe   int auto_increment
        primary key,
    IdBeerKind int not null,
    IdResource int not null,
    Amount     int not null,
    constraint RecipeList_ibfk_1
        foreign key (IdBeerKind) references beerstorage (IdBeerKind),
    constraint RecipeList_ibfk_2
        foreign key (IdResource) references resourcestorage (IdResource)
);

create index IdBeerKind
    on recipelist (IdBeerKind);

create index IdResource
    on recipelist (IdResource);

INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (1, 1, 1, 5);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (2, 1, 2, 4);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (3, 1, 3, 2);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (4, 1, 8, 2);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (5, 6, 1, 5);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (6, 6, 2, 4);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (7, 6, 6, 1);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (8, 6, 7, 2);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (9, 2, 1, 5);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (10, 2, 2, 4);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (11, 2, 5, 1);