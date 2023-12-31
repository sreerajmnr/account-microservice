INSERT INTO customers (name, email_id, phone_number) VALUES
  ('Søren', 's123@gmail.com', '4512345678');
INSERT INTO customers (name, email_id, phone_number) VALUES
  ('Brian', 'b123@gmail.com', '4587654321');

INSERT INTO transactions (txn_type, txn_date, txn_amount, txn_account) VALUES
  ('DEPOSIT', PARSEDATETIME('23-12-2023','dd-MM-yyyy'), 100, '123');
INSERT INTO transactions (txn_type, txn_date, txn_amount, txn_account) VALUES
  ('DEPOSIT', PARSEDATETIME('23-12-2023','dd-MM-yyyy'), 200, '123');
INSERT INTO transactions (txn_type, txn_date, txn_amount, txn_account) VALUES
  ('DEPOSIT', PARSEDATETIME('23-12-2023','dd-MM-yyyy'), 300, '123');
