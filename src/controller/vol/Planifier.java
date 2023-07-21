package controller.vol;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.sql.SQLException;

import connection.DAC;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.vol.Reservation;
import model.vol.Vol;

public class Planifier extends HttpServlet{
    @Override // Tsy ilina tsony
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = null;
        try {
            con = DAC.getConnection();
            Vol[] vols = Vol.list_all(con);
            Reservation[] reservations = Reservation.list_all(con);

            req.setAttribute("vols", vols);
            req.setAttribute("reserver", reservations);
            RequestDispatcher rd = req.getRequestDispatcher("home/accueil.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(resp.getWriter());
        }finally{
            if(con != null)
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace(resp.getWriter());
                }
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con;
        try {
            con = DAC.getConnection();
            String id_avion = req.getParameter("id_avion");
            String id_lieu_depart = req.getParameter("id_lieu_depart");
            String id_lieu_arrive = req.getParameter("id_lieu_arrive");
            String duree = req.getParameter("duree");
            String aller_retour = req.getParameter("aller_retour");
            String prix_eco = req.getParameter("prix_eco");
            String prix_affaire = req.getParameter("prix_affaire");
            String debut_vente = req.getParameter("debut_vente");
            String fin_vente = req.getParameter("fin_vente");
            String date_vol = req.getParameter("date_vol");

            int insert = Vol.save(con, id_avion, id_lieu_depart, id_lieu_arrive, duree, aller_retour, prix_eco, prix_affaire, debut_vente, fin_vente, date_vol);
            con.close();
            resp.getWriter().println(insert);
            resp.sendRedirect("/avion-mi/emp");
        } catch (Exception e) {
            e.printStackTrace(resp.getWriter());
        }
    }
    
}
