-- TODO Task 3
create database practice1CSF;

use practice1CSF;


CREATE TABLE orderdetails(
	order_id VARCHAR(256),
    order_date Date,
    name VARCHAR(256),
    address VARCHAR(256),
    priority BOOLEAN,
    comments VARCHAR(256),
    CONSTRAINT pk_user_id PRIMARY KEY (order_id)
);

CREATE TABLE orderList(
	id INT NOT NULL auto_increment,
    order_id VARCHAR(256),
    product_id VARCHAR(256),
    name VARCHAR(256),
    quantity INT,
    price DECIMAL(10,2),
    CONSTRAINT pk_id PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (order_id) REFERENCES orderdetails (order_id)   ON DELETE CASCADE ON UPDATE CASCADE 
    
)
