package co.edu.unbosque.taller_4.dto;

public class Visit {

    private int visit_id;
    private String created_at;
    private String type;
    private String description;
    private String vet_id;
    private int pet_id;

    public Visit(int visit_id, String created_at, String type, String description, String vet_id, int pet_id) {
        this.visit_id=visit_id;
        this.created_at=created_at;
        this.type=type;
        this.description=description;
        this.vet_id=vet_id;
        this.pet_id=pet_id;
    }

    public int getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(int visit_id) {
        this.visit_id = visit_id;
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

    public String getVet_id() {
        return vet_id;
    }

    public void setVet_id(String vet_id) {
        this.vet_id = vet_id;
    }

    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }
}
