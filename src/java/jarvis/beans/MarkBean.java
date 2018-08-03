package jarvis.beans;

import jarvis.daos.MarkDAO;
import jarvis.dtos.MarkDTO;
import jarvis.dtos.MarkSearchDTO;
import jarvis.errorobjects.MarkErrorObject;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Chris Tran
 */
public class MarkBean implements Serializable {

    private String markCode;
    private String markName;
    private String armorType;
    private String description;

    private String searchMarkName;

    public MarkBean() {
    }

    public void setMarkCode(String markCode) {
        if (markCode != null) {
            if (markCode.trim().isEmpty()
                    || !markCode.trim().isEmpty() && !Pattern.matches("^MARK M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$", markCode.trim())) {
                return;
            }
        }
        this.markCode = markCode;
    }

    public void setMarkName(String markName) {
        if (markName != null && !Pattern.matches("^[\\w ]{4,50}$", markName.trim())) {
            return;
        }
        this.markName = markName;
    }

    public void setArmorType(String armorType) {
        if (armorType != null && !Pattern.matches("^[\\w ]{4,50}$", armorType.trim())) {
            return;
        }
        this.armorType = armorType;
    }

    public void setDescription(String description) {
        if (description != null && !Pattern.matches("^.{4,250}$", description.trim())) {
            return;
        }
        this.description = description;
    }

    public String getMarkCode() {
        return markCode;
    }

    public MarkDTO getMarkDTO() {
        return new MarkDTO(markCode, markName, armorType, description);
    }

    public MarkSearchDTO getMarkSearchDTO() {
        return new MarkSearchDTO(searchMarkName);
    }

    public MarkErrorObject validateInsert() {
        MarkErrorObject markErrorObject = new MarkErrorObject();
        if (markCode == null) {
            markErrorObject.setMarkCodeError("Code is required and must be formed as 'MARK <roman number>'. Ex: MARK IX");
        }
        if (markName == null) {
            markErrorObject.setMarkNameError("Name is required, contains 4-50 alphabet characters and underscore '_'");
        }
        if (armorType == null) {
            markErrorObject.setArmorTypeError("Armor Type is required, contains 4-50 alphabet characters and underscore '_'");
        }
        return markErrorObject.confirm();
    }

    public MarkErrorObject validateUpdate() {
        MarkErrorObject markErrorObject = new MarkErrorObject();
        if (markCode == null) {
            markErrorObject.setMarkCodeError("Code is required and must be formed as 'MARK <roman number>'. Ex: MARK IX");
        }
        if (markName == null) {
            markErrorObject.setMarkNameError("Name is required, contains 4-50 alphabet characters and underscore '_'");
        }
        if (armorType == null) {
            markErrorObject.setArmorTypeError("Armor Type is required, contains 4-50 alphabet characters and underscore '_'");
        }
        if (description == null) {
            markErrorObject.setDescriptionError("Description is required and contains 4-250 chars");
        }
        return markErrorObject.confirm();
    }

    public String getSearchMarkName() {
        return searchMarkName;
    }

    public void setSearchMarkName(String searchMarkName) {
        if (searchMarkName != null && !Pattern.matches("^[\\w ]{1,50}$", searchMarkName.trim())) {
            return;
        }
        this.searchMarkName = searchMarkName;
    }

    public List<MarkDTO> getAll() throws Exception {
        MarkDAO dao = new MarkDAO();
        return dao.getAll();
    }

    public List<MarkDTO> search() throws Exception {
        MarkDAO dao = new MarkDAO();
        return dao.findByLikeMarkName(searchMarkName);
    }

    public MarkDTO getByMarkCode() throws Exception {
        MarkDAO dao = new MarkDAO();
        return dao.getByMarkCode(markCode);
    }

    public boolean insert() throws Exception {
        MarkDAO dao = new MarkDAO();
        return dao.insert(markCode, markName, armorType);
    }

    public boolean update() throws Exception {
        MarkDAO dao = new MarkDAO();
        return dao.update(markCode, markName, armorType, description);
    }

    public boolean delete() throws Exception {
        MarkDAO dao = new MarkDAO();
        return dao.delete(markCode);
    }

}
