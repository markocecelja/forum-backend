INSERT INTO role (name, created_date_time, updated_date_time)
VALUES ('ADMIN', '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       ('REGULAR', '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00');

INSERT INTO vote_type (name, created_date_time, updated_date_time)
VALUES ('UP', '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       ('DOWN', '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00');

INSERT INTO users (email, created_date_time, updated_date_time, deleted_date_time)
VALUES ('ikovac@mail.com', '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00', null),
       ('mfranic@mail.com', '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00', null),
       ('obrisanikorisnik@mail.com', '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00');

INSERT INTO user_login (username, password, user_id, created_date_time, updated_date_time, deleted_date_time)
VALUES ('ikovac', '$2a$12$JB8fZ4Kojd6/H2U.SS9mq.1fjg3i3dgmmURGCH0Huw0LnO06P/fb2', 1, '2021-07-03 12:00:00.00',
        '2021-07-03 12:00:00.00', null),
       ('mfranic', '$2a$12$d49jFDxeptjiWqWAaWMqfOBt5LHuHUgn0t0U0IlzR19p77nA2.xsG', 2, '2021-07-03 12:00:00.00',
        '2021-07-03 12:00:00.00', null),
       ('obrisanikorisnik', '$2a$12$0gxN.BHLS2Lk6YUtONX5COwOgyY0Wr6YSE8.WMKHu20e3wHAg42om', 3, '2021-07-03 12:00:00.00',
        '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00');

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 2);

INSERT INTO topic (title, description, created_by_id, created_date_time, updated_date_time)
VALUES ('Prva testna tema', 'Opis prve testne teme', 1, '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       ('Druga testna tema', 'Opis druge testne teme', 2, '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00');

INSERT INTO comment (content, topic_id, created_by_id, created_date_time, updated_date_time)
VALUES ('Prvi komentar u prvoj temi', 1, 1, '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       ('Drugi komentar u prvoj temi', 1, 2, '2021-07-04 12:00:00.00', '2021-07-03 12:00:00.00'),
       ('Treći komentar u prvoj temi', 1, 2, '2021-07-05 12:00:00.00', '2021-07-03 12:00:00.00'),
       ('Četvrti komentar u prvoj temi', 1, 2, '2021-07-06 12:00:00.00', '2021-07-03 12:00:00.00'),
       ('Peti komentar u prvoj temi', 1, 2, '2021-07-07 12:00:00.00', '2021-07-03 12:00:00.00'),
       ('Šesti komentar u prvoj temi', 1, 2, '2021-07-07 12:00:00.00', '2021-07-03 12:00:00.00'),
       ('Sedmi komentar u prvoj temi', 1, 2, '2021-07-07 12:00:00.00', '2021-07-03 12:00:00.00'),
       ('Prvi komentar u drugoj temi', 2, 1, '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       ('Drugi komentar u drugoj temi', 2, 2, '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       ('Komentar obrisanog korisnika', 1, 3, '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00');

INSERT INTO vote (comment_id, vote_type_id, created_by_id, created_date_time, updated_date_time)
VALUES (1, 1, 1, '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       (2, 2, 1, '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       (1, 1, 2, '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       (2, 1, 2, '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       (8, 1, 1, '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       (9, 2, 1, '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       (8, 2, 2, '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       (9, 2, 2, '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00');