-- Criação do banco de dados
create database if not exists barberschedule;

-- Seleção do banco de dados
use barberschedule;

-- Exclusão das tabelas existentes, se houver
drop table if exists barber_service;
drop table if exists appointment;
drop table if exists service;
drop table if exists client;
drop table if exists barber;

-- Criação da tabela client
create table client (
    id int auto_increment primary key,
    name varchar(255) not null,
    email varchar(255) not null unique,
    phone varchar(50),
    password varchar(255) not null
);

-- Criação da tabela service
create table service (
    id int auto_increment primary key,
    name varchar(255) not null unique,
    price decimal(10, 2) not null,
    duration int not null
);

-- Criação da tabela barber
create table barber (
    id int auto_increment primary key,
    name varchar(255) not null,
    email varchar(255) not null unique,
    phone varchar(50),
    password varchar(255) not null
);

-- Criação da tabela appointment
create table appointment (
    id int auto_increment primary key,
    date date not null,
    time time not null,
    service_id int not null,
    client_id int not null,
    barber_id int not null,
    foreign key (service_id) references service(id) on delete cascade,
    foreign key (client_id) references client(id) on delete cascade,
    foreign key (barber_id) references barber(id) on delete cascade
);

-- Criação da tabela de relação many-to-many entre barber e service
create table barber_service (
    barber_id int not null,
    service_id int not null,
    primary key (barber_id, service_id),
    foreign key (barber_id) references barber(id) on delete cascade,
    foreign key (service_id) references service(id) on delete cascade
);
