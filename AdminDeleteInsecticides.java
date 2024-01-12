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
 * Servlet implementation class AdminDeleteInsecticides
 */
@WebServlet("/AdminDeleteInsecticides")
public class AdminDeleteInsecticides extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession hs=request.getSession(); 
		
		int deleteInsecticides = DatabaseConnection.insertUpdateFromSqlQuery("delete from tblinsecticide where id='" + id + "'");
		String message="Insecticode deleted.";
		hs.setAttribute("insecticide-delete", message);
		if (deleteInsecticides > 0) {
			response.sendRedirect("admin-manage-insecticides.jsp");
		}

	}
}
