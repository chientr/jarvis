package jarvis.daos;

import jarvis.dtos.MissionDTO;
import jarvis.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chris Tran
 */
public class HomeDAO implements Serializable {

    private static final String SIGN_IN = "select "
            + "1 as dummy "
            + "from Users "
            + "where username=? and password=?";
    private static final String CHECK_USERNAME_STILL_ALIVE = "select "
            + "1 as dummy "
            + "from Users "
            + "where username=?";
    private static final String GET_MY_MISSIONS = "select "
            + "missionCode, missionName, location, description, state, startDate, finishDate "
            + "from Missions "
            + "where missionCode in (select "
            + "missionCode "
            + "from MissionRefs "
            + "where avengerUsername=?) "
            + "order by startDate";

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public boolean signIn(String username, String password) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(SIGN_IN);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            check = resultSet.next();
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean checkUsernameStillAlive(String username) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(CHECK_USERNAME_STILL_ALIVE);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            check = resultSet.next();
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<MissionDTO> getMyMissions(String username) throws Exception {
        List<MissionDTO> dtos = null;
        String missionCode, missionName, location, description, state;
        Date startDate, finishDate;
        MissionDTO dto;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(GET_MY_MISSIONS);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            dtos = new ArrayList<>();
            while (resultSet.next()) {
                missionCode = resultSet.getString("missionCode");
                missionName = resultSet.getString("missionName");
                location = resultSet.getString("location");
                description = resultSet.getString("description");
                state = resultSet.getString("state");
                startDate = resultSet.getDate("startDate");
                finishDate = resultSet.getDate("finishDate");
                dto = new MissionDTO(missionCode, missionName, location, description, state, startDate, finishDate);
                dtos.add(dto);
            }
        } finally {
            closeConnection();
        }
        return dtos;
    }

    private void closeConnection() throws Exception {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
