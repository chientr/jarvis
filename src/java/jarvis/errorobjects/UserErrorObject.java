package jarvis.errorobjects;

import java.io.Serializable;

/**
 *
 * @author Chris Tran
 */
public class UserErrorObject implements Serializable {

    private String usernameError;
    private String passwordError;
    private String passwordConfirmError;
    private String aliasError;
    private String realnameError;
    private String descriptionError;

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPasswordConfirmError() {
        return passwordConfirmError;
    }

    public void setPasswordConfirmError(String passwordConfirmError) {
        this.passwordConfirmError = passwordConfirmError;
    }

    public String getAliasError() {
        return aliasError;
    }

    public void setAliasError(String aliasError) {
        this.aliasError = aliasError;
    }

    public String getRealnameError() {
        return realnameError;
    }

    public void setRealnameError(String realnameError) {
        this.realnameError = realnameError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public UserErrorObject confirm() {
        if (usernameError != null
                || passwordError != null
                || passwordConfirmError != null
                || aliasError != null
                || realnameError != null
                || descriptionError != null) {
            return this;
        }
        return null;
    }
}
