package jarvis.daos;

import jarvis.dtos.UserDTO;
import jarvis.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chris Tran
 */
public class UserDAO implements Serializable {

    private static final String GET_ALL = "select "
            + "username, alias, realname "
            + "from Users "
            + "where username <> 'jarvis'"
            + "order by alias";
    private static final String FIND_BY_LIKE_ALIAS = "select "
            + "username, alias, realname "
            + "from Users "
            + "where alias like ? and username <> 'jarvis' "
            + "order by alias";
    private static final String GET_BY_USERNAME = "select "
            + "alias, realname, description "
            + "from Users "
            + "where username=?";
    private static final String INSERT = "insert "
            + "into Users(username, password, alias, realname) "
            + "values(?, ?, ?, ?)";
    private static final String UPDATE = "update "
            + "Users "
            + "set alias=?, realname=?, description=? "
            + "where username=?";
    private static final String DELETE = "delete "
            + "Users "
            + "where username=?";
    private static final String SIGN_IN = "select "
            + "1 as dummy "
            + "from Users "
            + "where username=? and password=?";
    private static final String SET_PASSWORD = "update "
            + "Users "
            + "set password=? "
            + "where username=?";
    private static final String DELETE_REF = "delete "
            + "MissionRefs "
            + "where avengerUsername=?";

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<UserDTO> getAll() throws Exception {
        List<UserDTO> dtos = null;
        String username, alias, realname;
        UserDTO dto;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
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

    public List<UserDTO> findByLikeAlias(String searchAlias) throws Exception {
        List<UserDTO> dtos = null;
        String username, alias, realname;
        UserDTO dto;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_LIKE_ALIAS);
            preparedStatement.setString(1, "%" + searchAlias + "%");
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

    public UserDTO getByUsername(String username) throws Exception {
        UserDTO dto = null;
        String alias, realname, description;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(GET_BY_USERNAME);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                alias = resultSet.getString("alias");
                realname = resultSet.getString("realname");
                description = resultSet.getString("description");
                dto = new UserDTO(username, alias, realname, description);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean insert(String username, String password, String alias, String realname) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, alias);
            preparedStatement.setString(4, realname);
            check = preparedStatement.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean update(String username, String alias, String realname, String description) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(4, username);
            preparedStatement.setString(1, alias);
            preparedStatement.setString(2, realname);
            preparedStatement.setString(3, description);
            check = preparedStatement.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean delete(String username) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareCall(DELETE_REF);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            connection.commit();
            check = true;
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            closeConnection();
        }
        return check;
    }

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

    public boolean setPassword(String username, String password) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(SET_PASSWORD);
            preparedStatement.setString(2, username);
            preparedStatement.setString(1, password);
            check = preparedStatement.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        return check;
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
