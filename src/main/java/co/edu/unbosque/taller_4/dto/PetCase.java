package co.edu.unbosque.taller_4.dto;

public class PetCase {

    private int case_id;
    private String created_at;
    private String type;
    private String description;
    private int pet_id;

    public PetCase(int visit_id, String created_at, String type, String description, int pet_id) {
        this.case_id =visit_id;
        this.created_at=created_at;
        this.type=type;
        this.description=description;
        this.pet_id=pet_id;
    }

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }
}
