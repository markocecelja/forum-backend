INSERT INTO role (name, created_date_time, updated_date_time)
VALUES ('ADMIN', '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       ('REGULAR', '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00');

INSERT INTO users (first_name, last_name, created_date_time, updated_date_time)
VALUES ('Ivan', 'Kovač', '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00'),
       ('Mišo', 'Franić', '2021-07-03 12:00:00.00', '2021-07-03 12:00:00.00');

INSERT INTO user_login (username, password, user_id, created_date_time, updated_date_time)
VALUES ('ikovac', '$2a$12$JB8fZ4Kojd6/H2U.SS9mq.1fjg3i3dgmmURGCH0Huw0LnO06P/fb2', 1, '2021-07-03 12:00:00.00',
        '2021-07-03 12:00:00.00'),
       ('mfranic', '$2a$12$d49jFDxeptjiWqWAaWMqfOBt5LHuHUgn0t0U0IlzR19p77nA2.xsG', 2, '2021-07-03 12:00:00.00',
        '2021-07-03 12:00:00.00');

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1),
       (2, 2);