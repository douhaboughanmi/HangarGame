/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
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
            String ajoutStatement = "insert into forum_sujet( titre,categorie, contenue,username) "
                    + "VALUES(?,?,?,'hamza')";

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
            String supprimerstatment = "delete from forum_sujet where titre = ?";

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
            String req4 = "SELECT `titre`,`date`,id "
                + "from forum_sujet where titre=?";

            prpste = connect.prepareStatement(req4);
            prpste.setString(1, s);
            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                String titre = rs.getString("titre");
                Timestamp date = rs.getTimestamp("date");
                int note = rs.getInt("id");
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
        String req = "SELECT `titre`,`date` "
                + "from forum_sujet ";

        try {

            prpste = connect.prepareStatement(req);

            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                String titre = rs.getString("titre");
                Timestamp date = rs.getTimestamp("date");

                Sujet cm = new Sujet(titre, date);
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
        String req = "select * from forum_sujet WHERE titre = ?";
        Sujet sujet = null;
        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                String nomsujet = rs.getString("titre");
                Timestamp datesujet = rs.getTimestamp("date");
                String textsujet = rs.getString("contenue");
                String categorie = rs.getString("categorie");
                String gamer = rs.getString("username");
                int id = rs.getInt("id");

                sujet = new Sujet(nomsujet, textsujet, categorie, datesujet, gamer, id);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sujet;

    }

    @Override
    public ArrayList<Sujet> afficherHistoriquePersonnel(String s) {
        ArrayList<Sujet> ls = new ArrayList<>();
        String req = "select `titre`,`date` from forum_sujet where `username`=?";

        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                String nomsujet = rs.getString("titre");
                Timestamp datepub = rs.getTimestamp("date");
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
    public ArrayList<Sujet> afficherFavorisPersonnel(String s) {
        ArrayList<Sujet> ls = new ArrayList<>();
        String req = "SELECT `titreSujet`,`contenue` FROM `favorissujets` where `propr`=?";

        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                String titreSujet = rs.getString("titreSujet");
                String textSujet = rs.getString("contenue");
                Sujet st = new Sujet(titreSujet, textSujet);
                ls.add(st);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;

    }

    @Override
    public int tolalJaime(String s) {
        String req = "SELECT SUM(`nbrlike`) as somme from forum_evaluation where iduser=(select id from gamer where username=?)";
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
    public int totalSujet() {
        String req = "select count(`titre`) sommesujet from forum_sujet";
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
        int idsujet = 0;
        String gamer = "";

        String req = "SELECT id,user,titre from forum_sujet where titre= ?";
        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                idsujet = rs.getInt("id");
                gamer = rs.getString("user");
                System.out.println(idsujet + gamer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }

        String req2 = "INSERT INTO `forum_signalisation`( `sujet`, `nb_signalisation`, `user`)"
                + " VALUES ("+idsujet+",'1',"+gamer+")";

        try {
            prpste = connect.prepareStatement(req2);
            int res = prpste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void updateSignale(String s) {
        String req = "UPDATE `forum_signalisation` SET `nb_signalisation`= `nb_signalisation`+1 "
                + "WHERE `sujet` = (SELECT id from forum_sujet WHERE titre= ?)";
        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            int rs = prpste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int verifSujetSignl(String s) {
        int nbrSignalisation = 0;

        String req = "select nb_signalisation FROM forum_signalisation WHERE `sujet` = ( SELECT id from forum_sujet WHERE titre= ?)";

        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            ResultSet rs = prpste.executeQuery();
            while (rs.next()) {
                nbrSignalisation = rs.getInt("nb_signalisation");

                System.out.println(nbrSignalisation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nbrSignalisation;
    }

    @Override
    public int verifGamerSujet(String s, String g) {
        int nbrSignalGamer = 0;
        String req = "select count(`id`) as sommgamer "
                + "from forum_signalisation where sujet = (select titre from forum_sujet where titre= ?) AND user = ?";
        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, s);
            prpste.setString(2, g);
            ResultSet rs = prpste.executeQuery();
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
    public void ajouterFavoris(String titre, String cont, String prop) {
        String req = "INSERT INTO `favorisSujets`(`titreSujet`, `contenue`, `propr`) "
                + "VALUES (?,?,?)";
        try {

            prpste = connect.prepareStatement(req);
            prpste.setString(1, titre);
            prpste.setString(2, cont);
            prpste.setString(3, prop);
            int rs = prpste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int tolalSignale(String s) {
        int sommsign = 0;
        String req = "select SUM(`nb_signalisation`) as sommsign FROM  forum_signalisation where `sujet` In ( select id from forum_sujet where username= ?)";
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

    @Override
    public int totalSignal(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
