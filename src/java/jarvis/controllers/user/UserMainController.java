package jarvis.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chris Tran
 */
@MultipartConfig
public class UserMainController extends HttpServlet {

    private static final String ERROR = "WEB-INF/error.jsp";
    private static final String LIST = "UserListController";
    private static final String SEARCH = "UserSearchController";
    private static final String REQUEST_INSERT = "WEB-INF/_user.jsp";
    private static final String INSERT = "UserInsertController";
    private static final String EDIT = "UserEditController";
    private static final String UPDATE = "UserUpdateController";
    private static final String CHANGE_IMAGE = "UserChangeImageController";
    private static final String DELETE = "UserDeleteController";
    private static final String RESET_PASSWORD = "UserSetPasswordController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("ERROR", "Resource location not found");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (action == null || action.equals("List")) {
                url = LIST;
            } else if (action.equals("Search")) {
                url = SEARCH;
            } else if (action.equals("Add User")) {
                url = REQUEST_INSERT;
            } else if (action.equals("Insert")) {
                url = INSERT;
            } else if (action.equals("Edit")) {
                url = EDIT;
            } else if (action.equals("Update")) {
                url = UPDATE;
            } else if (action.equals("Change Image")) {
                url = CHANGE_IMAGE;
            } else if (action.equals("Delete")) {
                url = DELETE;
            } else if (action.equals("Set Password")) {
                url = RESET_PASSWORD;
            }
        } catch (Exception e) {
            log("ERROR at UserMainController: " + e.getMessage());
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
