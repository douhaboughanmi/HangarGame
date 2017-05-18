/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.xml.AccueilAnnonceController;
import hangargame.xml.LoginController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mayss
 */
public class CrudSignalisation {

    Connection connect;
    Statement ste;
    PreparedStatement prepste;
    int nbrSignalisation;
    int idSignalisation;
    String signaleur;
    int idAnnonce;
    String signaleurr;
    int signaleurr2;
    int nbr = 0;

    public CrudSignalisation() {

        connect = ConnexionSingleton.getInstance();
        try {
            connect = ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int signalisationGamer(String login) {
        nbr = 0;

        String query = "Select nbrSignalisation from signalisation where gamerSignale=? ";

        try {
            prepste = connect.prepareStatement(query);
            prepste.setString(1, login);
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {

                nbr += rs.getInt("nbrSignalisation");

                return nbr;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nbr;
    }

    public void ajoutSignaleur() {
        String req = "INSERT INTO signaleurannonce(user,idannonce) "
                + "VALUES (?,?)";

        try {
            prepste = connect.prepareStatement(req);
            prepste.setInt(2, AccueilAnnonceController.indexAnnonce2);
            prepste.setInt(1, LoginController.LoginStaticID);

            prepste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String signalerAnnonce(String gamer) {
        idAnnonce = 0;
        signaleurr = "";
        String req5 = "Select * from signaleurannonce"
                + " where idannonce=? AND user= ? ";
        try {
            System.out.println("select");
            prepste = connect.prepareStatement(req5);
            prepste.setInt(1, AccueilAnnonceController.indexAnnonce2);

            prepste.setInt(2, LoginController.LoginStaticID);
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                idAnnonce = rs.getInt("idannonce");
                signaleurr2 = rs.getInt("user");

                //System.out.println("test" + nbrSignalisation + idSignalisation);
            }
             System.out.println("select"+idAnnonce+"signaleur"+signaleurr);
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (idAnnonce == AccueilAnnonceController.indexAnnonce2
                && signaleurr2==LoginController.LoginStaticID) {

            return "vous avez déja signaler cette annonce";

        } else {

            String req0 = "Select idSignalisation,nbrSignalisation from Signalisatio_annonces where idAnnonce=?";
            try {
                System.out.println("select");
                prepste = connect.prepareStatement(req0);
                prepste.setInt(1, AccueilAnnonceController.indexAnnonce2);

             
                ResultSet rs = prepste.executeQuery();
                while (rs.next()) {
                    nbrSignalisation = rs.getInt("nbrSignalisation");
                    idSignalisation = rs.getInt("idSignalisation");

                    System.out.println("test" + nbrSignalisation + idSignalisation);

                }
            } catch (SQLException ex) {
                Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (nbrSignalisation == 4) {

                String req1 = "Delete  from annonces where idAnnonces = ?";

                try {

                    prepste = connect.prepareStatement(req1);
                    prepste.setInt(1, AccueilAnnonceController.indexAnnonce2);

                    prepste.executeUpdate();
                    System.out.println("c'est fait");

                } catch (SQLException ex) {
                    Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (nbrSignalisation == 0) {
                ajoutSignaleur();
                System.out.println("insert");
                String req1 = "INSERT INTO Signalisatio_annonces(idAnnonce,UserSignale,nbrSignalisation) "
                        + "VALUES (?,?,?)";

                try {
                    prepste = connect.prepareStatement(req1);
                    prepste.setInt(1, AccueilAnnonceController.indexAnnonce2);
                    prepste.setString(2, gamer);
                    prepste.setInt(3, nbrSignalisation + 1);

                    prepste.executeUpdate();

                } catch (SQLException ex) {
                    Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                ajoutSignaleur();
                System.out.println("update");
                String req1
                        = //" INSERT INTO Signalisation(idObjet,typeSignalisation,gamerSignale,nbrSignalisation) "
                        //+ "VALUES (?,?,?,?)";
                        "UPDATE Signalisatio_annonces SET idAnnonce = ?, userSignale=?"
                        + ",nbrSignalisation=?"
                        + " WHERE idAnnonce = ?";

                try {

                    prepste = connect.prepareStatement(req1);
                    prepste.setInt(1, AccueilAnnonceController.indexAnnonce2);
                    prepste.setString(2, gamer);
                    prepste.setInt(3, nbrSignalisation + 1);
                    prepste.setInt(4, AccueilAnnonceController.indexAnnonce2);
                    prepste.executeUpdate();
                    System.out.println("c'est fait");

                } catch (SQLException ex) {
                    Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        //SendMail sendMail = new SendMail();
            //  sendMail.send();
            return "";
        }
    }

}
