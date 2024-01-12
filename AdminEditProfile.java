package com.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class AdminEditProfile
 */
@WebServlet("/AdminEditProfile")
public class AdminEditProfile extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int adminId=Integer.parseInt(request.getParameter("adminId"));
		String fname=request.getParameter("fname");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		HttpSession session=request.getSession();
		try {
			int editProfile=DatabaseConnection.insertUpdateFromSqlQuery("update tbladmin set full_name='"+fname+"',mobile_no='"+mobile+"',address='"+address+"' where uname='"+session.getAttribute("uname")+"' and id='"+adminId+"'");
			if(editProfile>0) {
				String message="Profile updated successfully.";
				session.setAttribute("profile-update", message);
				response.sendRedirect("admin-view-profile.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
