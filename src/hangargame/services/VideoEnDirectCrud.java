package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.VideoEnDirect;
import hangargame.entites.VideoTest;
import hangargame.serviceinterface.ICrudVideoEnDirect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class VideoEnDirectCrud implements ICrudVideoEnDirect {

    Connection connect;
    Statement ste;
    PreparedStatement prepste;
      private ObservableList<VideoEnDirect> data;

    public VideoEnDirectCrud() {

        try {
            connect = ConnexionSingleton.getInstance();
            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(VideoTestCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouter(VideoEnDirect v) {

        String req1 = " insert into video_direct ( nom,url,description)values( ?,?,?)";
        try {

            prepste = connect.prepareStatement(req1);
            prepste.setString(1, v.getNom_videoEnDirect());
            prepste.setString(2, v.getUrl_videoEnDirect());
            prepste.setString(3, v.getDescription_videoEnDirect());

            prepste.executeUpdate();
            System.out.println("ajout avec fait");

        } catch (SQLException ex) {
            Logger.getLogger(VideoTestCrud.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println("err");
               System.out.println(v.getNom_videoEnDirect());

        }
    }

    @Override
    public void supprimer(int id) {

        try {
              VideoEnDirect v =new VideoEnDirect();
            String req2 = "delete from video_direct where id=?";

           prepste = connect.prepareStatement(req2);
             prepste.setInt(1,id);
             prepste.execute();
        } catch (SQLException ex) {
            Logger.getLogger(VideoTestCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(VideoEnDirect v) {

        try {
            String req3 = "UPDATE video_direct SET nom=?"
                    + "url=?"
                    + "description=?";

            prepste = connect.prepareStatement(req3);
            prepste.setString(1, v.getNom_videoEnDirect());
            prepste.setString(2, v.getUrl_videoEnDirect());
            prepste.setString(3, v.getDescription_videoEnDirect());

            prepste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VideoEnDirectCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void afficher() {

        try {
            String req4 = "select * from video_direct";

            ResultSet res = ste.executeQuery(req4);
            while (res.next()) {
                System.out.println(
                        "nom: " + res.getString("nom") + " "
                        + "url: " + res.getString("url") + " "
                        + "description: " + res.getString("description"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VideoEnDirectCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public ObservableList<VideoEnDirect>  afficherVideoEnDirect(){
          try {
            Connection connect;
            connect = ConnexionSingleton.getInstance();
            data = FXCollections.observableArrayList();
            ResultSet rs = connect.createStatement().executeQuery("select * from video_direct");
            while (rs.next()) {
                data.add(new VideoEnDirect(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)) );
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
        return data;
     }
     
}
