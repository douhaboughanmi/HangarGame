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
                    + "VALUES(?,?,?,'2','hamza')";

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
    public void supprimerSujet(String s) {
        try {
            String supprimerstatment = "delete from sujet_forum where nomsujet = ?";

            prpste = connect.prepareStatement(supprimerstatment);
            prpste.setString(1, s);
            prpste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //rechercher les sujets dans une listview 
    @Override
    public ArrayList<Sujet> rechercherSujet(String s) {
        ArrayList<Sujet> ls = new ArrayList<>();
        try {
            String req4 = "SELECT `nomsujet`,`datepub`, `note` from sujet_forum , evaluation_sujet"
                    + " WHERE sujet_forum.nomsujet = evaluation_sujet.sujet "
                    + "AND sujet_forum.nomsujet=?";

            prpste = connect.prepareStatement(req4);
            prpste.setString(1, s);
            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                String titre = rs.getString("nomsujet");
                Timestamp date = rs.getTimestamp("datepub");
                int note = rs.getInt("note");
                Sujet cm = new Sujet(titre, date, note);
                ls.add(cm);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
        return ls;
    }
// Afficher listview des sujets 

    @Override
    public ArrayList<Sujet> AffichageSuhetSujetCategorie() {
        ArrayList<Sujet> ls = new ArrayList<>();
        String req = "SELECT `sujet`,`datepub`, `note` "
                + "from sujet_forum e, evaluation_sujet ev WHERE e.nomsujet = ev.sujet";

        try {

            prpste = connect.prepareStatement(req);

            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                String titre = rs.getString("sujet");
                Timestamp date = rs.getTimestamp("datepub");
                int note = rs.getInt("note");
                Sujet cm = new Sujet(titre, date, note);
                ls.add(cm);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    //Afficher un sujet
    @Override
    public Sujet consulterSujet(String s) {
        String req = "SELECT `nomsujet`,`textsujet`,`datepub`,`gamer`,`categorie`, `note`, count(commentaire_forum.idcomm) \n"
                + "       As sommecomm from sujet_forum  ,evaluation_sujet ,commentaire_forum  \n"
                + "                 where commentaire_forum.sujet = sujet_forum.nomsujet              AND  sujet_forum.nomsujet=evaluation_sujet.sujet AND sujet_forum.nomsujet=?";
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
                int note = rs.getInt("note");
                int sommecom = rs.getInt("sommecomm");
                sujet = new Sujet(nomsujet, textsujet, categorie, datesujet, sommecom, gamer, note);
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
        String req = "SELECT SUM(`note`) as somme from evaluation_sujet where email_gamer=?";
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

    @Override
    public int totalSujet() {
        String req = "select count(`sujet`) sommesujet from evaluation_sujet";
        int sommesujet = 0;
        try {

            prpste = connect.prepareStatement(req);

            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                sommesujet = rs.getInt("sommesujet");

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sommesujet;

    }

    @Override
    public void signalerSujet(String s) {
        int idsujet=0;
                String gamer="";
        
        String req = "SELECT idsujet,gamer as gr,nomsujet from sujet_forum where nomsujet= ?";
        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                idsujet = rs.getInt("idsujet");
                gamer = rs.getString("gr");
                System.out.println(idsujet + gamer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         String req2 = "INSERT INTO `signalisation`(`idObjet`, `typeSignalisation`, `gamerSignale`, `nbrSignalisation`)"
                        + " VALUES (" + idsujet + ",'Sujet','" + gamer +"',1"
                        + ")";

        try {
            prpste = connect.prepareStatement(req2);
             int res = prpste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }

               

    }

    @Override
    public void updateSignale(String s) {
       String req ="UPDATE `signalisation` SET `nbrSignalisation`= `nbrSignalisation`+1 "
               + "WHERE `idObjet` = (SELECT idsujet from sujet_forum WHERE nomsujet= ?)";
        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            int  rs = prpste.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int verifSujetSignl(String s) {
       int nbrSignalisation = 0 ; 
       
       String req="select nbrSignalisation FROM signalisation WHERE `idObjet` = ( SELECT idsujet from sujet_forum WHERE nomsujet= ?)";
       
         try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                nbrSignalisation = rs.getInt("nbrSignalisation");
                
                System.out.println(nbrSignalisation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return nbrSignalisation;
    }

    @Override
    public int verifGamerSujet(String s, String g) {
        int nbrSignalGamer =0;
        String req ="select count(`idSignalisation`) as sommgamer "
                + "from signalisation where idObjet = (select idsujet from sujet_forum where nomsujet= ?) AND gamerSignale = ?";
             try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            prpste.setString(2, g);
            ResultSet  rs = prpste.executeQuery();
              while (rs.next()) {
                nbrSignalGamer = rs.getInt("sommgamer");
                
                System.out.println(nbrSignalGamer);
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
             return nbrSignalGamer;
    }

    @Override
    public void ajouterFavoris(String s) {
        String req  = "update sujet_forum set etatsujet =1 where nomsujet = ? ";
        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            int  rs = prpste.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int tolalSignale(String s) {
       int sommsign=0;
        String req="select SUM(`nbrSignalisation`) as sommsign FROM signalisation where `idObjet` In ( select idsujet from sujet_forum where gamer= ?)";
        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                sommsign = rs.getInt("sommsign");
                
                System.out.println(sommsign);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
       return sommsign;
    }
}
