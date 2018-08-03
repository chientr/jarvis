package jarvis.dtos;

import java.io.Serializable;

/**
 *
 * @author Chris Tran
 */
public class UserSearchDTO implements Serializable {

    private String searchAlias;

    public UserSearchDTO(String searchAlias) {
        this.searchAlias = searchAlias;
    }

    public String getSearchAlias() {
        return searchAlias;
    }

    public void setSearchAlias(String searchAlias) {
        this.searchAlias = searchAlias;
    }

    public boolean confirm() {
        return searchAlias != null && !searchAlias.isEmpty();
    }

}
