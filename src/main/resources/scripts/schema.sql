drop schema if exists asd_service cascade;
create schema if not exists asd_service;
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;


create table asd_service.owner_type
(
    id        bigint     not null,
    name_type varchar(6) not null, -- AREA || PERSON
    constraint pk_id_owner_type primary key (id),
    constraint natural_key unique (name_type)
);

create table asd_service.owner
(
    id            bigint not null,
    id_owner_type bigint not null,
    foreign key (id_owner_type) references asd_service.owner_type (id),
    primary key (id)
);

create table asd_service.actives
(
    id              bigint         not null,
    id_owner        bigint         not null,
    active_name     varchar(25)    not null,
    description     varchar(50)    not null,
    active_type     varchar(20)    not null,
    internal_serial varchar(25)    not null,
    active_weight   int            not null,
    active_height   int            not null,
    active_length   int            not null,
    stock_value     numeric(25, 2) not null,
    purchase_date   timestamp      not null,
    constraint unique_internal_serial unique (internal_serial),
    constraint fk_active_per_owner foreign key (id_owner) references asd_service.owner (id),
    constraint pk_active primary key (id)
);


create table asd_service.persons
(
    id          bigint      not null,
    person_name varchar(25) not null,
    constraint fk_person_per_owner foreign key (id) references asd_service.owner (id),
    constraint pk_person primary key (id)
);

create table asd_service.areas
(
    id        bigint     not null,
    area_name varchar(25) not null,
    constraint fk_area_per_owner foreign key (id) references asd_service.owner (id),
    constraint pk_areas primary key (id)

);

create table asd_service.cities
(
    id        bigint      not null,
    city_name varchar(30) not null,
    primary key (id)
);

create table asd_service.areas_cities
(
    id_city bigint not null,
    id_area bigint not null,
    primary key (id_city, id_area)
);

alter table asd_service.areas_cities
    add constraint fk_city
        foreign key (id_city) references asd_service.cities (id);

alter table asd_service.areas_cities
    add constraint fk_area
        foreign key (id_area) references asd_service.areas (id);
