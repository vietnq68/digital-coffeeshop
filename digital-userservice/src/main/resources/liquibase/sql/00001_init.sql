--liquibase formatted sql
--changeset vietnq:1
--comment Init database
create table users(
    id serial,
    mobile_number varchar(10),
    full_name varchar(255),
    password varchar(255),
    type varchar(20),
    created_at bigint,
    updated_at bigint,
    primary key(id)
);