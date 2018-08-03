package jarvis.daos;

import jarvis.dtos.MarkDTO;
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
public class MarkDAO implements Serializable {

    private static final String GET_ALL = "select "
            + "markCode, markName, armorType "
            + "from Marks "
            + "order by markCode";
    private static final String FIND_BY_LIKE_MARKNAME = "select "
            + "markCode, markName, armorType "
            + "from Marks "
            + "where markName like ? "
            + "order by markCode";
    private static final String GET_BY_MARK_CODE = "select "
            + "markName, armorType, description "
            + "from Marks "
            + "where markCode=?";
    private static final String INSERT = "insert "
            + "into Marks(markCode, markName, armorType) "
            + "values(?, ?, ?)";
    private static final String UPDATE = "update "
            + "Marks "
            + "set markName=?, armorType=?, description=? "
            + "where markCode=?";
    private static final String DELETE = "delete "
            + "Marks "
            + "where markCode=?";
    private static final String DELETE_REF = "delete "
            + "MissionRefs "
            + "where markCode=?";

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public List<MarkDTO> getAll() throws Exception {
        List<MarkDTO> dtos = null;
        String markCode, markName, armorType;
        MarkDTO dto;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL);
            resultSet = preparedStatement.executeQuery();
            dtos = new ArrayList<>();
            while (resultSet.next()) {
                markCode = resultSet.getString("markCode");
                markName = resultSet.getString("markName");
                armorType = resultSet.getString("armorType");
                dto = new MarkDTO(markCode, markName, armorType);
                dtos.add(dto);
            }
        } finally {
            closeConnection();
        }
        return dtos;
    }

    public List<MarkDTO> findByLikeMarkName(String searchMarkName) throws Exception {
        List<MarkDTO> dtos = null;
        String markCode, markName, armorType;
        MarkDTO dto;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_LIKE_MARKNAME);
            preparedStatement.setString(1, "%" + searchMarkName + "%");
            resultSet = preparedStatement.executeQuery();
            dtos = new ArrayList<>();
            while (resultSet.next()) {
                markCode = resultSet.getString("markCode");
                markName = resultSet.getString("markName");
                armorType = resultSet.getString("armorType");
                dto = new MarkDTO(markCode, markName, armorType);
                dtos.add(dto);
            }
        } finally {
            closeConnection();
        }
        return dtos;
    }

    public MarkDTO getByMarkCode(String markCode) throws Exception {
        MarkDTO dto = null;
        String markName, armorType, description;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(GET_BY_MARK_CODE);
            preparedStatement.setString(1, markCode);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                markName = resultSet.getString("markName");
                armorType = resultSet.getString("armorType");
                description = resultSet.getString("description");
                dto = new MarkDTO(markCode, markName, armorType, description);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean insert(String markCode, String markName, String armorType) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, markCode);
            preparedStatement.setString(2, markName);
            preparedStatement.setString(3, armorType);
            check = preparedStatement.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean update(String markCode, String markName, String armorType, String description) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(4, markCode);
            preparedStatement.setString(1, markName);
            preparedStatement.setString(2, armorType);
            preparedStatement.setString(3, description);
            check = preparedStatement.executeUpdate() == 1;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean delete(String markCode) throws Exception {
        boolean check = false;
        try {
            connection = DBUtils.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, markCode);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareCall(DELETE_REF);
            preparedStatement.setString(1, markCode);
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
