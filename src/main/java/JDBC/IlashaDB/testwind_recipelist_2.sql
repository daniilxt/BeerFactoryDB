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
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (12, 5, 1, 5);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (13, 5, 2, 4);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (14, 5, 9, 1);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (15, 5, 5, 3);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (16, 10, 10, 2);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (17, 10, 1, 5);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (18, 10, 2, 4);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (19, 10, 3, 2);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (20, 11, 1, 5);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (21, 11, 2, 4);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (22, 11, 5, 3);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (23, 11, 10, 1);
INSERT INTO testwind.recipelist (idRecipe, IdBeerKind, IdResource, Amount) VALUES (24, 1, 1, 5);