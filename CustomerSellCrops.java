package com.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class SellCrops
 */
@WebServlet("/CustomerSellCrops")
public class CustomerSellCrops extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId=Integer.parseInt(request.getParameter("customerId"));
		String customerName=request.getParameter("cname");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		String cropName=request.getParameter("cropName");
		String quantity=request.getParameter("quantity");
		String cropDescription=request.getParameter("cropDescription");
		String cropBuyerName=request.getParameter("cropBuyerName");
		HttpSession hs=request.getSession();
		
		try {
			int sellCrop=DatabaseConnection.insertUpdateFromSqlQuery("insert into tblsellcrops(sell_id,customer_name,email_id,mobile_no,address,crop_name,crop_description,crop_quantity,buyer_name,status)values('"+customerId+"','"+customerName+"','"+email+"','"+mobile+"','"+address+"','"+cropName+"','"+cropDescription+"','"+quantity+"','"+cropBuyerName+"','Pending')");
			if(sellCrop>0) {
				String message="Crop sell successfully.";
				hs.setAttribute("success", message);
				response.sendRedirect("customer-sell-crops.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
