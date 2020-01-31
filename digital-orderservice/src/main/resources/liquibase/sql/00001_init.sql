--liquibase formatted sql
--changeset vietnq:1
--comment Init database
create table shops(
    id serial,
    owner_id integer,
    name varchar(255),
    address varchar(255),
    lat real,
    lng real,
    created_at bigint,
    updated_at bigint,
    primary key(id)
);

create table products(
    id serial,
    shop_id integer,
    name varchar(255),
    price numeric,
    created_at bigint,
    updated_at bigint,
    primary key(id)
);
create table queues(
    id serial,
    queue_number integer,
    size integer,
    shop_id integer,
    end_index integer default 0,
    created_at bigint,
    updated_at bigint,
    primary key(id)
);
create table orders(
    id serial,
    customer_id integer,
    shop_id integer,
    product_id integer,
    queue_id integer,
    queue_number integer,
    amount numeric,
    position_code varchar(10),
    status varchar(10),
    created_at bigint,
    updated_at bigint,
    primary key(id)
);

--rollback set sql_safe_updates=0;
--rollback drop table orders;
--rollback drop table queues;
--rollback drop table products;
--rollback drop table shops;
--rollback set sql_safe_updates=1;