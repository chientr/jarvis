package jarvis.controllers.mark;

import jarvis.beans.MarkBean;
import jarvis.utils.BeanPopulator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chris Tran
 */
public class MarkDeleteController extends HttpServlet {

    private static final String ERROR = "WEB-INF/error.jsp";
    private static final String UPLOAD_DIR = "uploads/marks";
    private static final String SEARCH = "MarkSearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("ERROR", "Unable to delete mark");
        String url = ERROR;
        try {
            MarkBean bean = new MarkBean();
            BeanPopulator populator = new BeanPopulator();
            populator.populateMark(request, bean);
            if (bean.delete()) {
                url = SEARCH;

                String fileName = bean.getMarkCode();
                String applicationPath = request.getServletContext().getRealPath("");
                String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
                File fileImage = new File(uploadFilePath + File.separator + fileName);
                Files.deleteIfExists(fileImage.toPath());
            }
        } catch (Exception e) {
            log("ERROR at MarkDeleteController: " + e.getMessage());
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
