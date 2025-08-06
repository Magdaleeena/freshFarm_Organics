DROP TABLE IF EXISTS inventory_packaging CASCADE;
DROP TABLE IF EXISTS supplier CASCADE;
DROP TABLE IF EXISTS inventory_food CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS customers CASCADE;

CREATE TABLE customers (
    customer_id BIGSERIAL PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    address_line1 VARCHAR(200),
    address_city VARCHAR(100),
    address_postcode VARCHAR(20),
    email_address VARCHAR(100),
    phone_number VARCHAR(20)
);

CREATE TABLE products (
    product_id BIGSERIAL PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    unit_value DECIMAL(10,2), -- numeric part
    unit_type VARCHAR(20), -- kg, litre, box, bottle,
    category VARCHAR(50), -- fruits, vegetable, dairy, package_box
    description TEXT,
    image_url VARCHAR(255)
);

CREATE TABLE orders (
    order_id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    order_date DATE NOT NULL,
    order_items JSON NOT NULL,  -- e.g. [{"product_id":1,"qty":2},{"product_id":3,"qty":1}]
    total_amount DECIMAL(10,2) NOT NULL,
    delivery_status VARCHAR(20),
    tracking_number VARCHAR(50),
    delivery_date DATE,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id)
        REFERENCES customers(customer_id)
        ON DELETE CASCADE
);

CREATE TABLE inventory_food (
    inventory_food_id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    quantity_on_hand DECIMAL(10,2) NOT NULL CHECK (quantity_on_hand >= 0), -- represents the number of those units e.g. apples - total_items = 120;
    date_added DATE NOT NULL,
    expire_date DATE,
    storage_location VARCHAR(50),
    batch_number VARCHAR(50), -- e.g. APP-20250805-001 for apple
    CONSTRAINT fk_product_food FOREIGN KEY (product_id)
        REFERENCES products(product_id)
        ON DELETE CASCADE
);

CREATE TABLE supplier (
    supplier_id BIGSERIAL PRIMARY KEY,
    supplier_name VARCHAR(100) NOT NULL,
    contact_info TEXT,
    email_address VARCHAR(100)
);

CREATE TABLE inventory_packaging (
    inventory_packaging_id BIGSERIAL PRIMARY KEY,
    supplier_id BIGINT NULL,
    material_name VARCHAR(100) NOT NULL,
    storage_location VARCHAR(50),
    quantity INT NOT NULL,
    CONSTRAINT fk_supplier_packaging FOREIGN KEY (supplier_id)
        REFERENCES supplier(supplier_id)
        ON DELETE SET NULL
);
