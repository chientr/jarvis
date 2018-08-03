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
public class HomeSignInController extends HttpServlet {

    private static final String ERROR = "WEB-INF/error.jsp";
    private static final String INVALID = "WEB-INF/signin.jsp";
    private static final String SUCCESS = "HomeIndexController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("ERROR", "Unable to sign in");
        String url = ERROR;
        try {
            HomeBean bean = new HomeBean();
            BeanPopulator populator = new BeanPopulator();
            populator.populateHome(request, bean);
            UserErrorObject errorObject = bean.validateSignIn();
            if (errorObject == null) {
                if (!bean.signIn()) {
                    errorObject = new UserErrorObject();
                    errorObject.setUsernameError("Invalid username or password");
                }
            }
            if (errorObject != null) {
                request.setAttribute("ERROROBJECT", errorObject);
                request.setAttribute("DTO", bean.getUserDTO());
                url = INVALID;
            } else {
                request.getSession().setAttribute("USERNAME", bean.getUsername());
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("ERROR at UserSignInController: " + e.getMessage());
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
