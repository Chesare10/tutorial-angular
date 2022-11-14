INSERT INTO CATEGORY(id, name) VALUES (1, 'Eurogames');
INSERT INTO CATEGORY(id, name) VALUES (2, 'Ameritrash');
INSERT INTO CATEGORY(id, name) VALUES (3, 'Familiar');

INSERT INTO AUTHOR(id, name, nationality) VALUES (1, 'Alan R. Moon', 'US');
INSERT INTO AUTHOR(id, name, nationality) VALUES (2, 'Vital Lacerda', 'PT');
INSERT INTO AUTHOR(id, name, nationality) VALUES (3, 'Simone Luciani', 'IT');
INSERT INTO AUTHOR(id, name, nationality) VALUES (4, 'Perepau Llistosella', 'ES');
INSERT INTO AUTHOR(id, name, nationality) VALUES (5, 'Michael Kiesling', 'DE');
INSERT INTO AUTHOR(id, name, nationality) VALUES (6, 'Phil Walker-Harding', 'US');

INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (1, 'On Mars', '14', 1, 2);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (2, 'Aventureros al tren', '8', 3, 1);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (3, '1920: Wall Street', '12', 1, 4);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (4, 'Barrage', '14', 1, 3);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (5, 'Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (6, 'Azul', '8', 3, 5);

INSERT INTO CLIENT(id, name) VALUES (1, 'Jorge');
INSERT INTO CLIENT(id, name) VALUES (2, 'Cesar');
INSERT INTO CLIENT(id, name) VALUES (3, 'Javier');
INSERT INTO CLIENT(id, name) VALUES (4, 'Sergi');
INSERT INTO CLIENT(id, name) VALUES (5, 'Laura');
INSERT INTO CLIENT(id, name) VALUES (6, 'Ana');
INSERT INTO CLIENT(id, name) VALUES (7, 'Gabriela');

INSERT INTO LOAN(id, game_id, client_id, date_loan, date_return) VALUES (1,1,1,'2022-11-03', '2022-11-08');
INSERT INTO LOAN(id, game_id, client_id, date_loan, date_return) VALUES (2,6,3,'2022-08-30', '2022-09-12');
INSERT INTO LOAN(id, game_id, client_id, date_loan, date_return) VALUES (3,4,6,'2022-01-10', '2022-01-18');
INSERT INTO LOAN(id, game_id, client_id, date_loan, date_return) VALUES (4,5,1,'2022-12-01', '2022-12-14');
INSERT INTO LOAN(id, game_id, client_id, date_loan, date_return) VALUES (5,2,2,'2022-05-11', '2022-05-24');
INSERT INTO LOAN(id, game_id, client_id, date_loan, date_return) VALUES (6,4,4,'2022-06-09', '2022-06-17');
INSERT INTO LOAN(id, game_id, client_id, date_loan, date_return) VALUES (7,2,5,'2022-10-31', '2022-11-10');
INSERT INTO LOAN(id, game_id, client_id, date_loan, date_return) VALUES (8,3,7,'2022-11-03', '2022-11-04');