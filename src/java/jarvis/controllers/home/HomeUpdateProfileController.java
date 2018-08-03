package jarvis.controllers.home;

import jarvis.beans.HomeBean;
import jarvis.errorobjects.UserErrorObject;
import jarvis.utils.BeanPopulator;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chris Tran
 */
public class HomeUpdateProfileController extends HttpServlet {

    private static final String ERROR = "WEB-INF/error.jsp";
    private static final String INVALID = "HomeEditProfileController";
    private static final String HOME = "HomeIndexController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("ERROR", "Unable to update profile");
        String url = ERROR;
        try {
            request.setAttribute("ERROR", "Unable to update profile");
            HomeBean bean = new HomeBean();
            String sessionUsername = (String) request.getSession().getAttribute("USERNAME");
            BeanPopulator populator = new BeanPopulator();
            populator.populateHome(request, bean);
            UserErrorObject errorObject = bean.validateUpdate();
            if (errorObject != null) {
                request.setAttribute("ERROROBJECT", errorObject);
                url = INVALID;
            } else {
                if (bean.update(sessionUsername)) {
                    url = HOME;
                }
            }
        } catch (Exception e) {
            log("ERROR at HomeUpdateProfileController: " + e.getMessage());
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
