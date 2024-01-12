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
 * Servlet implementation class AdminEditPesticides
 */
@WebServlet("/AdminEditPesticides")
public class AdminEditPesticides extends HttpServlet {
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pesticideId=Integer.parseInt(request.getParameter("pesticideId"));
		String pname=request.getParameter("pname");
		String description=request.getParameter("description");
		String costRange=request.getParameter("costRange");
		HttpSession hs=request.getSession();
		
		try{
			int editPesticide=DatabaseConnection.insertUpdateFromSqlQuery("update tblpesticide set pesticide_name='"+pname+"',pesticide_cost_range='"+costRange+"',description='"+description+"' where id='"+pesticideId+"'");
			if(editPesticide>0) {
				String message="Pesticide edited successfully.";
				hs.setAttribute("pesticide-edit", message);
				response.sendRedirect("admin-manage-pesticides.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
