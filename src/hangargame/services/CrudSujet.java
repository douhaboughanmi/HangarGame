/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.services;

/**
 *
 * @author Hamza
 */

import hangargame.connexionDB.ConnexionSingleton;

import hangargame.entites.Sujet;
import hangargame.serviceinterface.ISujetCrud;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Hamza
 */
public class CrudSujet implements ISujetCrud {

    Connection connect;
    Statement stat;
    PreparedStatement prpste;

    public CrudSujet() {
        try {
            connect = ConnexionSingleton.getInstance();

            stat = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    

    @Override
    public List<Sujet> rechercherSujetCategorie(String c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajoutersujet(Sujet s) {
          try {
            String ajoutStatement = "INSERT INTO INTO  `sujet_forum`( `nomsujet`, `categorie`, `textsujet`,`etatsujet`, `gamer`) VALUES "
                    + "VALUES(?,?,?,'2',?)";

            prpste = connect.prepareStatement(ajoutStatement);
            prpste.setString(1, s.getNomSujet());
            prpste.setString(2, s.getCategorie());
            prpste.setString(3, s.gettextSujet());
             prpste.setInt(4, s.getetat());
            prpste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerSujet(Sujet s) {
        try {
            String supprimerstatment = "delete from sujet_forum where etatsujet=?";

            prpste = connect.prepareStatement(supprimerstatment);
            prpste.setInt(1, s.getetat());
            prpste.executeUpdate();

            
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rechercherSujet(Sujet s) {
         String req4= "select * from sujet_forum ORDER BY nomsujet";
        try {
            ResultSet res  =stat.executeQuery(req4);
            while (res.next()) { 
               String nomSujet= res.getString("nomsujet");
               Date date = res.getDate("datepub");
               String text = res.getString("textsujet");
               String categrie = res.getString("categorie");
            System.out.println(""+nomSujet+date+categrie+"");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }}

    }


