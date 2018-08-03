package jarvis.beans;

import jarvis.daos.HomeDAO;
import jarvis.daos.UserDAO;
import jarvis.dtos.MissionDTO;
import jarvis.dtos.UserDTO;
import jarvis.errorobjects.UserErrorObject;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Chris Tran
 */
public class HomeBean implements Serializable {

    private String username;
    private String password;
    private String passwordConfirm;
    private String alias;
    private String realname;
    private String description;

    public HomeBean() {
    }

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

    public String getUsername() {
        return username;
    }

    public UserDTO getUserDTO() {
        return new UserDTO(username, alias, realname, description);
    }

    public UserErrorObject validateSignIn() {
        UserErrorObject errorObject = new UserErrorObject();
        if (username == null) {
            errorObject.setUsernameError("Username is required and contains 4-50 alphabet characters or underscore '_'");
        }
        if (password == null) {
            errorObject.setPasswordError("Password is required and contains 8-50 alphabet characters or specials '#?!@$%^&*-'");
        }
        return errorObject.confirm();
    }

    public UserErrorObject validateSignUp() {
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

    public UserErrorObject validateResetPassword() {
        UserErrorObject errorObject = new UserErrorObject();
        if (password == null) {
            errorObject.setPasswordError("Password is required and contains 8-50 alphabet characters or specials '#?!@$%^&*-'");
        }
        if (passwordConfirm == null || (password != null && !passwordConfirm.equals(password))) {
            errorObject.setPasswordConfirmError("Password Confirm must be the same with password");
        }
        return errorObject.confirm();
    }

    public boolean signIn() throws Exception {
        HomeDAO dao = new HomeDAO();
        return dao.signIn(username, password);
    }

    public boolean signUp() throws Exception {
        UserDAO dao = new UserDAO();
        return dao.insert(username, password, alias, realname);
    }

    public UserDTO getByUsername(String sessionUsername) throws Exception {
        UserDAO dao = new UserDAO();
        return dao.getByUsername(sessionUsername);
    }

    public boolean update(String sessionUsername) throws Exception {
        UserDAO dao = new UserDAO();
        return dao.update(sessionUsername, alias, realname, description);
    }

    public boolean delete(String sessionUsername) throws Exception {
        UserDAO dao = new UserDAO();
        return dao.delete(sessionUsername);
    }

    public boolean resetPassword(String sessionUsername) throws Exception {
        UserDAO dao = new UserDAO();
        return dao.setPassword(sessionUsername, password);
    }

    public List<MissionDTO> getMyMissions(String sessionUsername) throws Exception {
        HomeDAO dao = new HomeDAO();
        return dao.getMyMissions(sessionUsername);
    }

    public boolean checkUsernameStillAlive(String sessionUsername) throws Exception {
        HomeDAO dao = new HomeDAO();
        return dao.checkUsernameStillAlive(sessionUsername);
    }

}
