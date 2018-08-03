package jarvis.dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Chris Tran
 */
public class MissionDTO implements Serializable {

    private String missionCode;
    private String missionName;
    private String location;
    private String description;
    private String state;
    private Date startDate;
    private Date finishDate;

    public MissionDTO(String missionCode, String missionName, String location, String state, Date startDate) {
        this.missionCode = missionCode;
        this.missionName = missionName;
        this.location = location;
        this.state = state;
        this.startDate = startDate;
    }

    public MissionDTO(String missionCode, String missionName, String location, String description, String state, Date startDate, Date finishDate) {
        this.missionCode = missionCode;
        this.missionName = missionName;
        this.location = location;
        this.description = description;
        this.state = state;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public String getMissionCode() {
        return missionCode;
    }

    public void setMissionCode(String missionCode) {
        this.missionCode = missionCode;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

}
