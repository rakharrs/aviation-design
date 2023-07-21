package controller;

import java.io.IOException;
import java.sql.Connection;

import connection.DAC;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.vol.Reservation;
import model.vol.Vol;

public class Client extends HttpServlet{

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            Connection con = DAC.getConnection();
            Vol[] vols = Vol.list_place_disponible(con);
            Reservation[] reservations = Reservation.list_actif(con);

            req.setAttribute("vols", vols);
            req.setAttribute("reservations", reservations);
            con.close();
            RequestDispatcher rd = req.getRequestDispatcher("home/client.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(resp.getWriter());
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
