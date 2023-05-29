create table categories(
    id bigint not null AUTO_INCREMENT,
    name varchar(100) not null,
    primary key (id)
)engine = InnoDb;

select * from categories;

create table products(
    id bigint not null auto_increment,
    name varchar(100) not null,
    price bigint not null ,
    category_id bigint not null ,
    primary key (id),
    foreign key fk_products_categories (category_id) references categories (id)
)engine InnoDB;

select * from products;

delete from products where id = 3;

alter table categories
add column created_at timestamp, add column updated_at timestamp;
