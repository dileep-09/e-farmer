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
 * Servlet implementation class AdminAddCrops
 */
@WebServlet("/AdminAddCrops")
public class AdminAddCrops extends HttpServlet {
	private final String UPLOAD_DIRECTORY = "F:/project-workspace/FarmerBuddy/WebContent/uploads/";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				String imageName = null;
				String cropId = null;
				String cropName = null;
				String cropMonth = null;
				String cropDescription = null;
				

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						imageName = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + imageName));

						FileItem cId = (FileItem) multiparts.get(0);
						cropId = cId.getString();

					
						FileItem cName = (FileItem) multiparts.get(1);
						cropName = cName.getString();

						FileItem cDescription = (FileItem) multiparts.get(2);
						cropDescription = cDescription.getString();
						
						FileItem cMonth = (FileItem) multiparts.get(3);
						cropMonth = cMonth.getString();


					}
				}
				try {
					int id = 0;
					String imagePath = UPLOAD_DIRECTORY + imageName;
					int i = DatabaseConnection.insertUpdateFromSqlQuery(
							"insert into tblcrops(id,crop_name,description,month,crop_image_name,crop_image) values('"
									+ cropId + "','" + cropName + "','" + cropDescription + "','" + cropMonth
									+ "','" + imageName + "','" + imagePath + "')");
					if (i > 0) {
						String success = "Crop added successfully.";
						session.setAttribute("message", success);
						response.sendRedirect("admin-manage-crops.jsp");
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
