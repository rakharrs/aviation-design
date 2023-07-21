package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.DAC;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.avion.Avion;
import model.utilisateur.Utilisateur;
import model.vol.Lieu;
import model.vol.Vol;

public class Emp extends HttpServlet{
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        Connection con;
        HttpSession session = req.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
        if(user == null || user.getId_profil() < 10)
            resp.sendRedirect("/avion-mi/login?error=Vous n'etes pas authentifie");
        try {
            con = DAC.getConnection();
            Vol[] vols = Vol.list_all(con);
            Vol[] list_invalide = Vol.list_invalide(con);
            Vol[] list_valide = Vol.list_valider(con);
            Lieu[] lieus = Lieu.list_all(con);
            Avion[] avions = Avion.list_all(con);

            req.setAttribute("vols", vols);
            req.setAttribute("lieus", lieus);
            req.setAttribute("vols_valide", list_valide);
            req.setAttribute("vols_invalide", list_invalide);
            req.setAttribute("avions", avions);

            con.close();
            RequestDispatcher rd = req.getRequestDispatcher("home/emp.jsp");
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
