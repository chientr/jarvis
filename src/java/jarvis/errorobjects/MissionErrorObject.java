package jarvis.errorobjects;

import java.io.Serializable;

/**
 *
 * @author Chris Tran
 */
public class MissionErrorObject implements Serializable {

    private String missionCodeError;
    private String missionNameError;
    private String locationError;
    private String descriptionError;

    public String getMissionCodeError() {
        return missionCodeError;
    }

    public void setMissionCodeError(String missionCodeError) {
        this.missionCodeError = missionCodeError;
    }

    public String getMissionNameError() {
        return missionNameError;
    }

    public void setMissionNameError(String missionNameError) {
        this.missionNameError = missionNameError;
    }

    public String getLocationError() {
        return locationError;
    }

    public void setLocationError(String locationError) {
        this.locationError = locationError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public MissionErrorObject confirm() {
        if (missionCodeError != null
                || missionNameError != null
                || locationError != null
                || descriptionError != null) {
            return this;
        }
        return null;
    }
}
