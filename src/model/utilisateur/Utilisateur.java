package model.utilisateur;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    int id;
    int id_profil;
    String nom;
    String mot_de_passe;

    public Utilisateur(){}
    public Utilisateur(int id, int id_profil, String nom, String mot_de_passe){
        setId(id);
        setId_profil(id_profil);
        setNom(nom);
        setMot_de_passe(mot_de_passe);
    }

    public static Utilisateur getUtilisateur(Connection con, int id_utilisateur) throws SQLException{
        Statement statement = con.createStatement();
        String sql = "select * from utilisateur where id = "+id_utilisateur;
        ResultSet rs = statement.executeQuery(sql);
        List<Utilisateur> val = new ArrayList<>();
        while(rs.next()){
            int row_id = rs.getInt("id");
            int row_id_profil = rs.getInt("id_profil");
            String row_nom = rs.getString("nom");
            String row_mdp = rs.getString("mot_de_passe");
            val.add(new Utilisateur(row_id, row_id_profil, row_nom, row_mdp));
        }
        rs.close();
        statement.close();
        return val.get(0);
    }

    public static Utilisateur getUtilisateur(Connection con, String nom, String mot_de_passe) throws SQLException{
        Statement statement = con.createStatement();
        String sql = "select * from utilisateur where nom = '"+nom+"' and mot_de_passe = '"+mot_de_passe+"'";
        ResultSet rs = statement.executeQuery(sql);
        List<Utilisateur> val = new ArrayList<>();
        while(rs.next()){
            int row_id = rs.getInt("id");
            int row_id_profil = rs.getInt("id_profil");
            String row_nom = rs.getString("nom");
            String row_mdp = rs.getString("mot_de_passe");
            val.add(new Utilisateur(row_id, row_id_profil, row_nom, row_mdp));
        }
        rs.close();
        statement.close();
        return val.get(0);
    }

    public int getId() {
        return id;
    }
    public int getId_profil() {
        return id_profil;
    }
    public String getMot_de_passe() {
        return mot_de_passe;
    }
    public String getNom() {
        return nom;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setId_profil(int id_profil) {
        this.id_profil = id_profil;
    }
    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}
