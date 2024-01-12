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
 * Servlet implementation class AdminDeleteCrops
 */
@WebServlet("/AdminDeleteCrops")
public class AdminDeleteCrops extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession hs=request.getSession();
		
		int deleteCrop=DatabaseConnection.insertUpdateFromSqlQuery("delete from tblcrops where id='"+id+"'");
		String message="Crop deleted.";
		hs.setAttribute("crop-delete", message);
		if(deleteCrop>0) {
			response.sendRedirect("admin-manage-crops.jsp");
		}
	}

}
