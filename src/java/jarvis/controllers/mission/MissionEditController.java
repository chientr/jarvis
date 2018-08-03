package jarvis.controllers.mission;

import jarvis.beans.MarkBean;
import jarvis.beans.MissionBean;
import jarvis.dtos.MarkDTO;
import jarvis.dtos.MissionDTO;
import jarvis.dtos.MissionSearchDTO;
import jarvis.dtos.UserDTO;
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
public class MissionEditController extends HttpServlet {

    private static final String ERROR = "WEB-INF/error.jsp";
    private static final String HOME = "WEB-INF/mission.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("ERROR", "Unable to get mission");
        String url = ERROR;
        try {
            MissionBean bean = new MissionBean();
            MarkBean markBean = new MarkBean();
            BeanPopulator populator = new BeanPopulator();
            populator.populateMission(request, bean);
            MissionDTO dto = bean.getByMissionCode();
            request.setAttribute("DTO", dto);
            MissionSearchDTO search = bean.getMissionSearchDTO();
            request.setAttribute("SEARCH", search);
            List<UserDTO> currentUsers = bean.getCurrentUsers();
            List<UserDTO> freeUsers = bean.getFreeUsers();
            List<MarkDTO> marks = markBean.getAll();
            request.setAttribute("CURRENTUSERS", currentUsers);
            request.setAttribute("FREEUSERS", freeUsers);
            request.setAttribute("MARKS", marks);
            url = HOME;
        } catch (Exception e) {
            log("ERROR at MissionEditController: " + e.getMessage());
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
