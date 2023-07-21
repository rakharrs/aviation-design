package model.vol;

import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import helper.Misc;

public class Vol {
    int id;
    int id_avion;
    int id_lieu_depart;
    int id_lieu_arrive;
    int duree;
    boolean aller_retour;
    String lieu_depart;     // view: v_vol
    String lieu_arrive;     // view: v_vol
    String nom_avion;

    int place_affaire;      // view: v_vol
    int place_economique;   // view: v_vol

    double prix_eco;
    double prix_affaire;
    Timestamp debut_vente;
    Timestamp fin_vente;
    Timestamp date_vol;
    
    public String aller_retour(){
        if(isAller_retour()) return "Aller retour";
        return "Aller simple";
    }
    public Vol(){}
    public Vol(int id, int id_avion, int id_lieu_arrive, int id_lieu_depart, int duree, boolean aller_retour, int place_affaire, int place_eco, double prix_eco, 
               double prix_affaire, Timestamp debut_vente, Timestamp fin_vente, Timestamp date_vol) throws Exception{
        setId(id);
        setId_lieu_arrive(id_lieu_arrive);
        setPlace_affaire(place_affaire);
        setPlace_economique(place_economique);
        setId_lieu_depart(id_lieu_depart);
        setDuree(duree);
        setAller_retour(aller_retour);
        setPrix_eco(prix_eco);
        setPrix_affaire(prix_affaire);
        setDebut_vente(debut_vente);
        setFin_vente(fin_vente);
        setDate_vol(date_vol);
        setId_avion(id_avion);
    }
    public Vol(int id, int id_avion, String nom_avion, int id_lieu_arrive, int id_lieu_depart, int duree, boolean aller_retour, 
               int place_affaire, int place_eco, 
               double prix_eco, double prix_affaire, Timestamp debut_vente, Timestamp fin_vente, 
               Timestamp date_vol,
               String lieu_depart, String lieu_arrive) throws Exception{
        setNom_avion(nom_avion);
        setId(id);
        setPlace_affaire(place_affaire);
        setPlace_economique(place_eco);
        setId_lieu_arrive(id_lieu_arrive);
        setId_lieu_depart(id_lieu_depart);
        setDuree(duree);
        setAller_retour(aller_retour);
        setLieu_arrive(lieu_arrive);
        setLieu_depart(lieu_depart);
        setPrix_eco(prix_eco);
        setPrix_affaire(prix_affaire);
        setDebut_vente(debut_vente);
        setFin_vente(fin_vente);
        setDate_vol(date_vol);
        setId_avion(id_avion);
    }

    public static int valider(Connection con,int id_vol,Date date) throws Exception{
        String sql="insert into vol_valider(id_vol,date_validation) values (?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id_vol);
        statement.setDate(2, date);
        int val = statement.executeUpdate();
        statement.close();
        return val;
    }

    public static Vol getVol(Vol[] vols, int id_vol){
        for(Vol vol : vols){
            if(vol.getId() == id_vol) return vol;
        }return null;
    }

    public static Vol[] list_by_view(Connection con, String view_name) throws Exception{
        Statement statement = con.createStatement();
        String sql = "select * from "+view_name;
        ResultSet rs = statement.executeQuery(sql);
        List<Vol> val = new ArrayList<>();
        while(rs.next()){
            int row_id = rs.getInt("id");
            int row_id_avion = rs.getInt("id_avion");
            int row_id_lieu_arrive = rs.getInt("id_lieu_arrive");
            int row_id_lieu_depart = rs.getInt("id_lieu_depart");
            int row_duree = rs.getInt("duree");
            boolean row_aller_retour = rs.getBoolean("aller_retour");

            double row_prix_eco = rs.getDouble("prix_eco");
            double row_prix_affaire = rs.getDouble("prix_affaire");
            Timestamp row_debut_vente = rs.getTimestamp("debut_vente");
            Timestamp row_fin_vente = rs.getTimestamp("fin_vente");
            Timestamp row_date_vol = rs.getTimestamp("date_vol");
            String row_lieu_depart = rs.getString("lieu_depart");
            String row_lieu_arrive = rs.getString("lieu_arrive");
            String row_nom_avion = rs.getString("nom_avion");

            int row_place_eco = rs.getInt("place_economique");
            int row_place_affaire = rs.getInt("place_affaire");

            val.add(new Vol(row_id, row_id_avion, row_nom_avion, row_id_lieu_arrive, row_id_lieu_depart, row_duree, row_aller_retour, row_place_affaire, row_place_eco, row_prix_eco, row_prix_affaire, row_debut_vente, row_fin_vente, row_date_vol, row_lieu_depart, row_lieu_arrive));
        }
        rs.close();
        statement.close();
        return val.toArray(new Vol[val.size()]);
    }
    // function(s)
    public static Vol[] list_all(Connection con) throws Exception{
        return list_by_view(con, "v_vol");
    }

    public static Vol[] list_valider(Connection con) throws Exception{
        return list_by_view(con,"v_vol_valider");
    }

    public static Vol[] list_invalide(Connection con) throws Exception{
        return list_by_view(con,"v_vol_nonvalider");
    }

    public static Vol[] list_place_disponible(Connection con) throws Exception{
        return list_by_view(con,"v_vol_disponible");
    }
    public static int save(
    Connection con,
    int id_avion,
    int id_lieu_depart,
    int id_lieu_arrive,
    int duree,
    boolean aller_retour,
    double prix_eco,
    double prix_affaire,
    Timestamp debut_vente,
    Timestamp fin_vente,
    Timestamp date_vol) throws SQLException{

        String sql = "insert into vol(id_avion, id_lieu_depart, id_lieu_arrive, duree, aller_retour, prix_eco, prix_affaire, debut_vente, fin_vente, date_vol) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id_avion);
        statement.setInt(2, id_lieu_depart);
        statement.setInt(3, id_lieu_arrive);
        statement.setInt(4, duree);
        statement.setBoolean(5, aller_retour);
        statement.setDouble(6, prix_eco);
        statement.setDouble(7, prix_affaire);
        statement.setTimestamp(8, debut_vente);
        statement.setTimestamp(9, fin_vente);
        statement.setTimestamp(10, date_vol);
        int val = statement.executeUpdate();
        statement.close();
        return val;
    }

    /**
     * @author Yoann
     * @param nb_place_eco  nombre de place economique demandé
     * @param nb_place_affaire  nombre de placee affaire demandé
     * @return  Le prix du bail en double
     * @throws Exception
     */
    public double get_Prix(Connection con, int nb_place_eco, int nb_place_affaire) throws Exception{
        Promotion promotion = Promotion.getPromotion(con, getId());
        if(promotion == null) promotion = new Promotion();
        double prix_affaire = (getPrix_affaire() * nb_place_affaire); 
        double prix_eco = (getPrix_eco() * nb_place_eco);

        // Resaka remise sy promotion
        if(is_remiseable(con, promotion, true))   // buisness class
            prix_affaire -= ((getPrix_affaire() * promotion.getRemise_affaire() / 100) * (nb_place_affaire));
        if(is_remiseable(con, promotion, false))    // eco class
            prix_eco -= ((getPrix_eco() * promotion.getRemise_eco() / 100) * (nb_place_eco));

        return prix_affaire + prix_eco;
    }

    public void get_remise(Connection con, boolean is_affaire) throws SQLException{
        Promotion promotion = Promotion.getPromotion(con, getId());
        if(promotion == null) promotion = new Promotion();
        
    }


    public boolean is_remiseable(Connection con, Promotion promotion, boolean is_affaire) throws Exception{
        Vol v1 = Vol.getVol(con, getId(), "v_vol_disponible");      // angalana place disponible
        Vol v2 = Vol.getVol(con,getId(), "v_vol");                  // angalana place totale
        if(is_affaire){
            int place_restante_affaire = v1.getPlace_affaire();
            int place_totale_affaire = v2.getPlace_affaire();
            int place_prise_affaire = place_totale_affaire - place_restante_affaire;
            double nb_place_affaire_valable_promotion = place_totale_affaire * promotion.getPourcentageaffaire() / 100;
            if(nb_place_affaire_valable_promotion >= place_prise_affaire)
                return true;
        }else{
            int place_restante_economique = v1.getPlace_economique();
            int place_totale_economique = v2.getPlace_economique();
            int place_prise_economique = place_totale_economique - place_restante_economique;
            double nb_place_economique_valable_promotion = place_totale_economique * promotion.getPourcentageeconomique() / 100;
            if(nb_place_economique_valable_promotion >= place_prise_economique)
                return true;
        }
        return false;
    }

    public static int save(
        Connection con,
        String id_avion,
        String id_lieu_depart,
        String id_lieu_arrive,
        String duree,
        String aller_retour,
        String prix_eco,
        String prix_affaire,
        String debut_vente,
        String fin_vente,
        String date_vol
    ) throws NumberFormatException, SQLException, ParseException{
        boolean b_aller_retour = false;     // Variable determinant le checkbox
        if(aller_retour == null)
            b_aller_retour = false;
        else b_aller_retour = true;
        return save(con, Integer.parseInt(id_avion), Integer.parseInt(id_lieu_depart), Integer.parseInt(id_lieu_arrive), 
                    Integer.parseInt(duree), b_aller_retour, Double.parseDouble(prix_eco), Double.parseDouble(prix_affaire), 
                    Misc.toTimestamp(debut_vente), Misc.toTimestamp(fin_vente), Misc.toTimestamp(date_vol));
    }

    public static Vol getVol(Connection con, int id_vol) throws Exception{
        Vol[] all_vol = Vol.list_all(con);
        for (Vol vol : all_vol)
            if(vol.getId() == id_vol) return vol;
        return null;
    }

    public static Vol getVol(Connection con, int id_vol, String view) throws Exception{
        Vol[] vols = Vol.list_by_view(con, view);
        for (Vol vol : vols) {
            if(vol.getId() == id_vol) return vol;
        }return null;
    }

    public Timestamp getDate_vol() {
        return date_vol;
    }
    public void setDate_vol(Timestamp date_vol) {
        this.date_vol = date_vol;
    }

    public Timestamp getDebut_vente() {
        return debut_vente;
    }
    public Timestamp getFin_vente() {
        return fin_vente;

    }
    public int getId_avion() {
        return id_avion;
    }
    public void setId_avion(int id_avion) {
        this.id_avion = id_avion;
    }
    public double getPrix_affaire() {
        return prix_affaire;
    }
    public double getPrix_eco() {
        return prix_eco;
    }
    public int getDuree() {
        return duree;
    }
    public int getId() {
        return id;
    }
    public int getPlace_affaire() {
        return place_affaire;
    }
    public int getPlace_economique() {
        return place_economique;
    }
    public String getNom_avion() {
        return nom_avion;
    }
    public void setNom_avion(String nom_avion) {
        this.nom_avion = nom_avion;
    }
    public void setPlace_affaire(int place_affaire) {
        this.place_affaire = place_affaire;
    }
    public void setPlace_economique(int place_economique) {
        this.place_economique = place_economique;
    }
    public boolean isAller_retour() {
        return aller_retour;
    }
    public int getId_lieu_arrive() {
        return id_lieu_arrive;
    }
    public int getId_lieu_depart() {
        return id_lieu_depart;
    }
    public String getLieu_arrive() {
        return lieu_arrive;
    }
    public String getLieu_depart() {
        return lieu_depart;
    }
    public void setAller_retour(boolean aller_retour) {
        this.aller_retour = aller_retour;
    }
    public void setDebut_vente(Timestamp debut_vente) {
        this.debut_vente = debut_vente;
    }
    public void setFin_vente(Timestamp fin_vente) {
        this.fin_vente = fin_vente;
    }
    public void setPrix_affaire(double prix_affaire) {
        this.prix_affaire = prix_affaire;
    }
    public void setPrix_eco(double prix_eco) {
        this.prix_eco = prix_eco;
    }
    public void setDuree(int duree) throws Exception {
        if(duree < 0) throw new Exception("La duree ne doit pas être < 0");
        this.duree = duree;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setId_lieu_arrive(int id_lieu_arrive) {
        this.id_lieu_arrive = id_lieu_arrive;
    }
    public void setId_lieu_depart(int id_lieu_depart) {
        this.id_lieu_depart = id_lieu_depart;
    }
    public void setLieu_arrive(String lieu_arrive) {
        this.lieu_arrive = lieu_arrive;
    }
    public void setLieu_depart(String lieu_depart) {
        this.lieu_depart = lieu_depart;
    }

}
