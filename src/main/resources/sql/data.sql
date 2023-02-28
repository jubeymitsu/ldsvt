CREATE TABLE customers(
    id SERIAL,
    name VARCHAR(15) NOT NULL,
    phone_number VARCHAR(13) NOT NULL PRIMARY KEY,
    discount int NOT NULL,
    number_of_purchases int,
    total_amount int,
    CHECK (discount IN (3, 5, 7, 10, 15, 20)),
    CHECK(number_of_purchases >= 0),
    CHECK(total_amount >=0)
);

INSERT INTO customers(name, phone_number, discount) VALUES('', '', );

UPDATE customers SET (name, phone_number, discount) = ('Max', '+79216059940', 5) WHERE id = 17;