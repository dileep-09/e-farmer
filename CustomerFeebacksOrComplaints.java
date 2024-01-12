package com.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class CustomerFeebacksOrComplaints
 */
@WebServlet("/CustomerFeebacksOrComplaints")
public class CustomerFeebacksOrComplaints extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		String cname = request.getParameter("cname");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		String feeback_complaint = request.getParameter("feedback");

		try {
			int addFeedback = DatabaseConnection.insertUpdateFromSqlQuery("insert into tblfeedback(id,customer_name,mobile,email,feedback_complaint_type,feedback_or_complaint)values('"+ id + "','" + cname + "','" + mobile + "','" + email + "','" + type + "','" + feeback_complaint + "')");
			if (addFeedback > 0) {
				response.sendRedirect("customer-feedback-or-complaints.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
