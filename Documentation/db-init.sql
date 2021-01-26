create database inventory;
drop database inventory;
use inventory;


CREATE TABLE dummy (
    firstname VARCHAR(100),
    lastname VARCHAR(100)
);

insert into dummy values('ABC','Mukherjee');
insert into dummy values('XYZ','Mukherjee');

SELECT 
    *
FROM
    dummy;

CREATE TABLE customers (
    id INT(10) PRIMARY KEY,
    name VARCHAR(20),
    homephone VARCHAR(20),
    cellphone VARCHAR(20),
    workphone VARCHAR(20),
    street VARCHAR(20),
    city VARCHAR(20),
    state VARCHAR(20),
    zip VARCHAR(20)
);

alter table customers add constraint customer_pk primary key (id);

desc customers;

SELECT 
    *
FROM
    customers;

drop table customers;

CREATE TABLE stock_items (
    itemNumber INT(10) PRIMARY KEY,
    itemDescription VARCHAR(20),
    itemPrice DOUBLE(10 , 2 ),
    quantity INT(10),
    unit VARCHAR(20)
);


SELECT 
    *
FROM
    stock_items;
    
drop table stock_items;

CREATE TABLE purchase_orders (
    poNumber INT(10) PRIMARY KEY,
    orderDate DATE,
    shipDate DATE,
    customerId INT(10),
    FOREIGN KEY (customerId)
        REFERENCES customers (id)
);

SELECT 
    *
FROM
    purchase_orders;

drop table purchase_orders;


CREATE TABLE order_items (
    noOfItems INT(10),
    poNumber INT(20),
    itemNumber INT(20),
    FOREIGN KEY (poNumber)
        REFERENCES purchase_orders (poNumber),
    FOREIGN KEY (itemNumber)
        REFERENCES stock_items (itemNumber)
);

SELECT 
    *
FROM
    order_items;
    
SELECT 
    *
FROM
    order_items
WHERE
    poNumber = 1 AND itemnumber = 1;

drop table order_items;