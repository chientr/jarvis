package jarvis.dtos;

import java.io.Serializable;

/**
 *
 * @author Chris Tran
 */
public class MissionSearchDTO implements Serializable {

    private String searchMissionName;
    private String searchLocation;
    private String searchState;

    public MissionSearchDTO(String searchMissionName, String searchLocation, String searchState) {
        this.searchMissionName = searchMissionName;
        this.searchLocation = searchLocation;
        this.searchState = searchState;
    }

    public String getSearchMissionName() {
        return searchMissionName;
    }

    public void setSearchMissionName(String searchMissionName) {
        this.searchMissionName = searchMissionName;
    }

    public String getSearchLocation() {
        return searchLocation;
    }

    public void setSearchLocation(String searchLocation) {
        this.searchLocation = searchLocation;
    }

    public String getSearchState() {
        return searchState;
    }

    public void setSearchState(String searchState) {
        this.searchState = searchState;
    }

    public boolean confirm() {
        return searchMissionName != null
                && ((searchLocation != null && !searchLocation.isEmpty())
                || (searchState != null && !searchState.isEmpty())
                || true);
    }
}
