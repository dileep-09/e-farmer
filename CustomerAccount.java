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
 * Servlet implementation class CustomerAccount
 */
@WebServlet("/CustomerAccount")
public class CustomerAccount extends HttpServlet {
	
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
		String uname=request.getParameter("uname");
		String upass=request.getParameter("upass");
		HttpSession hs=request.getSession();
		
		try {
			int addCustomer=DatabaseConnection.insertUpdateFromSqlQuery("insert into tblcustomer(id,full_name,email_id,mobile_no,gender,city,state,country,pincode,uname,upass) values('"+customerId+"','"+cname+"','"+email+"','"+mobile+"','"+gender+"','"+city+"','"+state+"','"+country+"','"+pincode+"','"+uname+"','"+upass+"')");
			if(addCustomer>0) {
				String message="Customer account create successfully.";
				hs.setAttribute("success", message);
				response.sendRedirect("customer-account.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
