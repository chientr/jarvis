package jarvis.dtos;

import java.io.Serializable;

/**
 *
 * @author Chris Tran
 */
public class MarkSearchDTO implements Serializable {

    private String searchMarkName;

    public MarkSearchDTO(String searchMarkName) {
        this.searchMarkName = searchMarkName;
    }

    public String getSearchMarkName() {
        return searchMarkName;
    }

    public void setSearchMarkName(String searchMarkName) {
        this.searchMarkName = searchMarkName;
    }

    public boolean confirm() {
        return searchMarkName != null && !searchMarkName.isEmpty();
    }

}
