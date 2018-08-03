package jarvis.beans;

import jarvis.daos.UserDAO;
import jarvis.dtos.UserDTO;
import jarvis.dtos.UserSearchDTO;
import jarvis.errorobjects.UserErrorObject;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Chris Tran
 */
public class UserBean implements Serializable {

    private String username;
    private String password;
    private String passwordConfirm;
    private String alias;
    private String realname;
    private String description;

    private String searchAlias;

    public void setUsername(String username) {
        if (username != null && !Pattern.matches("^[\\w]{4,50}$", username.trim())) {
            return;
        }
        this.username = username;
    }

    public void setPassword(String password) {
        if (password != null && !Pattern.matches("^[\\w#?!@$%^&*-]{8,50}$", password.trim())) {
            return;
        }
        this.password = password;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setAlias(String alias) {
        if (alias != null && !Pattern.matches("^[\\w ]{4,50}$", alias.trim())) {
            return;
        }
        this.alias = alias;
    }

    public void setRealname(String realname) {
        if (realname != null && !Pattern.matches("^[\\w ]{4,50}$", realname.trim())) {
            return;
        }
        this.realname = realname;
    }

    public void setDescription(String description) {
        if (description != null && !Pattern.matches("^.{4,250}$", description.trim())) {
            return;
        }
        this.description = description;
    }

    public String getSearchAlias() {
        return searchAlias;
    }

    public void setSearchAlias(String searchAlias) {
        if (searchAlias != null && !Pattern.matches("^[\\w ]{1,50}$", searchAlias.trim())) {
            return;
        }
        this.searchAlias = searchAlias;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO getUserDTO() {
        return new UserDTO(username, alias, realname, description);
    }

    public UserSearchDTO getUserSearchDTO() {
        return new UserSearchDTO(searchAlias);
    }

    public UserErrorObject validateInsert() {
        UserErrorObject errorObject = new UserErrorObject();
        if (username == null) {
            errorObject.setUsernameError("Username is required and contains 4-50 alphabet characters or underscore '_'");
        }
        if (password == null) {
            errorObject.setPasswordError("Password is required and contains 8-50 alphabet characters or specials '#?!@$%^&*-'");
        }
        if (passwordConfirm == null || (password != null && !passwordConfirm.equals(password))) {
            errorObject.setPasswordConfirmError("Password Confirm must be the same with password");
        }
        if (alias == null) {
            errorObject.setAliasError("Alias is required and contains 4-50 alphabet characters, space or underscore '_'");
        }
        if (realname == null) {
            errorObject.setRealnameError("Realname is required and contains 4-50 alphabet characters, space or underscore '_'");
        }
        return errorObject.confirm();
    }

    public UserErrorObject validateUpdate() {
        UserErrorObject errorObject = new UserErrorObject();
        if (username == null) {
            errorObject.setUsernameError("Username is required and contains 4-50 alphabet characters or underscore '_'");
        }
        if (alias == null) {
            errorObject.setAliasError("Alias is required and contains 4-50 alphabet characters, space or underscore '_'");
        }
        if (realname == null) {
            errorObject.setRealnameError("Realname is required and contains 4-50 alphabet characters, space or underscore '_'");
        }
        if (description == null) {
            errorObject.setDescriptionError("Description is required and contains 4-250 chars");
        }
        return errorObject.confirm();
    }
    
    public UserErrorObject validateSetPassword() {
        UserErrorObject errorObject = new UserErrorObject();
        if (password == null) {
            errorObject.setPasswordError("Password is required and contains 8-50 alphabet characters or specials '#?!@$%^&*-'");
        }
        if (passwordConfirm == null || (password != null && !passwordConfirm.equals(password))) {
            errorObject.setPasswordConfirmError("Password Confirm must be the same with password");
        }
        return errorObject.confirm();
    }

    public UserDTO getByUsername() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.getByUsername(username);
    }

    public List<UserDTO> getAll() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.getAll();
    }

    public List<UserDTO> search() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.findByLikeAlias(searchAlias);
    }

    public boolean insert() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.insert(username, password, alias, realname);
    }

    public boolean update() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.update(username, alias, realname, description);
    }

    public boolean delete() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.delete(username);
    }

    public boolean resetPassword() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.setPassword(username, password);
    }

}
