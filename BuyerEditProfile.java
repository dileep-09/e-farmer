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
 * Servlet implementation class BuyerEditProfile
 */
@WebServlet("/BuyerEditProfile")
public class BuyerEditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int buyerId=Integer.parseInt(request.getParameter("buyerId"));
		String fname=request.getParameter("fname");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");

		HttpSession session=request.getSession();
		try {
			int editProfile=DatabaseConnection.insertUpdateFromSqlQuery("update tblbuyer set full_name='"+fname+"',mobile_no='"+mobile+"',address='"+address+"' where uname='"+session.getAttribute("uname")+"'");
			if(editProfile>0) {
				String message="Profile updated successfully.";
				session.setAttribute("profile-update", message);
				response.sendRedirect("buyer-view-profile.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
