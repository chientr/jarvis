package jarvis.controllers.mission;

import jarvis.beans.MissionBean;
import jarvis.errorobjects.MissionErrorObject;
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
public class MissionInsertController extends HttpServlet {

    private static final String ERROR = "WEB-INF/error.jsp";
    private static final String INVALID = "WEB-INF/_mission.jsp";
    private static final String SEARCH = "MissionSearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("ERROR", "Unable to insert mission");
        String url = ERROR;
        try {
            MissionBean bean = new MissionBean();
            BeanPopulator populator = new BeanPopulator();
            populator.populateMission(request, bean);
            MissionErrorObject errorObject = bean.validateInsert();
            if (errorObject != null) {
                request.setAttribute("ERROROBJECT", errorObject);
                request.setAttribute("DTO", bean.getMissionDTO());
                url = INVALID;
            } else {
                try {
                    if (bean.insert()) {
                        url = SEARCH;
                    }
                } catch (Exception e) {
                    if (e.getMessage().contains("duplicate")) {
                        if (errorObject == null) {
                            errorObject = new MissionErrorObject();
                        }
                        errorObject.setMissionCodeError("Mission code is existed");
                        request.setAttribute("ERROROBJECT", errorObject);
                        request.setAttribute("DTO", bean.getMissionDTO());
                        url = INVALID;
                    }
                }
            }
        } catch (Exception e) {
            log("ERROR at MissionInsertController: " + e.getMessage());
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
