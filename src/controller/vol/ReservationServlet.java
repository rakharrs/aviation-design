package controller.vol;

import java.io.IOException;
import java.sql.Connection;

import connection.DAC;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.vol.Reservation;
import model.vol.Vol;

public class ReservationServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection con = DAC.getConnection();
            String nom = req.getParameter("nom");
            String id_vol = req.getParameter("id_vol");

            /* Resaka place */
            String affaire = req.getParameter("affaire");
            String place_eco = "0"; String place_affaire = "0";
            if(affaire != null){
                place_affaire = "1";
            }else place_eco = "1";

            //String place_affaire = req.getParameter("place_affaire");
            String date = req.getParameter("date");

            String token = Reservation.save(con, nom, id_vol, place_eco, place_affaire, date);
            resp.sendRedirect("client?token="+token);

        } catch (Exception e) {
           e.printStackTrace(resp.getWriter());
        }
    }
}
