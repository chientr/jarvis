package jarvis.controllers.home;

import jarvis.beans.HomeBean;
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
public class HomeMainController extends HttpServlet {

    private static final String ERROR = "WEB-INF/error.jsp";
    private static final String INDEX = "HomeIndexController";
    private static final String SIGN_OUT = "HomeSignOutController";
    private static final String PROFILE = "HomeEditProfileController";
    private static final String PROFILE_UPDATE = "HomeUpdateProfileController";
    private static final String CHANGE_IMAGE = "HomeChangeImageController";
    private static final String RESET_PASSWORD = "HomeResetPasswordController";
    private static final String DELETE_PROFILE = "HomeDeleteProfileController";
    private static final String REQUEST_SIGN_UP = "WEB-INF/signup.jsp";
    private static final String SIGN_UP = "HomeSignUpController";
    private static final String REQUEST_SIGN_IN = "WEB-INF/signin.jsp";
    private static final String SIGN_IN = "HomeSignInController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("ERROR", "Resource location not found");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            String sessionUsername = (String) request.getSession().getAttribute("USERNAME");
            if (sessionUsername == null) {
                if (action.equals("Register")) {
                    url = REQUEST_SIGN_UP;
                } else if (action.equals("Sign Up")) {
                    url = SIGN_UP;
                } else if (action.equals("Log In")) {
                    url = REQUEST_SIGN_IN;
                } else if (action.equals("Sign In")) {
                    url = SIGN_IN;
                } else {
                    url = REQUEST_SIGN_IN;
                }
            } else {
                HomeBean bean = new HomeBean();
                if (bean.checkUsernameStillAlive(sessionUsername)) {
                    if (action.equals("Profile")) {
                        url = PROFILE;
                    } else if (action.equals("Update")) {
                        url = PROFILE_UPDATE;
                    } else if (action.equals("Change Image")) {
                        url = CHANGE_IMAGE;
                    } else if (action.equals("Reset Password")) {
                        url = RESET_PASSWORD;
                    } else if (action.equals("Delete")) {
                        url = DELETE_PROFILE;
                    } else if (action.equals("Sign Out")) {
                        url = SIGN_OUT;
                    } else {
                        url = INDEX;
                    }
                } else {
                    url = REQUEST_SIGN_IN;
                }
            }
        } catch (Exception e) {
            log("ERROR at HomeMainController: " + e.getMessage());
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
