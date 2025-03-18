-- TODO Task
drop database batch4_csf;

create database batch4_csf;
use batch4_csf;

create table orders(
	id varchar(256),
    order_date date,
    name varchar(256),
    address varchar(256),
    comments varchar(256),
    constraint pk_order_id primary key(id)
);

create table orderItems(
	id varchar(256),
    order_id varchar(256),
    name varchar(256),
    quantity int,
    price decimal(10,2),
    constraint pk_product_id primary key(id),
    constraint fk_order_id FOREIGN KEY (order_id) REFERENCES orders(id)
    ON DELETE CASCADE   
    ON UPDATE CASCADE 
    
    
    
)

SELECT * FROM orders;
SELECT * FROM orderItems;
