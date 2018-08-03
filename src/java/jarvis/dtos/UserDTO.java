package jarvis.dtos;

import java.io.Serializable;

/**
 *
 * @author Chris Tran
 */
public class UserDTO implements Serializable {

    private String username;
    private String alias;
    private String realname;
    private String description;

    public UserDTO(String username, String alias, String realname) {
        this.username = username;
        this.alias = alias;
        this.realname = realname;
    }

    public UserDTO(String username, String alias, String realname, String description) {
        this.username = username;
        this.alias = alias;
        this.realname = realname;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
