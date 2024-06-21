-- User table
CREATE TABLE users (  -- Changed from "user" to "users"
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Expense table
CREATE TABLE expense (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(255),
    amount DOUBLE,
    date DATE,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Income table
CREATE TABLE income (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    source VARCHAR(255),
    amount DOUBLE,
    date DATE,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Investment table
CREATE TABLE investment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255),
    name VARCHAR(255),
    amount FLOAT,
    purchase_date DATE,
    current_value FLOAT,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- SavingsGoal table
CREATE TABLE savings_goal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    target_amount FLOAT,
    current_amount FLOAT,
    target_date DATE,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);