CREATE TABLE products(
  id int(11) NOT NULL AUTO_INCREMENT,
  description varchar(255) NOT NULL,
  stock_quantity bigint(20) NOT NULL,
  ean varchar(255) NOT NULL,
  sku varchar(255) NOT NULL,
  price float NOT NULL,
  status int NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE sales (
  id int(11) NOT NULL AUTO_INCREMENT,
  ean String NOT NULL,
  price float NOT NULL,
  total_price float NOT NULL,
  discount double NOT NULL,
  total_quantity int(11) NOT NULL,
  sale_datetime datetime NOT NULL,
  payment_method varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE sale_product (
  id int(11) NOT NULL AUTO_INCREMENT,
  product_id int(11) NOT NULL,
  sale_id int(11) NOT NULL,
  product_quantity int(11) NOT NULL,
  product_price float NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE sales
  ADD CONSTRAINT sale_ibfk_1 FOREIGN KEY (ean) REFERENCES products (ean);

ALTER TABLE sale_product
  ADD CONSTRAINT sale_product_ibfk_1 FOREIGN KEY (product_id) REFERENCES products (id),
  ADD CONSTRAINT sale_product_ibfk_2 FOREIGN KEY (sale_id) REFERENCES sales (id);

