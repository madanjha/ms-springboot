Create table IF NOT EXIST customer(
    'customer_id' int AUTO_INCREMENT PRIMARY KEY,
    'name' varchar(100) NOT NULL,
    'email' varchar(100) NOT NULL,
    'mobile_number' varchar(20) NOT NULL,
    'created_at' date NOT NULL,
    'created_by' varchar(20) NOT NULL,
    'update_at' date DEFAULT NULL,
    'update_by' varchar(20) DEFAULT NULL
);

Create table IF NOT EXIST customer(
    'customer_id' int NOT NULL,
    'account_number' int AUTO_INCREAMENT PRIMARY KEY,
    'account_type' varchar(100) NOT NULL,
    'branch_address' varchar(200) NOT NULL,
    'created_at' date NOT NULL,
    'created_by' varchar(20) NOT NULL,
    'update_at' date DEFAULT NULL,
    'update_by' varchar(20) DEFAULT NULL
);