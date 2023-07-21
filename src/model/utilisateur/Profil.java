package model.utilisateur;

public class Profil {
    int id;
    String designation;

    public Profil(){}
    public Profil(int id, String designation){
        setId(id);
        setDesignation(designation);
    }

    

    public String getDesignation() {
        return designation;
    }
    public int getId() {
        return id;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public void setId(int id) {
        this.id = id;
    }
}
