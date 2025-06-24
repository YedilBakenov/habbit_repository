INSERT INTO habbits(created_at, duration, name, updated_at)
VALUES ('2025-06-12', 1.30, 'smoking', '2025-06-12'),
       ('2025-06-12', 2.30, 'drinking', '2025-06-12');

INSERT INTO users(age, created_at, email, full_name, updated_at)
VALUES (20, '2025-06-12', 'serik@mail.ru', 'Serik Serikov', '2025-06-12'),
       (22, '2025-06-12', 'berik@mail.ru', 'Berik Berikov', '2025-06-12');

INSERT INTO money(country, type)
VALUES ('KAZAKHSTAN', 'tenge'),
       ('RUSSIA', 'rubl');

INSERT INTO habbits_users(habbit_id, users_id)
VALUES (1, 1),
       (2, 2);