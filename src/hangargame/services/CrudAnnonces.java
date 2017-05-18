/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Annonces;
import hangargame.serviceinterface.IAnnonce;
import hangargame.utils.SendMail;
import hangargame.xml.AccueilAnnonceController;
import hangargame.xml.LoginController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author mayss
 */
public class CrudAnnonces implements IAnnonce{

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

    @Override
    public void ajouterAnnonces(Annonces a
    ) {

        String req1 = " insert into annonces (nomAnnonces, typeAnnonces, consoleAnnonces, descriptionAnnonces, prixAnnonces,imageAnnonces,User,pathImage)"
                + " values (?, ?, ?, ?, ?,?,?,?)";

        try {

            prepste = connect.prepareStatement(req1);
            prepste.setString(1, a.getNomAnnonces());
            prepste.setString(2, a.getTypeAnnonces());
            prepste.setString(3, a.getConsoleAnnonces());
            prepste.setString(4, a.getDescriptionAnnonces());
            prepste.setInt(5, a.getPrix());
            prepste.setBlob(6, a.getInputStream());
            prepste.setInt(7, LoginController.LoginStaticID);
            prepste.setString(8, a.getPathImage());
            prepste.executeUpdate();
            System.out.println("c'est fait");

            tray.notification.TrayNotification tr = new TrayNotification();
            tr.setTitle("Terminé");
            tr.setMessage("Consulter votre boite mail");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.setAnimationType(AnimationType.POPUP);
            tr.showAndDismiss(Duration.seconds(4));

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }

        SendMail sendMail = new SendMail();
        sendMail.send();
    }

    @Override
    public void modifierAnnonces(Annonces a) {

        String req1 = "UPDATE annonces SET nomAnnonces = ?, typeAnnonces = ?,prixAnnonces=?"
                + ",descriptionAnnonces=?,imageAnnonces=? ,pathImage=? , consoleAnnonces=?"
                + " WHERE idAnnonces = ?";

        try {

            prepste = connect.prepareStatement(req1);
            prepste.setString(1, a.getNomAnnonces());
            prepste.setString(2, a.getTypeAnnonces());
            prepste.setInt(3, a.getPrix());
            prepste.setString(4, a.getDescriptionAnnonces());

            prepste.setBlob(5, a.getInputStream());
            prepste.setString(6, a.getPathImage());
            prepste.setString(7, a.getConsoleAnnonces());
            prepste.setString(7, a.getConsoleAnnonces());
            prepste.setInt(8, a.getIdAnnonces());
            prepste.executeUpdate();
            System.out.println("hig");

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }

        //SendMail sendMail = new SendMail();
        //  sendMail.send();
    }

    @Override
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

    @Override
    public List<Annonces> reccupererSimple() {

        String query = "Select * from annonces";

        try {
            prepste = connect.prepareStatement(query);
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                int id = rs.getInt("idAnnonces");
                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(id, nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
    public List<Annonces> reccupererSimple2() {

        String query = "Select * from annonces where User=?";

        try {
            prepste = connect.prepareStatement(query);
            prepste.setInt(1, LoginController.LoginStaticID);
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                int idAnnonces = rs.getInt("idAnnonces");
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                String console = rs.getString("consoleAnnonces");
                String desc = rs.getString("descriptionAnnonces");
                String path = rs.getString("pathImage");
                int gamer = rs.getInt("User");
                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(idAnnonces, nomA, typeAnnonces, console, desc, prixAnnonces, inputStream, gamer, path);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<Annonces> reccupererSimple3() {

        String query = "Select * from annonces ";

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
                String path = rs.getString("pathImage");
                int gamer = rs.getInt("User");
                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(idAnnonces, nomA, typeAnnonces, console, desc, prixAnnonces, inputStream, gamer, path);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    
    
    @Override
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
                int id = rs.getInt("idAnnonces");
                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(id, nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
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
                int id = rs.getInt("idAnnonces");
                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(id, nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
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
                int id = rs.getInt("idAnnonces");
                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(id, nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
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
                int id = rs.getInt("idAnnonces");
                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(id, nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
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
                int id = rs.getInt("idAnnonces");
                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(id, nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
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
                int id = rs.getInt("idAnnonces");
                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(id, nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
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
                int id = rs.getInt("idAnnonces");
                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(id, nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
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
                int id = rs.getInt("idAnnonces");
                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(id, nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
    public Annonces reccupererAnnonce() {

        String query = "Select * from annonces where idAnnonces=?";

        try {

            prepste = connect.prepareStatement(query);
            prepste.setInt(1, Integer.parseInt(AccueilAnnonceController.indexAnnonce));
            ResultSet rs = prepste.executeQuery();

            while (rs.next()) {
                int idAnnonces = rs.getInt("idAnnonces");
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                String console = rs.getString("consoleAnnonces");
                String desc = rs.getString("descriptionAnnonces");
                String gamer = rs.getString("emailGamer");

                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                annoncees = new Annonces(idAnnonces, nomA, typeAnnonces, console, desc, prixAnnonces, inputStream, gamer);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("wwwwwwwwww" + annoncees);
        return annoncees;

    }

    @Override
    public Annonces reccupererAnnonce2() {

        String query = "Select * from annonces where idAnnonces=?";

        try {

            prepste = connect.prepareStatement(query);
            prepste.setInt(1, AccueilAnnonceController.indexAnnonce2);
            ResultSet rs = prepste.executeQuery();

            while (rs.next()) {
                int idAnnonces = rs.getInt("idAnnonces");
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                String console = rs.getString("consoleAnnonces");
                String desc = rs.getString("descriptionAnnonces");
                int gamer = rs.getInt("User");
                String pathImage = rs.getString("pathImage");

                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                annoncees = new Annonces(idAnnonces, nomA, typeAnnonces, console, desc, prixAnnonces, inputStream, gamer, pathImage);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("wwwwwwwwww" + annoncees);
        return annoncees;

    }
    int a;
    
    public int nbrAnnonceVente(){
    
        String query = "Select count(*) as count from annonces where typeAnnonces=? ";

        try {

            prepste = connect.prepareStatement(query);
           prepste.setString(1,"Vente");
            ResultSet rs = prepste.executeQuery();

            while (rs.next()) {
               a = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
        

    }
    
        int b;
    public int nbrAnnonceEchange(){
    
        String query = "Select count(*) as count from annonces where typeAnnonces=? ";

        try {

            prepste = connect.prepareStatement(query);
           prepste.setString(1,"Echange");
            ResultSet rs = prepste.executeQuery();

            while (rs.next()) {
               b = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
        

    }
  

}
