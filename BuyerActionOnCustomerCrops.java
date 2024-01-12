package com.buyer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class BuyerActionOnCustomerCrops
 */
@WebServlet("/BuyerActionOnCustomerCrops")
public class BuyerActionOnCustomerCrops extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sellId=Integer.parseInt(request.getParameter("sellId"));
		String cropName=request.getParameter("cropName");
		String status=request.getParameter("status");
		
		try {
			int cropAction=DatabaseConnection.insertUpdateFromSqlQuery("update tblsellcrops set status='"+status+"' where sell_id='"+sellId+"' and crop_name='"+cropName+"'");
			if(cropAction>0) {
				response.sendRedirect("buyer-view-customer-crops-selling-details.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
