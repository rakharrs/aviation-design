package model.avion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Type_avion {
    private int id;
    private String type;
    public Type_avion(){}
    public Type_avion(int id, String type){
        setId(id);
        setType(type);
    }

    // List all type
    public Type_avion[] list_all(Connection con) throws SQLException{
        Statement statement = con.createStatement();
        String sql = "select * from type_avion";
        ResultSet rs = statement.executeQuery(sql);
        List<Type_avion> val = new ArrayList<>();
        while(rs.next()){
            int row_id = rs.getInt("id");
            String row_type = rs.getString("type");
            val.add(new Type_avion(row_id, row_type));
        }
        rs.close();
        statement.close();
        return val.toArray(new Type_avion[val.size()]);
    }

    public int getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setType(String type) {
        this.type = type;
    }
}
