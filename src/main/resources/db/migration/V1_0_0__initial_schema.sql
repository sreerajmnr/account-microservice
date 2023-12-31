DROP TABLE IF EXISTS ACCOUNTS;

CREATE TABLE customers (
  customer_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  email_id VARCHAR(50) NOT NULL,
  phone_number VARCHAR(50) NOT NULL
);

CREATE TABLE accounts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  account_number VARCHAR(50) NOT NULL,
  branch_code VARCHAR(50) NOT NULL,
  account_type VARCHAR(50) NOT NULL,
  account_status VARCHAR(50) NOT NULL,
  balance NUMERIC(20, 2) NOT NULL,
  customer_id INT NOT NULL
);

CREATE TABLE transactions (
  txn_id INT AUTO_INCREMENT PRIMARY KEY,
  txn_type VARCHAR(50) NOT NULL,
  txn_date DATE NOT NULL,
  txn_amount NUMERIC(20, 2) NOT NULL,
  txn_account VARCHAR(50) NOT NULL
);