package model.vol;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import helper.Misc;

public class Reservation {
    int id ;
    String token;
    String nom;
    int id_vol;
    int place_eco;
    int place_affaire;
    double prix;
    Timestamp date;

    public Reservation(){}
    public Reservation(int id,String token,String nom,int id_vol,int place_eco, int place_affaire,double prix, Timestamp date){
        setId(id);
        setNom(nom);
        setToken(token);
        setId_vol(id_vol);
        setPrix(prix);
        setDate(date);
        setPlace_affaire(place_affaire);
        setPlace_eco(place_eco);
    }

    public static int annuler(Connection con, String id_reservation, String date_annulation, String n_token)throws Exception{
        return annuler(con, Integer.parseInt(id_reservation), Misc.toTimestamp(date_annulation), n_token);
    }

    public static int annuler(Connection con, int id_reservation, Timestamp date_annulation, String n_token) throws Exception{
        Reservation reservation = getReservation(con, id_reservation);
        if(reservation.getToken().equals(n_token))
            return annuler(con, id_reservation, date_annulation);
        else throw new Exception("Token invalid");
    }


    public static int annuler(Connection con, int id_reservation, Timestamp date_annulation) throws SQLException{
        String sql = "insert into reservation_annuler(id_reservation, date_annulation) values(?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id_reservation);
        statement.setTimestamp(2, date_annulation);
        int val = statement.executeUpdate();
        statement.close();
        return val;
    }

    public static Reservation getReservation(Connection con, int id_reservation) throws SQLException{
        Reservation[] reservations = list_all(con);
        for (Reservation reservation : reservations) {
            if(reservation.getId() == id_reservation) return reservation;
        }return null;
    }

    public static int save(
        Connection con,
        String token,
        String nom,
        int id_vol,
        int place_eco, 
        int place_affaire,
        double prix, 
        Timestamp date
        ) throws SQLException{
            String sql = "insert into reservation(token, nom, id_vol, place_eco, place_affaire, prix, date) values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, token);
            statement.setString(2, nom);
            statement.setInt(3, id_vol);
            statement.setInt(4, place_eco);
            statement.setInt(5, place_affaire);
            statement.setDouble(6, prix);
            statement.setTimestamp(7, date);;
            int val = statement.executeUpdate();
            statement.close();
            return val;
    }

    public static String save(
        Connection con,
        String nom,
        int id_vol,
        int place_eco, 
        int place_affaire,
        double prix, 
        Timestamp date
    ){
        String new_token = Misc.generateToken();
        try {
            int val = save(con, new_token, nom, id_vol, place_eco, place_affaire, prix, date);
            if(val > 0)
                return new_token;
            else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String save(
        Connection con,
        String nom,
        String id_vol,
        String place_eco, 
        String place_affaire,
        String date
    ) throws NumberFormatException, Exception{
        Vol vol = Vol.getVol(Vol.list_all(con), Integer.parseInt(id_vol));
        return save(con, nom, id_vol, place_eco, place_affaire, 
        vol.get_Prix(con, Integer.parseInt(place_eco), Integer.parseInt(place_affaire)), date);
    }
    public static String save(
        Connection con,
        String nom,
        String id_vol,
        String place_eco, 
        String place_affaire,
        String prix, 
        String date
    ) throws NumberFormatException, ParseException{
        return save(con, nom, Integer.parseInt(id_vol), Integer.parseInt(place_eco),Integer.parseInt(place_affaire),Double.parseDouble(prix),Misc.toTimestamp(date));
    }

    public static String save(
        Connection con,
        String nom,
        String id_vol,
        String place_eco, 
        String place_affaire,
        double prix, 
        String date
    ) throws NumberFormatException, ParseException{
        return save(con, nom, Integer.parseInt(id_vol), Integer.parseInt(place_eco),Integer.parseInt(place_affaire),prix,Misc.toTimestamp(date));
    }

    public static Reservation[] list_by_view(Connection con, String view) throws SQLException{
        Statement statement = con.createStatement();
        String sql = "select * from "+view;
        ResultSet rs = statement.executeQuery(sql);
        List<Reservation> val = new ArrayList<>();
        while(rs.next()){
            int row_id = rs.getInt("id");
            String row_token = rs.getString("token");
            String row_nom = rs.getString("nom");
            int row_id_vol = rs.getInt("id_vol");
            double row_prix = rs.getDouble("prix");
            int row_place_eco = rs.getInt("place_eco");
            int row_place_affaire = rs.getInt("place_affaire");
            Timestamp row_date = rs.getTimestamp("date");

            val.add(new Reservation(row_id, row_token, row_nom, row_id_vol, row_place_eco, row_place_affaire, row_prix, row_date));
        }
        rs.close();
        statement.close();
        return val.toArray(new Reservation[val.size()]);
    }

    public static Reservation[] list_all(Connection con) throws SQLException{
        return list_by_view(con, "reservation");
    }  

    public static Reservation[] list_actif(Connection con) throws SQLException{
        return list_by_view(con, "v_reservation_actif");
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public void setPlace_affaire(int place_affaire) {
        if(place_affaire < 0) throw new IllegalArgumentException("doit pas être < 0");
        this.place_affaire = place_affaire;
    }
    public void setPlace_eco(int place_eco) {
        if(place_eco < 0) throw new IllegalArgumentException("doit pas être < 0");
        this.place_eco = place_eco;
    }
    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public int getId() {
        return id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
    public int getPlace_affaire() {
        return place_affaire;
    }
    public int getPlace_eco() {
        return place_eco;
    }
    public int getId_vol() {
        return id_vol;
    }
    public double getPrix() {
        return prix;
    }

    public Timestamp getDate() {
        return date;
    }
    public String getToken() {
        return token;
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }
    public void setToken(String token) {
        this.token = token;
    }
    
}
