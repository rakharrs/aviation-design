package model.vol;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Promotion {
    int id;
    double pourcentageaffaire = 0;
    double pourcentageeconomique = 0;
    double remise_affaire = 0;
    double remise_eco = 0;
    int id_vol;
    
    public Promotion(){}
     public Promotion(int id,double Pourcentageaffaire,double pourcentageeconomique, double remise_affaie, double remise_eco,int id_vol){
        setId(id); 
        setId_vol(id_vol);
        setPourcentageaffaire(pourcentageaffaire);
        setPourcentageeconomique(pourcentageeconomique);
        setRemise_affaire(remise_affaie);
        setRemise_eco(remise_eco);
     } 
    public int getId() {
        return id;
    }
    public double getRemise_affaire() {
        return remise_affaire;
    }
    public double getRemise_eco() {
        return remise_eco;
    }
    public void setRemise_affaire(double remise_affaie) {
        this.remise_affaire = remise_affaie;
    }
    public void setRemise_eco(double remise_eco) {
        this.remise_eco = remise_eco;
    }
    public int getId_vol() {
        return id_vol;
    }
    public double getPourcentageaffaire() {
        return pourcentageaffaire;
    }

    public double getPourcentageeconomique() {
        return pourcentageeconomique;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }
    public void setPourcentageaffaire(double pourcentageaffaire) {
        this.pourcentageaffaire = pourcentageaffaire;
    }

    public void setPourcentageeconomique(double pourcentageeconomique) {
        this.pourcentageeconomique = pourcentageeconomique;
    }

    public Promotion getPromotion(Connection con, String id_vol) throws NumberFormatException, SQLException{
        return getPromotion(con, Integer.parseInt(id_vol));
    }

    public static Promotion getPromotion(Connection con, int id_vol) throws SQLException{
        Promotion [] promotions = list_all(con);
        for (Promotion promotion : promotions)
            if(promotion.getId_vol() == id_vol)
                return promotion;
        return null;
    }

    public static Promotion [] list_all(Connection con) throws SQLException{
        Statement statement = con.createStatement();
        String sql="Select * from promotion";
        ResultSet rs = statement.executeQuery(sql);
        ArrayList<Promotion> list = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            int id_vol =rs.getInt("id_vol");
            double pourcentageaffaire = rs.getDouble("pourcentageaffaire");
            double pourcentageeconomique = rs.getDouble("pourcentageeconomique");

            double remise_affaie = rs.getDouble("remise_affaire");
            double remise_eco = rs.getDouble("remise_eco");

            list.add(new Promotion(id, pourcentageaffaire, pourcentageeconomique, remise_affaie, remise_eco, id_vol));
        }
        return list.toArray(new Promotion[list.size()]);
    }



    
}
