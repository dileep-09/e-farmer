package com.buyer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class BuyerAccount
 */
@WebServlet("/BuyerAccount")
public class BuyerAccount extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int buyerId=Integer.parseInt(request.getParameter("buyerId"));
		String fullname=request.getParameter("fullname");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		String uname=request.getParameter("uname");
		String upass=request.getParameter("upass");
		HttpSession hs=request.getSession();
		try {
			int addAdmin=DatabaseConnection.insertUpdateFromSqlQuery("insert into tblbuyer(id,full_name,email_id,mobile_no,address,uname,upass) values('"+buyerId+"','"+fullname+"','"+email+"','"+mobile+"','"+address+"','"+uname+"','"+upass+"')");
			if(addAdmin>0) {
				String message="Admin account create successfully.";
				hs.setAttribute("success", message);
				response.sendRedirect("buyer-account.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
