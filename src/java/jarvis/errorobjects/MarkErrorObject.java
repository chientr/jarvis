package jarvis.errorobjects;

import java.io.Serializable;

/**
 *
 * @author Chris Tran
 */
public class MarkErrorObject implements Serializable {

    private String markCodeError;
    private String markNameError;
    private String armorTypeError;
    private String descriptionError;

    public MarkErrorObject() {
    }

    public String getMarkCodeError() {
        return markCodeError;
    }

    public void setMarkCodeError(String markCodeError) {
        this.markCodeError = markCodeError;
    }

    public String getMarkNameError() {
        return markNameError;
    }

    public void setMarkNameError(String markNameError) {
        this.markNameError = markNameError;
    }

    public String getArmorTypeError() {
        return armorTypeError;
    }

    public void setArmorTypeError(String armorTypeError) {
        this.armorTypeError = armorTypeError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public MarkErrorObject confirm() {
        if (markCodeError != null
                || markNameError != null
                || armorTypeError != null
                || descriptionError != null) {
            return this;
        }
        return null;
    }
}
