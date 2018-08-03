package jarvis.utils;

import jarvis.beans.HomeBean;
import jarvis.beans.MarkBean;
import jarvis.beans.MissionBean;
import jarvis.beans.UserBean;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Chris Tran
 */
public class BeanPopulator {

    public BeanPopulator() {
    }

    public void populateHome(HttpServletRequest request, HomeBean homeBean) {
        homeBean.setUsername(request.getParameter("textUsername"));
        homeBean.setPassword(request.getParameter("textPassword"));
        homeBean.setPasswordConfirm(request.getParameter("textPasswordConfirm"));
        homeBean.setAlias(request.getParameter("textAlias"));
        homeBean.setRealname(request.getParameter("textRealname"));
        homeBean.setDescription(request.getParameter("textDescription"));
    }

    public void populateMark(HttpServletRequest request, MarkBean markBean) {
        markBean.setMarkCode(request.getParameter("textMarkCode"));
        markBean.setMarkName(request.getParameter("textMarkName"));
        markBean.setArmorType(request.getParameter("textArmorType"));
        markBean.setDescription(request.getParameter("textDescription"));

        markBean.setSearchMarkName(request.getParameter("textSearchMarkName"));
    }

    public void populateUser(HttpServletRequest request, UserBean userBean) {
        userBean.setUsername(request.getParameter("textUsername"));
        userBean.setPassword(request.getParameter("textPassword"));
        userBean.setPasswordConfirm(request.getParameter("textPasswordConfirm"));
        userBean.setAlias(request.getParameter("textAlias"));
        userBean.setRealname(request.getParameter("textRealname"));
        userBean.setDescription(request.getParameter("textDescription"));

        userBean.setSearchAlias(request.getParameter("textSearchAlias"));
    }

    public void populateMission(HttpServletRequest request, MissionBean missionBean) {
        missionBean.setMissionCode(request.getParameter("textMissionCode"));
        missionBean.setMissionName(request.getParameter("textMissionName"));
        missionBean.setLocation(request.getParameter("textLocation"));
        missionBean.setDescription(request.getParameter("textDescription"));

        missionBean.setSearchMissionName(request.getParameter("textSearchMissionName"));
        missionBean.setSearchLocation(request.getParameter("textSearchLocation"));
        missionBean.setSearchState(request.getParameter("textSearchState"));

        missionBean.setAvengerUsername(request.getParameter("textAvengerUsername"));
        missionBean.setMarkCode(request.getParameter("textMarkCode"));
    }
}
