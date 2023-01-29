create table categories(
    id      bigserial primary key,
    title   varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into categories (title) values ('Apparel'), ('Food');

create table products
(
    id              bigserial primary key,
    title           varchar(255),
    category_id     bigint references categories(id),
    price           numeric(8, 2),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into products (title, price, category_id) values
('Dress', 9800.00, 1),
('Scarf', 2000.00, 1),
('Shoes', 15300.00, 1),
('Skirt', 7800.00, 1),
('Blouse', 11200.00, 1),
('Milk', 90.00, 2),
('Bag', 5500.00, 1);

CREATE TABLE orders (
    id              bigserial primary key,
    username        varchar(255) not null,
    total_price     numeric(8, 2) not null,
    address         varchar(255),
    phone           varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp

);

CREATE TABLE orders_items (
    id                  bigserial primary key,
    product_id          bigint not null references products (id),
    order_id            bigint not null references orders (id),
    quantity            int not null,
    price_per_product   numeric(8, 2) not null,
    price               numeric(8, 2) not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);