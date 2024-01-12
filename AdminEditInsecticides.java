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
 * Servlet implementation class AdminEditInsecticides
 */
@WebServlet("/AdminEditInsecticides")
public class AdminEditInsecticides extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int insectidesId=Integer.parseInt(request.getParameter("insectidesId"));
		String iname=request.getParameter("iname");
		String description=request.getParameter("description");
		String costRange=request.getParameter("costRange");
		HttpSession hs=request.getSession();
		
		try{
			int editCrops=DatabaseConnection.insertUpdateFromSqlQuery("update tblinsecticide set insecticide_name='"+iname+"',insecticide_cost_range='"+costRange+"',description='"+description+"' where id='"+insectidesId+"'");
			if(editCrops>0) {
				String message="Insecticide edited";
				hs.setAttribute("insecticide-edit", message);
				response.sendRedirect("admin-manage-insecticides.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
