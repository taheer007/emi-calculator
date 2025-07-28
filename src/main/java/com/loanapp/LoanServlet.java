package com.loanapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoanServlet")  // Make sure this matches your form action
public class LoanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            double principal = Double.parseDouble(request.getParameter("loanAmount"));
            double annualRate = Double.parseDouble(request.getParameter("rate"));
            int tenureMonths = Integer.parseInt(request.getParameter("timeDuration"));

            double monthlyRate = annualRate / 1200.0;
            double emi = principal * monthlyRate * Math.pow(1 + monthlyRate, tenureMonths)
                        / (Math.pow(1 + monthlyRate, tenureMonths) - 1);
            double totalInterest = (emi * tenureMonths) - principal;

            // Store result in DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/emi_calculator", "root", "Taheer6364@#");

            String sql = "INSERT INTO loan_applications (loan_amount, time_duration, total_interest, emi) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, principal);
            stmt.setInt(2, tenureMonths);
            stmt.setDouble(3, totalInterest);
            stmt.setDouble(4, emi);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

            request.setAttribute("emi", String.format("%.2f", emi));
            request.getRequestDispatcher("result.jsp").forward(request, response); // changed to results.jsp

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid input or database error!");
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }
}
