create table products
(
    id bigserial primary key,
    title varchar(255),
    price int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
insert into products (title, price) values
('Dress', 9800), ('Scarf', 3400), ('Bag', 5500);

create table users (
    id         bigserial primary key,
    username   varchar(36) not null unique,
    password   varchar(80) not null,
    email      varchar(50) unique
);

create table roles (
    id         bigserial primary key,
    name       varchar(50) not null
);

CREATE TABLE users_roles (
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);