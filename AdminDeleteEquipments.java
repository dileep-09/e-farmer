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
 * Servlet implementation class AdminDeleteEquipments
 */
@WebServlet("/AdminDeleteEquipments")
public class AdminDeleteEquipments extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession hs=request.getSession();
		
		int deleteCrop = DatabaseConnection.insertUpdateFromSqlQuery("delete from tblequipements where id='" + id + "'");
		if (deleteCrop > 0) {
			String success = "Equipment deleted successfully.";
			hs.setAttribute("equipment-delete", success);
			response.sendRedirect("admin-manage-equipments.jsp");
		}
	}

}
