/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Participants;
import hangargame.serviceinterface.IParticipantCrud;
import hangargame.xml.LoginController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import hangargame.xml.LoginController;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Louay
 */
public class CrudParticipant implements IParticipantCrud {

    private ObservableList<String> data;
    Connection connect;
    Statement ste;
    PreparedStatement prepste;
    Participants P = new Participants();
    int rec;

    public CrudParticipant() {
        try {
            connect = ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CrudParticipant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String ajouterParticipants(Participants e) {
        String req = "select login from participant where login =? and id_tournoi=?";

        try {
            prepste = connect.prepareStatement(req);

            prepste.setInt(1,2);
            prepste.setInt(2,e.getId_tounoi());
            ResultSet rs = prepste.executeQuery();

            while (rs.next()) {
                rec = rs.getInt("login");
               
                tray.notification.TrayNotification tr = new TrayNotification();
                tr.setTitle("Terminé");
                tr.setMessage("Erreur de participation !!");
                tr.setNotificationType(NotificationType.ERROR);
                tr.setAnimationType(AnimationType.POPUP);
                tr.showAndDismiss(Duration.seconds(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CrudParticipant.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rec==2) {
            return "vous avez déja etez inscrit";

        } else {

            String req1 = "insert into participant (id_tournoi,login,nomTournoi,statut)values(?,?,?,?)";
            try {

                prepste = connect.prepareStatement(req1);
                prepste.setInt(1, e.getId_tounoi());
                prepste.setString(2,LoginController.LoginStatic);
                prepste.setString(3,"");
                prepste.setString(4,"Request");
                prepste.executeUpdate();
                tray.notification.TrayNotification tr = new TrayNotification();
                tr.setTitle("Terminé");
                tr.setMessage("Votre Particapation est effectué avec succés");
                tr.setNotificationType(NotificationType.SUCCESS);
                tr.setAnimationType(AnimationType.SLIDE);
                tr.showAndDismiss(Duration.seconds(4));
                
            } catch (SQLException ex) {
                Logger.getLogger(TournoiCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "";
        }
    }

    @Override
    public ObservableList<String> afficherParticipants(int id) {
        try {
            connect = ConnexionSingleton.getInstance();
            data = FXCollections.observableArrayList();
            ResultSet rs = connect.createStatement().executeQuery("select login from participant where id_tournoi=" + id);
            while (rs.next()) {

                data.add(rs.getString(1));
                //System.out.println(data);
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
        return data;
    }
    int a = 0;

    @Override
    public int recupererParticipant(int id) {
        try {
            connect = ConnexionSingleton.getInstance();

            ResultSet rs = connect.createStatement().executeQuery("SELECT COUNT(login) FROM participant "
                    + "WHERE id_tournoi=" +id);
            while (rs.next()) {

                a = Integer.parseInt(rs.getString(1));

            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
        return a;
    }

}
