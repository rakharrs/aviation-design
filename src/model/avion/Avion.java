package model.avion;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Avion {
    private int id;
    private String nom;
    private int place_economique;
    private int place_affaire;
    private int id_type;
    private String nom_type;

    public Avion(){}
    public Avion(int id, String nom, int place_economique, int place_affaire) throws Exception{
        setId(id);
        setNom(nom);
        setPlace_affaire(place_affaire);
        setPlace_economique(place_economique);
    }
    public Avion(int id, String nom, int place_economique, int place_affaire, int id_type, String nom_type) throws Exception{
        setId(id);
        setNom(nom);
        setPlace_affaire(place_affaire);
        setPlace_economique(place_economique);
        setNom_type(nom_type);
        setId_type(id_type); 
    }

    public static Avion[] list_all(Connection con) throws Exception{
        Statement statement = con.createStatement();
        String sql = "select * from v_avion";
        ResultSet rs = statement.executeQuery(sql);
        List<Avion> val = new ArrayList<>();
        while(rs.next()){
            int row_id = rs.getInt("id");
            String row_nom = rs.getString("nom");
            int p_eco = rs.getInt("place_economique");
            int p_affaire = rs.getInt("place_affaire");
            int row_id_type = rs.getInt("id_type");
            String row_nom_type = rs.getString("nom_type");
            val.add(new Avion(row_id, row_nom, p_eco, p_affaire, row_id_type, row_nom_type));
        }
        rs.close();
        statement.close();
        return val.toArray(new Avion[val.size()]);
    }

    public Avion[] insertAvion(int id,String nom, int place1, int place2,int id_type,String nom_type,Connection con) throws Exception {
        Statement statement =con.createStatement();
        String sql=("INSERT INTO v_avion (id,nom, place_economique, place_affaire,id_type,type) VALUES (?, ?, ?, ?,?,?)");
        ResultSet rs = statement.executeQuery(sql);
        List<Avion> val = new ArrayList<>();
        while(rs.next()){
            int row_id = rs.getInt("id");
            String row_nom = rs.getString("nom");
            int p_eco = rs.getInt("place_economique");
            int p_affaire = rs.getInt("place_affaire");
            int row_id_type = rs.getInt("id_type");
            String row_nom_type = rs.getString("nom_type");
            val.add(new Avion(row_id, row_nom, p_eco, p_affaire,row_id_type, row_nom_type));
        }
        rs.close();
        statement.close();
        return val.toArray(new Avion[val.size()]);
    }

    

    public int getId() {
        return id;
    }
    public int getId_type() {
        return id_type;
    }
    public String getNom() {
        return nom;
    }
    public String getNom_type() {
        return nom_type;
    }
    public int getPlace_affaire() {
        return place_affaire;
    }
    public int getPlace_economique() {
        return place_economique;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setId_type(int id_type) {
        this.id_type = id_type;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setNom_type(String nom_type) {
        this.nom_type = nom_type;
    }
    public void setPlace_affaire(int place_affaire) throws Exception{
        if(place_affaire < 0) throw new Exception("Place affaire ne doit pas être < 0");
        this.place_affaire = place_affaire;
    }
    public void setPlace_economique(int place_economique) throws Exception{
        if(place_economique < 0) throw new Exception("Place affaire ne doit pas être < 0");
        this.place_economique = place_economique;
    }
}
