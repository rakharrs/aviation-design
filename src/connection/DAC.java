package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAC{   // Databasse access connection
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        String url = "jdbc:postgresql://localhost:5432/avion_mi";
        String user = "rakharrs";
        String msp = "pixel";
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(url, user, msp);
        return conn;
    }
}