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
 * Servlet implementation class AdminEditEquipment
 */
@WebServlet("/AdminEditEquipment")
public class AdminEditEquipment extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eId=Integer.parseInt(request.getParameter("eId"));
		String ename=request.getParameter("ename");
		String description=request.getParameter("description");
		String costRange=request.getParameter("costRange");
		HttpSession hs=request.getSession();
		
		try{
			int editCrops=DatabaseConnection.insertUpdateFromSqlQuery("update tblequipements set equipement_name='"+ename+"',equipement_cost_range='"+costRange+"',description='"+description+"' where id='"+eId+"'");
			if(editCrops>0) {
				String success = "Equipment edited successfully.";
				hs.setAttribute("equipment-edit", success);
				response.sendRedirect("admin-manage-equipments.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
