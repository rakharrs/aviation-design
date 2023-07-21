package model.vol;

import java.sql.Timestamp;

public class Billet {
    int id;
    int id_vol;
    double prix_eco;
    double prix_affaire;
    Timestamp debut_vente;
    Timestamp fin_vente;

    Vol vol = null;

    public Billet(){}
    public Billet(int id, int id_vol, double prix_eco, double prix_affaire, Timestamp debut_vente, Timestamp fin_vente) throws Exception{
        setId(id);
        setId_vol(id_vol);
        setPrix_eco(prix_eco);
        setPrix_affaire(prix_affaire);
        setDebut_vente(debut_vente);
        setFin_vente(fin_vente);
    }

    public Vol getVol() {
        return vol;
    }
    public Timestamp getDebut_vente() {
        return debut_vente;
    }
    public Timestamp getFin_vente() {
        return fin_vente;
    }
    public int getId() {
        return id;
    }
    public int getId_vol() {
        return id_vol;
    }
    public double getPrix_affaire() {
        return prix_affaire;
    }
    public double getPrix_eco() {
        return prix_eco;
    }
    public void setDebut_vente(Timestamp debut_vente) {
        this.debut_vente = debut_vente;
    }
    public void setFin_vente(Timestamp fin_vente) {
        this.fin_vente = fin_vente;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }
    public void setPrix_affaire(double prix_affaire) throws Exception {
        if(prix_affaire < 0) throw new Exception("La duree ne doit pas être < 0");
        this.prix_affaire = prix_affaire;
    }
    public void setPrix_eco(double prix_eco) throws Exception {
        if(prix_eco < 0) throw new Exception("La duree ne doit pas être < 0");
        this.prix_eco = prix_eco;
    }
    public void setVol(Vol vol) {
        this.vol = vol;
    }
}
