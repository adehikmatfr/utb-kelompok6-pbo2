CREATE TABLE feedbacks (
    id SERIAL PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    feedback_date TIMESTAMP NOT NULL,
    feedback_text TEXT,
    rating INT
);
