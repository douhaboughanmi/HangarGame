/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.entites.Annonces;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import hangargame.connexionDB.ConnexionSingleton;
import hangargame.utils.SendMail;
import hangargame.xml.LoginController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mayss
 */
public class CrudAnnonces {

    Connection connect;
    Statement ste;
    PreparedStatement prepste;
    List<Annonces> list = new ArrayList<>();
    Annonces annoncees = new Annonces(0, "", "", "", "", 0, null);
    int nbrSignalisation = 0;
    int idSignalisation = 0;

    public CrudAnnonces() {
        connect = ConnexionSingleton.getInstance();
        try {
            connect = ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ajouterAnnonces(Annonces a
    ) {

        String req1 = " insert into annonces (nomAnnonces, typeAnnonces, consoleAnnonces, descriptionAnnonces, prixAnnonces,imageAnnonces,emailGamer)"
                + " values (?, ?, ?, ?, ?,?,?)";

        try {

            prepste = connect.prepareStatement(req1);
            prepste.setString(1, a.getNomAnnonces());
            prepste.setString(2, a.getTypeAnnonces());
            prepste.setString(3, a.getConsoleAnnonces());
            prepste.setString(4, a.getDescriptionAnnonces());
            prepste.setInt(5, a.getPrix());
            prepste.setBlob(6, a.getInputStream());
            prepste.setString(7, "LoginController.LoginStatic");
            prepste.executeUpdate();
            System.out.println("c'est fait");

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }

        //SendMail sendMail = new SendMail();
       // sendMail.send();
    }

    public void modifierAnnonces(Annonces a) {

        String req1 = "UPDATE annonces SET nomAnnonces = ?, typeAnnonces = ?,prixAnnonces=?"
                + ",descriptionAnnonces=?,imageAnnonces=?"
                + " WHERE idAnnonces = ?";

        try {

            prepste = connect.prepareStatement(req1);
            prepste.setString(1, a.getNomAnnonces());
            prepste.setString(2, a.getTypeAnnonces());
            prepste.setInt(3, a.getPrix());
            prepste.setString(4, a.getDescriptionAnnonces());

            prepste.setBlob(5, a.getInputStream());
            prepste.setInt(6, a.getIdAnnonces());
            prepste.executeUpdate();
            System.out.println("c'est fait");

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }

        //SendMail sendMail = new SendMail();
        //  sendMail.send();
    }

    public void supprimerAnnonces(Annonces a) {

        String req1 = "Delete from annonces where idAnnonces = ?";

        try {

            prepste = connect.prepareStatement(req1);
            prepste.setInt(1, a.getIdAnnonces());

            prepste.executeUpdate();
            System.out.println("c'est fait");

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }

        //SendMail sendMail = new SendMail();
        //  sendMail.send();
    }

    public List<Annonces> reccupererSimple() {

        String query = "Select * from annonces";

        try {
            prepste = connect.prepareStatement(query);
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<Annonces> reccupererSimple2() {

        String query = "Select * from annonces";

        try {
            prepste = connect.prepareStatement(query);
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                int idAnnonces = rs.getInt("idAnnonces");
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                String console = rs.getString("consoleAnnonces");
                String desc = rs.getString("descriptionAnnonces");

                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(idAnnonces, nomA, typeAnnonces, console, desc, prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<Annonces> reccupererSelonEchange() {

        String query = "Select * from annonces where typeAnnonces=?";

        try {

            prepste = connect.prepareStatement(query);
            prepste.setString(1, "Echange");
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<Annonces> reccupererSelonVente() {

        String query = "Select * from annonces where typeAnnonces=?";

        try {

            prepste = connect.prepareStatement(query);
            prepste.setString(1, "Vente");
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<Annonces> reccupererSelonPC() {

        String query = "Select * from annonces where consoleAnnonces=?";

        try {

            prepste = connect.prepareStatement(query);
            prepste.setString(1, "PC");
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<Annonces> reccupererSelonPS4() {

        String query = "Select * from annonces where consoleAnnonces=?";

        try {

            prepste = connect.prepareStatement(query);
            prepste.setString(1, "PS4");
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<Annonces> reccupererSelonPS3() {

        String query = "Select * from annonces where consoleAnnonces=?";

        try {

            prepste = connect.prepareStatement(query);
            prepste.setString(1, "PS3");
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<Annonces> reccupererSelonXbox360() {

        String query = "Select * from annonces where consoleAnnonces=?";

        try {

            prepste = connect.prepareStatement(query);
            prepste.setString(1, "Xbox 360");
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<Annonces> reccupererSelonXboxOne() {

        String query = "Select * from annonces where consoleAnnonces=?";

        try {

            prepste = connect.prepareStatement(query);
            prepste.setString(1, "Xbox One");
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<Annonces> reccupererSelonPSVita() {

        String query = "Select * from annonces where consoleAnnonces=?";

        try {

            prepste = connect.prepareStatement(query);
            prepste.setString(1, "PS Vita");
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public Annonces reccupererAnnonce() {

        String query = "Select * from annonces where idAnnonces=?";

        try {

            prepste = connect.prepareStatement(query);
            prepste.setInt(1, 17);
            ResultSet rs = prepste.executeQuery();

            while (rs.next()) {
                int idAnnonces = rs.getInt("idAnnonces");
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                String console = rs.getString("consoleAnnonces");
                String desc = rs.getString("descriptionAnnonces");
                String amer = rs.getString("emailGamer");

                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                annoncees = new Annonces(idAnnonces, nomA, typeAnnonces, console, desc, prixAnnonces, inputStream, amer);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("wwwwwwwwww" + annoncees);
        return annoncees;

    }

    public void ajouterAnnoncesFavoris() {

        String req1 = " insert into favoris (login,idAnnonce)"
                + " values (?, ?)";

        try {

            prepste = connect.prepareStatement(req1);
            prepste.setString(1, "LoginController.LoginStatic");
            prepste.setInt(2, 5);
            prepste.executeUpdate();
            System.out.println("c'est fait");

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }

        //SendMail sendMail = new SendMail();
        //  sendMail.send();
    }

    public void signalerAnnonce() {

        String req0 = "Select idSignalisation,nbrSignalisation from Signalisation where idObjet=? AND typeSignalisation= ? ";
        try {
            prepste = connect.prepareStatement(req0);
            prepste.setInt(1, 1);
            prepste.setString(2, "Echange");
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

            String req1 = "Delete  from Signalisation where idSignalisation = ?";

            try {

                prepste = connect.prepareStatement(req1);
                prepste.setInt(1, idSignalisation);

                prepste.executeUpdate();
                System.out.println("c'est fait");

            } catch (SQLException ex) {
                Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (nbrSignalisation == 0) {
            String req1 = "INSERT INTO Signalisation(idObjet,typeSignalisation,gamerSignale,nbrSignalisation) "
                    + "VALUES (?,?,?,?)";

            try {
                prepste = connect.prepareStatement(req1);
                prepste.setInt(1, 1);
                prepste.setString(2, "Echange");
                prepste.setString(3, "mayssa");
                prepste.setInt(4, nbrSignalisation + 1);
                prepste.executeUpdate();
                System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
            } catch (SQLException ex) {
                Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            String req1
                    = //" INSERT INTO Signalisation(idObjet,typeSignalisation,gamerSignale,nbrSignalisation) "
                    //+ "VALUES (?,?,?,?)";
                    "UPDATE Signalisation SET idObjet = ?, typeSignalisation = ?,gamerSignale=?"
                    + ",nbrSignalisation=?"
                    + " WHERE idObjet = ?";

            try {

                prepste = connect.prepareStatement(req1);
                prepste.setInt(1, 1);
                prepste.setString(2, "Echange");
                prepste.setString(3, "mayssa");
                prepste.setInt(4, nbrSignalisation + 1);
                prepste.setInt(5, 1);
                prepste.executeUpdate();
                System.out.println("c'est fait");

            } catch (SQLException ex) {
                Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //SendMail sendMail = new SendMail();
        //  sendMail.send();
    }

}