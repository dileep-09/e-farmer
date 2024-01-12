package com.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class AdminAddPesticides
 */
@WebServlet("/AdminAddPesticides")
public class AdminAddPesticides extends HttpServlet {
	private final String UPLOAD_DIRECTORY = "F:/project-workspace/FarmerBuddy/WebContent/uploads/";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				String imageName = null;
				String pesticideId = null;
				String pesticideName = null;
				String pesticideCostPrice = null;
				String pesticideDescription = null;
				

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						imageName = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + imageName));

						FileItem pId = (FileItem) multiparts.get(0);
						pesticideId = pId.getString();

					
						FileItem pName = (FileItem) multiparts.get(1);
						pesticideName = pName.getString();

						FileItem pDescription = (FileItem) multiparts.get(2);
						pesticideDescription = pDescription.getString();
						
						FileItem pCostRange = (FileItem) multiparts.get(3);
						pesticideCostPrice = pCostRange.getString();


					}
				}
				try {
					int id = 0;
					String imagePath = UPLOAD_DIRECTORY + imageName;
					int i = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into tblpesticide(id,pesticide_name,description,pesticide_cost_range,pesticide_image_name,pesticide_image) values('"
									+ pesticideId + "','" + pesticideName + "','" + pesticideDescription + "','" + pesticideCostPrice
									+ "','" + imageName + "','" + imagePath + "')");
					if (i > 0) {
						String success = "Pesticide added successfully.";
						session.setAttribute("message", success);
						response.sendRedirect("admin-manage-pesticides.jsp");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
			}

		} else {
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}
	}
}
