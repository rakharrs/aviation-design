package model.vol;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Lieu {
    int id;
    String designation;

    public Lieu(){}
    public Lieu(int id, String designation){
        setId(id);
        setDesignation(designation);
    }
    public static Lieu[] list_all(Connection con) throws SQLException{
        Statement statement = con.createStatement();
        String sql = "select * from lieu";
        ResultSet rs = statement.executeQuery(sql);
        List<Lieu> val = new ArrayList<>();
        while(rs.next()){
            int row_id = rs.getInt("id");
            String row_designation = rs.getString("designation");
            val.add(new Lieu(row_id, row_designation));
        }
        rs.close();
        statement.close();
        return val.toArray(new Lieu[val.size()]);
    }
    
    public int getId() {
        return id;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public void setId(int id) {
        this.id = id;
    }
}
