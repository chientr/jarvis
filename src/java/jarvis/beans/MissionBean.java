package jarvis.beans;

import jarvis.daos.MissionDAO;
import jarvis.dtos.MissionDTO;
import jarvis.dtos.MissionSearchDTO;
import jarvis.dtos.UserDTO;
import jarvis.errorobjects.MissionErrorObject;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Chris Tran
 */
public class MissionBean implements Serializable {

    private String missionCode;
    private String missionName;
    private String searchMissionName;
    private String searchLocation;
    private String searchState;
    private String location;
    private String description;
    private String state;
    private Date startDate;
    private Date finishDate;

    private String avengerUsername;
    private String markCode;

    public MissionBean() {
    }

    public MissionDTO getMissionDTO() {
        return new MissionDTO(missionCode, missionName, location, description, state, startDate, finishDate);
    }

    // Setters
    public void setMissionCode(String missionCode) {
        if (missionCode != null && !Pattern.matches("^[A-Z]{5}-[0-9]{1,45}$", missionCode)) {
            return;
        }
        this.missionCode = missionCode;
    }

    public void setMissionName(String missionName) {
        if (missionName != null && !Pattern.matches("^[\\w ]{4,50}$", missionName)) {
            return;
        }
        this.missionName = missionName;
    }

    public void setSearchMissionName(String searchMissionName) {
        if (searchMissionName != null && !Pattern.matches("^[\\w ]{0,50}$", searchMissionName)) {
            return;
        }
        this.searchMissionName = searchMissionName;
    }

    public void setSearchLocation(String searchLocation) {
        if (searchLocation != null && !Pattern.matches("^[\\w ]{1,50}$", searchLocation)) {
            return;
        }
        this.searchLocation = searchLocation;
    }

    public void setSearchState(String searchState) {
        if (searchState != null && !Pattern.matches("^[\\w ]{1,50}$", searchState)) {
            return;
        }
        this.searchState = searchState;
    }

    public void setLocation(String location) {
        if (location != null && !Pattern.matches("^[\\w ]{1,50}$", location)) {
            return;
        }
        this.location = location;
    }

    public void setDescription(String description) {
        if (description != null && !Pattern.matches("^.{4,250}$", description)) {
            return;
        }
        this.description = description;
    }

    public void setAvengerUsername(String avengerUsername) {
        if (avengerUsername != null && !Pattern.matches("^[\\w]{4,50}$", avengerUsername.trim())) {
            return;
        }
        this.avengerUsername = avengerUsername;
    }

    public void setMarkCode(String markCode) {
        if (markCode != null && !Pattern.matches("^MARK M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$", markCode.trim())) {
            return;
        }
        this.markCode = markCode;
    }

    // Getters
    public String getMissionCode() {
        return missionCode;
    }

    public String getMissionName() {
        return missionName;
    }

    public String getSearchMissionName() {
        return searchMissionName;
    }

    public String getSearchLocation() {
        return searchLocation;
    }

    public String getSearchState() {
        return searchState;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public String getAvengerUsername() {
        return avengerUsername;
    }

    public String getMarkCode() {
        return markCode;
    }

    public MissionSearchDTO getMissionSearchDTO() {
        return new MissionSearchDTO(searchMissionName, searchLocation, searchState);
    }

    public MissionErrorObject validateInsert() {
        MissionErrorObject missionErrorObject = new MissionErrorObject();
        if (missionCode == null) {
            missionErrorObject.setMissionCodeError("Code is required, 50 characters max and must be formed as AAAAA-<number>. Ex: EAGLE-321");
        }
        if (missionName == null) {
            missionErrorObject.setMissionNameError("Name is required, contains 5-50 alphabet characters, space underscore '_'");
        }
        if (location == null) {
            missionErrorObject.setLocationError("Location is required, select from map");
        }
        return missionErrorObject.confirm();
    }

    public MissionErrorObject validateUpdate() {
        MissionErrorObject missionErrorObject = new MissionErrorObject();
        if (missionCode == null) {
            missionErrorObject.setMissionCodeError("Code is required, 50 characters max and must be formed as AAAAA-<number>. Ex: EAGLE-321");
        }
        if (missionName == null) {
            missionErrorObject.setMissionNameError("Name is required, contains 4-50 alphabet characters, space underscore '_'");
        }
        if (location == null) {
            missionErrorObject.setLocationError("Location is required, select from map");
        }
        if (description == null) {
            missionErrorObject.setDescriptionError("Description is required and contains 4-250 chars");
        }
        return missionErrorObject.confirm();
    }

    public List<MissionDTO> getAll() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.getAll();
    }

    public List<MissionDTO> search() throws Exception {
        MissionDAO dao = new MissionDAO();
        if (searchMissionName != null) {
            if (searchLocation != null && !searchLocation.isEmpty()) {
                if (searchState != null && !searchState.isEmpty()) {
                    return dao.findByNameAndLocationAndState(searchMissionName, searchLocation, searchState);
                }
                return dao.findByNameAndLocation(searchMissionName, searchLocation);
            }
            if (searchState != null && !searchState.isEmpty()) {
                return dao.findByNameAndState(searchMissionName, searchState);
            }
            return dao.findByName(searchMissionName);
        }
        return null;
    }

    public boolean insert() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.insert(missionCode, missionName, location);
    }

    public MissionDTO getByMissionCode() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.getByMissionCode(missionCode);
    }

    public boolean update() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.update(missionCode, missionName, location, description);
    }

    public boolean delete() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.delete(missionCode);
    }

    public String retrieveState() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.retrieveState(missionCode);
    }

    public boolean finish() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.finish(missionCode);
    }

    public List<String> getAllLocations() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.getAllLocations();
    }

    public boolean addUser() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.addUser(missionCode, avengerUsername);
    }

    public boolean addUserWithMark() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.addUserWithMark(missionCode, avengerUsername, markCode);
    }

    public boolean removeUser() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.removeUser(missionCode, avengerUsername);
    }

    public List<UserDTO> getFreeUsers() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.getFreeUsers();
    }

    public List<UserDTO> getCurrentUsers() throws Exception {
        MissionDAO dao = new MissionDAO();
        return dao.getCurrentUsers(missionCode);
    }
}
