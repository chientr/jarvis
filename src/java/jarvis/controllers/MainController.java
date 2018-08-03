package jarvis.controllers;

import jarvis.beans.HomeBean;
import jarvis.utils.Secret;
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
public class MainController extends HttpServlet {

    private static final String ERROR = "WEB-INF/error.jsp";
    private static final String MISSION = "MissionMainController";
    private static final String USER = "UserMainController";
    private static final String MARK = "MarkMainController";
    private static final String HOME = "HomeMainController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            request.setAttribute("SECRET", Secret.SECRET);
            String sessionUsername = (String) request.getSession().getAttribute("USERNAME");
            HomeBean userBean = new HomeBean();
            if (sessionUsername == null || !userBean.checkUsernameStillAlive(sessionUsername)) {
                request.getSession().setAttribute("USERNAME", null);
                sessionUsername = "";
            }
            String controller = request.getParameter("controller");
            if (controller == null) {
                controller = "";
            }
            if (controller.equals("Mission") && (sessionUsername.equals("jarvis"))) {
                url = MISSION;
            } else if (controller.equals("User") && (sessionUsername.equals("jarvis"))) {
                url = USER;
            } else if (controller.equals("Mark") && (sessionUsername.equals("stark"))) {
                url = MARK;
            } else {
                url = HOME;
            }
        } catch (Exception e) {
            log("ERROR at MainController: " + e.getMessage());
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
