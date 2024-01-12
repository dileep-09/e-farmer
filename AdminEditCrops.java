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
 * Servlet implementation class AdminEditCrops
 */
@WebServlet("/AdminEditCrops")
public class AdminEditCrops extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cropId=Integer.parseInt(request.getParameter("cropId"));
		String cname=request.getParameter("cname");
		String description=request.getParameter("description");
		String month=request.getParameter("month");
		HttpSession hs=request.getSession();
		
		try{
			int editCrops=DatabaseConnection.insertUpdateFromSqlQuery("update tblcrops set crop_name='"+cname+"',month='"+month+"',description='"+description+"' where id='"+cropId+"'");
			if(editCrops>0) {
				String message="Crops edited successfully.";
				hs.setAttribute("crop-edit", message);
				response.sendRedirect("admin-manage-crops.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
