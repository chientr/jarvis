package jarvis.controllers.home;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Chris Tran
 */
@MultipartConfig
public class HomeChangeImageController extends HttpServlet {

    private static final String ERROR = "WEB-INF/error.jsp";
    private static final String UPLOAD_DIR = "uploads/users";
    private static final String SUCCESS = "HomeEditProfileController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("ERROR", "Unable to change image");
        String url = ERROR;
        try {
            String sessionUsername = (String) request.getSession().getAttribute("USERNAME");

            String fileName = sessionUsername;
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
            File fileSaveDir = new File(uploadFilePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            Part filePart = request.getPart("fileImage");
            if (!filePart.getSubmittedFileName().trim().isEmpty()) {
                OutputStream out = null;
                InputStream filecontent = null;
                try {
                    out = new FileOutputStream(new File(uploadFilePath + File.separator
                            + fileName));
                    filecontent = filePart.getInputStream();
                    int read;
                    final byte[] bytes = new byte[1024];
                    while ((read = filecontent.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                    url = SUCCESS;
                } finally {
                    if (out != null) {
                        out.close();
                    }
                    if (filecontent != null) {
                        filecontent.close();
                    }
                }
            } else {
                request.setAttribute("ERROR", "Please select a valid image");
            }
        } catch (Exception e) {
            log("ERROR at HomeChangeImageController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
