package controller.vol;

import java.io.IOException;
import java.sql.Connection;

import connection.DAC;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.vol.Reservation;

public class ReservationAnnuler extends HttpServlet{
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            String id_reservation = req.getParameter("id_reservation");
            String date_annulation = req.getParameter("date_annulation");
            String n_token = req.getParameter("n_token");
            Connection con = DAC.getConnection();
            int val = Reservation.annuler(con, id_reservation, date_annulation, n_token);
            con.close();
            if(val > 0)
                resp.sendRedirect("/avion-mi/client?success");
            else resp.sendRedirect("/avion-mi/client?error=un echec est survenu");
        } catch (Exception e) {
            resp.sendRedirect("/avion-mi/client?error="+e.getMessage());
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    
}
