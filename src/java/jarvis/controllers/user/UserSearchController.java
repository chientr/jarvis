package jarvis.controllers.user;

import jarvis.beans.UserBean;
import jarvis.dtos.UserDTO;
import jarvis.dtos.UserSearchDTO;
import jarvis.utils.BeanPopulator;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chris Tran
 */
public class UserSearchController extends HttpServlet {

    private static final String ERROR = "WEB-INF/error.jsp";
    private static final String HOME = "WEB-INF/users.jsp";
    private static final String LIST = "UserListController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("ERROR", "Unable to search user");
        String url = ERROR;
        try {
            UserBean bean = new UserBean();
            BeanPopulator populator = new BeanPopulator();
            populator.populateUser(request, bean);
            UserSearchDTO searchDTO = bean.getUserSearchDTO();
            if (!searchDTO.confirm()) {
                url = LIST;
            } else {
                List<UserDTO> dtos = bean.search();
                request.setAttribute("SEARCH", searchDTO);
                request.setAttribute("DTOS", dtos);
                url = HOME;
            }
        } catch (Exception e) {
            log("ERROR at UserSearchController: " + e.getMessage());
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
