INSERT INTO PRODUCT_STOCK (description,unit_price,stock) VALUES ('Lavarropa', 5500.75, 5);
INSERT INTO PRODUCT_STOCK (description,unit_price,stock) VALUES ('Televisor', 1500, 10);
INSERT INTO PRODUCT_STOCK (id,description,unit_price,stock) VALUES (3,'Pava', 500, 5);
INSERT INTO PRODUCT_STOCK (id,description,unit_price,stock) VALUES (4,'Notebook', 8000.63, 15);

INSERT INTO CART (full_name,email,creation_date,total,status) VALUES ('Pepe Gomez','pepe@gomez.com',parsedatetime('03-02-2020', 'dd-MM-yyyy'),0.0,0);
INSERT INTO CART (id,full_name,email,creation_date,total,status) VALUES (2,'Juan Perez','juan@perez.com',parsedatetime('31-12-2019', 'dd-MM-yyyy'),0.0,1);
INSERT INTO CART (id,full_name,email,creation_date,total,status) VALUES (3,'Pepe Gomez','pepe@gomez.com',parsedatetime('05-02-2020', 'dd-MM-yyyy'),8700.63,1);

INSERT INTO PRODUCT_CART (unit_price, quantity, cart_id,product_id) VALUES (1500,20,2,2);
INSERT INTO PRODUCT_CART (unit_price, quantity, cart_id,product_id) VALUES (350,2,3,3);
INSERT INTO PRODUCT_CART (unit_price, quantity, cart_id,product_id) VALUES (8000.63,1,3,4);