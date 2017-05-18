/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Annonces;
import hangargame.serviceinterface.IFavoris;
import hangargame.xml.AccueilAnnonceController;
import hangargame.xml.LoginController;
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
public class CrudFavoris implements IFavoris{

    int idAnnonce = 0;
    int signaleurr = 0;

    Connection connect;
    Statement ste;
    PreparedStatement prepste;
    List<Annonces> list = new ArrayList<>();

    public CrudFavoris()  {
        connect = ConnexionSingleton.getInstance();
        try {
            connect = ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String ajouterAnnoncesFavoris() {

        idAnnonce = 0;
        signaleurr =0;
        String req5 = "Select * from favoris_annonce"
                + " where idAnnonce=? AND idUser= ? ";
        try {
            prepste = connect.prepareStatement(req5);
            prepste.setInt(1, AccueilAnnonceController.indexAnnonce2);

            prepste.setInt(2, LoginController.LoginStaticID);
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                idAnnonce = rs.getInt("idAnnonce");
                signaleurr = rs.getInt("idUser");

               
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (idAnnonce == AccueilAnnonceController.indexAnnonce2
                && signaleurr ==LoginController.LoginStaticID) {

            return "vous avez déja aimez cette annonce";

        } else {

            String req1 = " insert into favoris_annonce (idAnnonce,idUser)"
                    + " values (?, ?)";

            try {

                prepste = connect.prepareStatement(req1);
                prepste.setInt(2, LoginController.LoginStaticID);
                prepste.setInt(1, AccueilAnnonceController.indexAnnonce2);
                prepste.executeUpdate();
                System.out.println("c'est fait");

            } catch (SQLException ex) {
                Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
            }

        //SendMail sendMail = new SendMail();
            //  sendMail.send();
            return null;
        }
    }

    @Override
    public List<Annonces> afficherFavoris() {
        String req5 = "SELECT * from annonces,favoris_annonce   WHERE annonces.idAnnonces = favoris_annonce.idannonce AND "
                + "favoris_annonce.idUser=?";

        try {
            prepste = connect.prepareStatement(req5);
            prepste.setInt(1,LoginController.LoginStaticID);
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
            return list;

        } catch (SQLException ex) {
            Logger.getLogger(CrudFavoris.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
