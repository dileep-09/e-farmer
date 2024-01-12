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
 * Servlet implementation class AdminAddEquipments
 */
@WebServlet("/AdminAddEquipments")
public class AdminAddEquipments extends HttpServlet {
	private final String UPLOAD_DIRECTORY = "F:/project-workspace/FarmerBuddy/WebContent/uploads/";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				String imageName = null;
				String equipmentId = null;
				String equipmentName = null;
				String equipmentCostPrice = null;
				String equipmentDescription = null;
				

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						imageName = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + imageName));

						FileItem eId = (FileItem) multiparts.get(0);
						equipmentId = eId.getString();

					
						FileItem eName = (FileItem) multiparts.get(1);
						equipmentName = eName.getString();

						FileItem eDescription = (FileItem) multiparts.get(2);
						equipmentDescription = eDescription.getString();
						
						FileItem eCostRange = (FileItem) multiparts.get(3);
						equipmentCostPrice = eCostRange.getString();


					}
				}
				try {
					int id = 0;
					String imagePath = UPLOAD_DIRECTORY + imageName;
					int i = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into tblequipements(id,equipement_name,description,equipement_cost_range,equipement_image_name,equipement_image) values('"
									+ equipmentId + "','" + equipmentName + "','" + equipmentDescription + "','" + equipmentCostPrice
									+ "','" + imageName + "','" + imagePath + "')");
					if (i > 0) {
						String success = "Equipment added successfully.";
						session.setAttribute("message", success);
						response.sendRedirect("admin-manage-equipments.jsp");
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
