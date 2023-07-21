import java.sql.Connection;
import java.sql.SQLException;

import connection.DAC;
import model.vol.Promotion;
import model.vol.Reservation;
import model.vol.Vol;

public class Test {
    public static void main(String[] args) throws Exception {
        Connection con = DAC.getConnection();
        Promotion promotions = Promotion.getPromotion(con, 6);
        System.out.println(promotions.getId_vol());
        System.out.println("ok "+promotions.getRemise_eco());
        
        Vol vol = Vol.getVol(con, 6);
        System.out.println(vol.get_Prix(con, 0, 1));
        con.close();
    }
}
