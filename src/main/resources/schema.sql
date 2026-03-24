-- =============================================
-- Student Management System - Database Schema
-- =============================================

-- Create database
CREATE DATABASE IF NOT EXISTS student_db;
USE student_db;

-- Create students table
CREATE TABLE IF NOT EXISTS students (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100)    NOT NULL,
    email       VARCHAR(150)    NOT NULL UNIQUE,
    phone       VARCHAR(15)     NOT NULL,
    course      VARCHAR(100)    NOT NULL,
    year        INT             NOT NULL CHECK (year BETWEEN 1 AND 4),
    cgpa        DECIMAL(3,1)    DEFAULT 0.0 CHECK (cgpa BETWEEN 0.0 AND 10.0),
    profile_image_url VARCHAR(500),
    created_at  TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO students (name, email, phone, course, year, cgpa) VALUES
('Dharma Naidu',    'dharma@example.com',   '9110584381', 'B.Tech CSE', 3, 8.5),
('Ravi Kumar',      'ravi@example.com',     '9876543210', 'B.Tech ECE', 2, 7.8),
('Priya Reddy',     'priya@example.com',    '9123456789', 'B.Tech IT',  4, 9.1),
('Arun Sharma',     'arun@example.com',     '9988776655', 'B.Tech CSE', 1, 8.0),
('Sneha Patel',     'sneha@example.com',    '9012345678', 'B.Tech ME',  3, 7.5);

-- Verify data
SELECT * FROM students;
