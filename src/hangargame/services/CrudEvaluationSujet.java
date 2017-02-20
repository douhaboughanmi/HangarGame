/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.EvaluationSujet;
import hangargame.serviceinterface.IEvaluSujetCrud;
import hangargame.services.CrudEvaluation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hamza
 */
public class CrudEvaluationSujet implements IEvaluSujetCrud {

    public Connection conn;
    public Statement stm;
    public PreparedStatement prstm;

    public CrudEvaluationSujet() {

        conn = ConnexionSingleton.getInstance();
        try {
            stm = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CrudEvaluation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void afficherSelonEvaluation() {
        String req4 = "select * from evaluation_sujet ";
        try {
            ResultSet res = stm.executeQuery(req4);
            while (res.next()) {
                String gamer = res.getString("gamer");
                int note = res.getInt("note");
                String sujet = res.getString("sujet");

                System.out.println("" + sujet + note + gamer + "");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterEvaluation(String gamer, String sujet, int note) {
        try {
            String ajoutStatement = "INSERT INTO `evaluation_sujet`(`email_gamer`, `sujet`, `note`) "
                    + "VALUES(?,?,?)";

            prstm = conn.prepareStatement(ajoutStatement);
            prstm.setString(1, gamer);
            prstm.setString(2, sujet);
            prstm.setInt(3, note);

            prstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public int verifierGamer(String gamer) {
        int nombreclient = 0;
        String req = "select count(note) as calcul from evaluation_sujet where email_gamer = ?";
        try {

            prstm = conn.prepareStatement(req);
            prstm.setString(1, gamer);
            ResultSet res = prstm.executeQuery();
            while (res.next()) {

                nombreclient = res.getInt("calcul");

                System.out.println(nombreclient);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CrudEvaluationSujet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nombreclient;
    }

    @Override
    public void UpdateEvaluation(String gamer) {
       
}
}
