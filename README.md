# 💸 EMI Calculator - Java Web Application

A user-friendly web application that calculates monthly EMI (Equated Monthly Installment) for loans using Java, JSP, Servlets, MySQL, and deployed using Render.

---

## 📌 Features

- 📥 Enter loan amount, interest rate, and loan tenure
- ⚡ Calculates:
  - Monthly EMI
  - Total interest payable
- 📊 Stores all applications in MySQL database
- 📄 Displays results in a styled results page
- 🌐 Deployable using Render with Docker

---

## 🛠️ Tech Stack

- **Frontend**: HTML, CSS, JavaScript
- **Backend**: Java, Servlets, JSP
- **Database**: MySQL
- **Build Tool**: Maven
- **Deployment**: GitHub + Render (Docker-based)

---

## 🚀 How to Run Locally

### 1. Clone the Repository

```bash
git clone https://github.com/taheer007/emi-calculator.git
cd emi-calculator

### 2. Setup MySQL Database
Copy
Edit
CREATE DATABASE emi_calculator;

CREATE TABLE loan_applications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    loan_amount DOUBLE,
    time_duration INT,
    total_interest DOUBLE,
    emi DOUBLE
);

## 3. Configure Database in LoanServlet.java
java
Copy
Edit
Connection conn = DriverManager.getConnection(
  "jdbc:mysql://localhost:3306/emi_calculator", "root", "your_password");

## 4. Build the Project
bash
Copy
Edit
mvn clean install
This generates a .war file in the target/ directory.

## 5. Deploy to Apache Tomcat
Copy the .war file into webapps/ folder of your local Tomcat

Start Tomcat server

Access at: http://localhost:8080/emi-calculator/
