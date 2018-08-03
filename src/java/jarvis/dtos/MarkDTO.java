package jarvis.dtos;

import java.io.Serializable;

/**
 *
 * @author Chris Tran
 */
public class MarkDTO implements Serializable {

    private String markCode;
    private String markName;
    private String armorType;
    private String description;

    public MarkDTO(String markCode, String markName, String armorType) {
        this.markCode = markCode;
        this.markName = markName;
        this.armorType = armorType;
    }

    public MarkDTO(String markCode, String markName, String armorType, String description) {
        this.markCode = markCode;
        this.markName = markName;
        this.armorType = armorType;
        this.description = description;
    }

    public String getMarkCode() {
        return markCode;
    }

    public void setMarkCode(String markCode) {
        this.markCode = markCode;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public String getArmorType() {
        return armorType;
    }

    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
