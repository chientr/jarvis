package jarvis.controllers.mission;

import jarvis.beans.MissionBean;
import jarvis.dtos.MissionDTO;
import jarvis.dtos.MissionSearchDTO;
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
public class MissionSearchController extends HttpServlet {

    private static final String ERROR = "WEB-INF/error.jsp";
    private static final String HOME = "WEB-INF/missions.jsp";
    private static final String LIST = "MissionListController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("ERROR", "Unable to search mission");
        String url = ERROR;
        try {
            MissionBean bean = new MissionBean();
            BeanPopulator populator = new BeanPopulator();
            populator.populateMission(request, bean);
            MissionSearchDTO searchDTO = bean.getMissionSearchDTO();
            if (!searchDTO.confirm()) {
                url = LIST;
            } else {
                List<MissionDTO> dtos = bean.search();
                request.setAttribute("SEARCH", searchDTO);
                List<String> locations = bean.getAllLocations();
                request.setAttribute("LOCATIONS", locations);
                request.setAttribute("DTOS", dtos);
                url = HOME;
            }
        } catch (Exception e) {
            log("ERROR at MissionSearchController: " + e.getMessage());
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
