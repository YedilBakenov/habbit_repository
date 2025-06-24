CREATE TABLE IF NOT EXISTS habbits(
    id SERIAL PRIMARY KEY,
    duration numeric,
    name varchar(255),
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    age int,
    email varchar(255),
    full_name varchar(255),
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE IF NOT EXISTS habbits_users(
    habbit_id int,
    users_id int
);

CREATE TABLE IF NOT EXISTS money(
    id SERIAL PRIMARY KEY,
    country varchar(255),
    type varchar(255)
);