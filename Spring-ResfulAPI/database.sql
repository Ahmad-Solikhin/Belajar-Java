create table users
(
    username        varchar(100) not null,
    password        varchar(100) not null,
    name            varchar(100) not null,
    token           varchar(100) default null,
    token_expire_at bigint       default null,
    primary key (username),
    unique (token)
) engine InnoDB;

select *
from users;

create table contacts
(
    id         varchar(100) not null,
    usename    varchar(100) not null,
    first_name varchar(100) not null,
    last_name  varchar(100) default null,
    phone      varchar(100) default null,
    email      varchar(100) default null,
    primary key (id),
    foreign key fk_users_contacts (usename) references users (username)
) engine innodb;

select *
from contacts;

create table addresses
(
    id          varchar(100) not null,
    contact_id  varchar(100) not null,
    street      varchar(255) default null,
    city        varchar(100) default null,
    province    varchar(100) default null,
    country     varchar(100) not null,
    postal_code varchar(100) default null,
    primary key (id),
    foreign key fk_contacts_addresses (contact_id) references contacts (id)
) engine innodb;

select *
from addresses;

delete from users where username = 'asgr39';