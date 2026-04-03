package com.sms.servlet;

import java.io.File;
import java.io.IOException;

import com.sms.dao.StudentDAO;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/uploadPhoto")
@MultipartConfig
public class UploadPhotoServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws IOException {

try{

String regd = request.getParameter("regd_no");

Part filePart = request.getPart("photo");

String fileName = filePart.getSubmittedFileName();

/* PATH WHERE IMAGE WILL BE SAVED */

String uploadPath = getServletContext().getRealPath("") + File.separator + "photos";

File folder = new File(uploadPath);

if(!folder.exists()){
folder.mkdir();
}

/* SAVE FILE */

filePart.write(uploadPath + File.separator + fileName);

/* UPDATE DATABASE */

StudentDAO.updatePhoto(regd, fileName);

/* REDIRECT BACK TO PROFILE */

response.sendRedirect("studentProfile");

}catch(Exception e){
e.printStackTrace();
}

}
}