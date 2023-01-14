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
    price           int,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into products (title, price, category_id) values
('Dress', 9800, 1), ('Scarf', 3400, 1), ('Bag', 5500, 1);

CREATE TABLE orders (
    id              bigserial primary key,
    username        varchar(255) not null,
    total_price     int not null,
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
    price_per_product   int not null,
    price               int not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);