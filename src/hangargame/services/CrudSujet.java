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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Hamza
 */
public class CrudSujet implements ISujetCrud {

    Connection connect;
    Statement stat;
    PreparedStatement prpste;
 public ObservableList<Sujet> data;
    public CrudSujet() {
        try {
            connect = ConnexionSingleton.getInstance();

            stat = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    

   

    @Override
    public void ajoutersujet(Sujet s) {
          try {
            String ajoutStatement = "insert into sujet_forum( nomsujet,categorie, textsujet,etatsujet,gamer) "
                    + "VALUES(?,?,?,'2','zut@zut.tn')";

            prpste = connect.prepareStatement(ajoutStatement);
            prpste.setString(1, s.getNomSujet());
            prpste.setString(2, s.getCategorie());
            prpste.setString(3, s.gettextSujet());
           // prpste.setString(4, s.getGamer());
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
    public ObservableList<Sujet>  rechercherSujet(String s) {
        
        try {
            String req4= "SELECT `nomsujet`,`datepub`,`textsujet`,`categorie`,`gamer`,`etatsujet` from  sujet_forum where nomsujet=?";
            connect = ConnexionSingleton.getInstance();
            data = FXCollections.observableArrayList();
            prpste = connect.prepareStatement(req4);
            prpste.setString(1,s);
          ResultSet rs= prpste.executeQuery();
            while (rs.next()) {
                data.add(new Sujet(rs.getString(1), rs.getTimestamp(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
return data ;
}

    @Override
    public ObservableList<Sujet> AffichageSuhetSujetCategorie() {
       try {
            
            connect = ConnexionSingleton.getInstance();
            data = FXCollections.observableArrayList();
            ResultSet rs = connect.createStatement().executeQuery("SELECT `nomsujet`,`datepub`,`textsujet`,`categorie`,`gamer`,`etatsujet` from  sujet_forum");
            while (rs.next()) {
                data.add(new Sujet(rs.getString(1), rs.getTimestamp(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
return data ;
    }
    
     @Override
    public Sujet consulterSujet(String s) {
        String req = "SELECT `nomsujet`,`datepub`,`textsujet`,`categorie`,`gamer`,`etatsujet` from  sujet_forum where nomsujet = ?";
        Sujet sujet = null;
        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                String nomsujet = rs.getString("nomsujet");
                Timestamp datesujet = rs.getTimestamp("datepub");
                String textsujet = rs.getString("textsujet");
                String categorie = rs.getString("categorie");
                String gamer = rs.getString("gamer");
                int etat = rs.getInt("etatsujet");
                sujet = new Sujet(nomsujet, datesujet, textsujet, categorie, gamer, etat);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sujet;

    }
    
    
    @Override
    public ArrayList<Sujet> afficherHistoriquePersonnel(String s) {
        ArrayList<Sujet> ls = new ArrayList<>();
        String req = "select `nomsujet`,`datepub` from sujet_forum where `gamer`=?";

        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                String nomsujet = rs.getString("nomsujet");
                Timestamp datepub = rs.getTimestamp("datepub");
                Sujet st = new Sujet(nomsujet, datepub);
                ls.add(st);
                System.out.println(nomsujet);
                System.out.println(nomsujet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;

    }

    @Override
    public int tolalJaime(String s) {
        String req = "SELECT SUM(`note`) as somme from evaluation_sujet where gamer=?";
        int sommenotes = 0;
        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                sommenotes = rs.getInt("somme");

                System.out.println(sommenotes);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sommenotes;

    }

    @Override
    public int totalSignal(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


