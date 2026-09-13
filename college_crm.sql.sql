USE college_crm;

DROP TABLE IF EXISTS fees;
DROP TABLE IF EXISTS faculty;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS courses;

CREATE TABLE courses (
  course_id INT AUTO_INCREMENT PRIMARY KEY,
  course_name VARCHAR(100),
  course_code VARCHAR(20) UNIQUE,
  duration VARCHAR(50),
  total_fees INT,
  department VARCHAR(50)
);
DELETE FROM courses;
ALTER TABLE courses AUTO_INCREMENT = 1;

CREATE TABLE students (
  student_id INT AUTO_INCREMENT PRIMARY KEY,
  student_name VARCHAR(100),
  roll_no VARCHAR(20) UNIQUE,
  course VARCHAR(50),
  mobile VARCHAR(15),
  branch VARCHAR(50)
);

CREATE TABLE faculty (
  faculty_id INT AUTO_INCREMENT PRIMARY KEY,
  faculty_name VARCHAR(100),
  faculty_code VARCHAR(20) UNIQUE,
  department VARCHAR(50),
  qualification VARCHAR(100),
  mobile VARCHAR(15)
);

CREATE TABLE fees (
  fee_id INT AUTO_INCREMENT PRIMARY KEY,
  roll_no VARCHAR(20),
  student_name VARCHAR(100),
  course VARCHAR(50),
  total_fees INT,
  paid_amount INT,
  due_amount INT
);
CREATE TABLE enquiry (
  enquiry_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  mobile VARCHAR(15),
  course_interested VARCHAR(50),
  enquiry_date DATE
);

CREATE TABLE leads (
  lead_id INT AUTO_INCREMENT PRIMARY KEY,
  lead_name VARCHAR(100),
  source VARCHAR(50),
  status VARCHAR(20)
);


USE college_crm;
DROP TABLE IF EXISTS attendance;
CREATE TABLE attendance (
  attendance_id INT AUTO_INCREMENT PRIMARY KEY,
  roll_no VARCHAR(50),
  student_name VARCHAR(100),
  att_date DATE,
  status VARCHAR(20)
);

USE college_crm;

DROP TABLE IF EXISTS attendance;

CREATE TABLE attendance (
  attendance_id INT AUTO_INCREMENT PRIMARY KEY,
  roll_no VARCHAR(50),
  student_name VARCHAR(100),
  att_date DATE,
  status VARCHAR(20)
);

INSERT INTO attendance (roll_no, student_name, att_date, status) VALUES ('BCA001', 'Manglesh Tiwari', '2026-09-13', 'Present');

SELECT * FROM attendance ORDER BY attendance_id ASC;
INSERT INTO courses (course_name, course_code, duration, total_fees, department) VALUES 
('B.Tech CSE', 'BTCS001', '4 Years', 75000, 'CSE'),
('BCA', 'BCA23001', '3 Years', 40000, 'Computer');

INSERT INTO students (student_name, roll_no, course, mobile, branch) VALUES 
('Aman Yadav', 'BCA001', 'BCA', '9876543210', 'BCA'),
('Rahul Kumar', 'MCA001', 'MCA', '9876543211', 'MCA'),
('Priya Singh', 'BCOM001', 'BCOM', '9876543212', 'Commerce');

INSERT INTO faculty (faculty_name, faculty_code, department, qualification, mobile) VALUES 
('Dr. Sitaram', 'FAC001', 'CSE', 'M.Tech PhD', '9876543213');

INSERT INTO fees (roll_no, student_name, course, total_fees, paid_amount, due_amount) VALUES 
('BCA23002', 'Aman Kumar', 'BCA', 40000, 25000, 15000);

SELECT 'DATABASE READY FOR MANGLESH TIWARI' AS MESSAGE;