package com.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class CustomerEditProfile
 */
@WebServlet("/CustomerEditProfile")
public class CustomerEditProfile extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId=Integer.parseInt(request.getParameter("customerId"));
		String cname=request.getParameter("cname");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String gender=request.getParameter("gender");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		String pincode=request.getParameter("pincode");
		
		HttpSession session=request.getSession();
		try {
			int editProfile=DatabaseConnection.insertUpdateFromSqlQuery("update tblcustomer set full_name='"+cname+"',mobile_no='"+mobile+"',gender='"+gender+"',city='"+city+"',state='"+state+"',country='"+country+"',pincode='"+pincode+"' where uname='"+session.getAttribute("uname")+"' and id='"+customerId+"'");
			if(editProfile>0) {
				String message="Profile updated successfully.";
				session.setAttribute("profile-update", message);
				response.sendRedirect("customer-view-profile.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
