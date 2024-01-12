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
 * Servlet implementation class AdminDeletePesticides
 */
@WebServlet("/AdminDeletePesticides")
public class AdminDeletePesticides extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession hs=request.getSession();
		
		int deletePesticide=DatabaseConnection.insertUpdateFromSqlQuery("delete from tblpesticide where id='"+id+"'");
		if(deletePesticide>0) {
			String message="Pesticide deleted successfully.";
			hs.setAttribute("pesticide-delete", message);
			response.sendRedirect("admin-manage-pesticides.jsp");
		}
	}

}
