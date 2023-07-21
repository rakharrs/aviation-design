package controller.auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.DAC;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.utilisateur.Utilisateur;

public class Login extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect("/avion-mi/login.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("name");
        String mdp = req.getParameter("password");
        Connection con;
        try {
            con = DAC.getConnection();
            Utilisateur user = Utilisateur.getUtilisateur(con, nom, mdp);
            HttpSession session = req.getSession();
            session.setAttribute("utilisateur", user);

            if(user.getId_profil()==10)         // si empr)*;
                resp.sendRedirect("emp");
            else if(user.getId_profil()==20)   // si admin
                resp.sendRedirect("admin");
            else resp.sendRedirect("/avion-mi/client");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("login.jsp?error=authentification failed");
        }
    }
    
}
