package jarvis.daos;

import jarvis.dtos.MissionDTO;
import jarvis.dtos.UserDTO;
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
public class MissionDAO implements Serializable {

    private static final String GET_ALL = "select "
            + "missionCode, missionName, location, state, startDate "
            + "from Missions "
            + "order by startDate DESC";
    private static final String FIND_BY_NAME_AND_STATE = "select "
            + "missionCode, missionName, location, startDate "
            + "from Missions "
            + "where missionName like ? and state=? "
            + "order by startDate DESC";
    private static final String FIND_BY_NAME_AND_LOCATION = "select "
            + "missionCode, missionName, state, startDate "
            + "from Missions "
            + "where missionName like ? and location=? "
            + "order by startDate DESC";
    private static final String FIND_BY_NAME_AND_LOCATION_AND_STATE = "select "
            + "missionCode, missionName, startDate "
            + "from Missions "
            + "where missionName like ? and location=? and state=? "
            + "order by startDate DESC";
    private static final String FIND_BY_NAME = "select "
            + "missionCode, missionName, location, state, startDate "
            + "from Missions "
            + "where missionName like ? "
            + "order by startDate DESC";
    private static final String GET_BY_MISSION_CODE = "select "
            + "missionName, location, description, state, startDate, finishDate "
            + "from Missions "
            + "where missionCode=?";
    private static final String INSERT = "insert "
            + "into Missions(missionCode, missionName, location, state, startDate) "
            + "values(?, ?, ?, ?, ?)";
    private static final String UPDATE = "update "
            + "Missions "
            + "set missionName=?, location=?, description=? "
            + "where missionCode=?";
    private static final String DELETE = "delete "
            + "Missions "
            + "where missionCode=?";
    private static final String RETRIEVE_STATE = "select "
            + "state "
            + "from Missions "
            + "where missionCode=?";
    private static final String FINISH = "update "
            + "Missions "
            + "set state=?, finishDate=? "
            + "where missionCode=?";
    private static final String GET_ALL_LOCATIONS = "select "
            + "distinct location "
            + "from Missions "
            + "order by location";
    private static final String GET_FREE_USERS = "select "
            + "username, alias, realname "
            + "from Users "
            + "where username not in (select "
            + "avengerUsername "
            + "from MissionRefs "
            + "where missionCode in (select "
            + "missionCode "
            + "from Missions "
            + "where state = 'RUNNING')"
            + ") and username <> 'jarvis'"
            + " order by alias";
    private static final String GET_CURRENT_USERS = "select "
            + "username, alias, realname "
            + "from Users "
            + "where username in(select avengerUsername as username "
            + "from MissionRefs "
            + "where missionCode=?)";
    private static final String ADD_USER = "insert "
            + "into MissionRefs(missionCode, avengerUsername) "
            + "values(?, ?)";
    private static final String ADD_USER_WITH_MARK = "insert "
            + "into MissionRefs(missionCode, avengerUsername, markCode) "
            + "values(?, ?, ?)";
    private static final String REMOVE_USER = "delete "
            + "MissionRefs "
            + "where missionCode=? and avengerUsername=?";

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<MissionDTO> getAll() throws Exception {
        List<MissionDTO> dtos = null;
        String missionCode, missionName, location, state;
        Date startDate;
        MissionDTO dto;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            resultSet = preparedStatement.executeQuery();
            dtos = new ArrayList<>();
            while (resultSet.next()) {
                missionCode = resultSet.getString("missionCode");
                missionName = resultSet.getString("missionName");
                location = resultSet.getString("location");
                state = resultSet.getString("state");
                startDate = resultSet.getDate("startDate");
                dto = new MissionDTO(missionCode, missionName, location, state, startDate);
                dtos.add(dto);
            }
        } finally {
            closeConnection();
        }
        return dtos;
    }

    public List<MissionDTO> findByNameAndState(String searcMissionName, String searchState) throws Exception {
        List<MissionDTO> dtos = null;
        String missionCode, missionName, location;
        Date startDate;
        MissionDTO dto;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_NAME_AND_STATE);
            preparedStatement.setString(1, "%" + searcMissionName + "%");
            preparedStatement.setString(2, searchState);
            resultSet = preparedStatement.executeQuery();
            dtos = new ArrayList<>();
            while (resultSet.next()) {
                missionCode = resultSet.getString("missionCode");
                missionName = resultSet.getString("missionName");
                location = resultSet.getString("location");
                startDate = resultSet.getDate("startDate");
                dto = new MissionDTO(missionCode, missionName, location, searchState, startDate);
                dtos.add(dto);
            }
        } finally {
            closeConnection();
        }
        return dtos;
    }

    public List<MissionDTO> findByNameAndLocationAndState(String searchMissionName, String searchLocation, String searchState) throws Exception {
        List<MissionDTO> dtos = null;
        String missionCode, missionName;
        Date startDate;
        MissionDTO dto;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_NAME_AND_LOCATION_AND_STATE);
            preparedStatement.setString(1, "%" + searchMissionName + "%");
            preparedStatement.setString(2, searchLocation);
            preparedStatement.setString(3, searchState);
            resultSet = preparedStatement.executeQuery();
            dtos = new ArrayList<>();
            while (resultSet.next()) {
                missionCode = resultSet.getString("missionCode");
                missionName = resultSet.getString("missionName");
                startDate = resultSet.getDate("startDate");
                dto = new MissionDTO(missionCode, missionName, searchLocation, searchState, startDate);
                dtos.add(dto);
            }
        } finally {
            closeConnection();
        }
        return dtos;
    }

    public List<MissionDTO> findByNameAndLocation(String searchMissionName, String searchLocation) throws Exception {
        List<MissionDTO> dtos = null;
        String missionCode, missionName, state;
        Date startDate;
        MissionDTO dto;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_NAME_AND_LOCATION);
            preparedStatement.setString(1, "%" + searchMissionName + "%");
            preparedStatement.setString(2, searchLocation);
            resultSet = preparedStatement.executeQuery();
            dtos = new ArrayList<>();
            while (resultSet.next()) {
                missionCode = resultSet.getString("missionCode");
                missionName = resultSet.getString("missionName");
                state = resultSet.getString("state");
                startDate = resultSet.getDate("startDate");
                dto = new MissionDTO(missionCode, missionName, searchLocation, state, startDate);
                dtos.add(dto);
            }
        } finally {
            closeConnection();
        }
        return dtos;
    }

    public List<MissionDTO> findByName(String searchMissionName) throws Exception {
        List<MissionDTO> dtos = null;
        String missionCode, missionName, location, state;
        Date startDate;
        MissionDTO dto;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            preparedStatement.setString(1, "%" + searchMissionName + "%");
            resultSet = preparedStatement.executeQuery();
            dtos = new ArrayList<>();
            while (resultSet.next()) {
                missionCode = resultSet.getString("missionCode");
                missionName = resultSet.getString("missionName");
                location = resultSet.getString("location");
                state = resultSet.getString("state");
                startDate = resultSet.getDate("startDate");
                dto = new MissionDTO(missionCode, missionName, location, state, startDate);
                dtos.add(dto);
            }
        } finally {
            closeConnection();
        }
        return dtos;
    }

    public MissionDTO getByMissionCode(String missionCode) throws Exception {
        MissionDTO dto = null;
        String missionName, location, description, state;
        Date startDate, finishDate;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(GET_BY_MISSION_CODE);
            preparedStatement.setString(1, missionCode);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                missionName = resultSet.getString("missionName");
                location = resultSet.getString("location");
                description = resultSet.getString("description");
                state = resultSet.getString("state");
                startDate = resultSet.getDate("startDate");
                finishDate = resultSet.getDate("finishDate");
                dto = new MissionDTO(missionCode, missionName, location, description, state, startDate, finishDate);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean insert(String missionCode, String missionName, String location) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, missionCode);
            preparedStatement.setString(2, missionName);
            preparedStatement.setString(3, location);
            preparedStatement.setString(4, "RUNNING");
            preparedStatement.setDate(5, new Date(System.currentTimeMillis()));
            check = preparedStatement.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean update(String missionCode, String missionName, String location, String description) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(4, missionCode);
            preparedStatement.setString(1, missionName);
            preparedStatement.setString(2, location);
            preparedStatement.setString(3, description);
            check = preparedStatement.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean delete(String missionCode) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, missionCode);
            check = preparedStatement.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        return check;
    }

    public String retrieveState(String missionCode) throws Exception {
        String state = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(RETRIEVE_STATE);
            preparedStatement.setString(1, missionCode);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                state = resultSet.getString("state");
            }
        } finally {
            closeConnection();
        }
        return state;
    }

    public boolean finish(String missionCode) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(FINISH);
            preparedStatement.setString(3, missionCode);
            preparedStatement.setString(1, "DONE");
            preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
            check = preparedStatement.executeUpdate() == 1;
        } catch (Exception e) {
            connection.rollback();
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<String> getAllLocations() throws Exception {
        List<String> locations = null;
        String location;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_LOCATIONS);
            resultSet = preparedStatement.executeQuery();
            locations = new ArrayList<>();
            while (resultSet.next()) {
                location = resultSet.getString("location");
                locations.add(location);
            }
        } finally {
            closeConnection();
        }
        return locations;
    }

    public List<UserDTO> getFreeUsers() throws Exception {
        List<UserDTO> users = null;
        String username, alias, realname;
        UserDTO user;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(GET_FREE_USERS);
            resultSet = preparedStatement.executeQuery();
            users = new ArrayList<>();
            while (resultSet.next()) {
                username = resultSet.getString("username");
                alias = resultSet.getString("alias");
                realname = resultSet.getString("realname");
                user = new UserDTO(username, alias, realname);
                users.add(user);
            }
        } finally {
            closeConnection();
        }
        return users;
    }

    public boolean addUser(String missionCode, String avengerUsername) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, missionCode);
            preparedStatement.setString(2, avengerUsername);
            check = preparedStatement.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean addUserWithMark(String missionCode, String avengerUsername, String markCode) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(ADD_USER_WITH_MARK);
            preparedStatement.setString(1, missionCode);
            preparedStatement.setString(2, avengerUsername);
            preparedStatement.setString(3, markCode);
            check = preparedStatement.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean removeUser(String missionCode, String avengerUsername) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(REMOVE_USER);
            preparedStatement.setString(1, missionCode);
            preparedStatement.setString(2, avengerUsername);
            check = preparedStatement.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<UserDTO> getCurrentUsers(String missionCode) throws Exception {
        List<UserDTO> dtos = null;
        String username, alias, realname;
        UserDTO dto;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(GET_CURRENT_USERS);
            preparedStatement.setString(1, missionCode);
            resultSet = preparedStatement.executeQuery();
            dtos = new ArrayList<>();
            while (resultSet.next()) {
                username = resultSet.getString("username");
                alias = resultSet.getString("alias");
                realname = resultSet.getString("realname");
                dto = new UserDTO(username, alias, realname);
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
