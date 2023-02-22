CREATE TABLE customers(
    id SERIAL PRIMARY KEY,
    name VARCHAR(15) NOT NULL,
    tel_number VARCHAR(13) NOT NULL,
    discount int NOT NULL,
    number_of_purchases int,
    total_amount int,
    CHECK (discount IN (3, 5, 7, 10, 15, 20)),
    CHECK(number_of_purchases >= 0),
    CHECK(total_amount >=0)
);