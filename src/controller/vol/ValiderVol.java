package controller.vol;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.Date;

import connection.DAC;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.vol.Vol;

public class ValiderVol extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_vol = Integer.parseInt(req.getParameter("id_vol"));
        Date r_date = new Date(new java.util.Date().getTime());
        Connection con;
        try {
            con = DAC.getConnection();
            Vol.valider(con, id_vol, r_date);
            con.close();
            resp.sendRedirect("admin?success");
        } catch (Exception e) {
            e.printStackTrace(resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        int id_vol = Integer.parseInt(req.getParameter("id_vol"));
        Date r_date = Date.valueOf(date);
        Connection con;
        try {
            con = DAC.getConnection();
            Vol.valider(con, id_vol, r_date);
            con.close();
            resp.sendRedirect("admin?success");
        } catch (Exception e) {
            e.printStackTrace(resp.getWriter());
        }
    }
    
}
