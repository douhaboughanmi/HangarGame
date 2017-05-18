/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.commentaireVideoTest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class CommentaireVideoTestCrud {
    

    Connection connect;
    Statement ste;
    PreparedStatement prepste;
    List<commentaireVideoTest> list = new ArrayList<>();
     private ObservableList<commentaireVideoTest> data;

    public CommentaireVideoTestCrud() {
        try {
            connect = ConnexionSingleton.getInstance();
            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireVideoTestCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ajouterCommentaire(commentaireVideoTest c) {
        String req1 = " insert into commentaire_video (commentaire,idvideo)values( ?,?)";
        try {

            prepste = connect.prepareStatement(req1);
            prepste.setString(1, c.getCommentaire());
            //prepste.setString(2, c.getUser());
            prepste.setString(2, c.getIdVideo());

            prepste.executeUpdate();
            System.out.println("ajout avec fait");

        } catch (SQLException ex) {
            Logger.getLogger(VideoTestCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err");
            System.out.println(c.getCommentaire());

        }
    }
    
    

    public List<commentaireVideoTest> afficher(String id) {

        String x = id;

        String req4 = "select * from video_test V , commentaire_video C where v.id=C.idvideo AND C.idvideo="+x+"";

        try {
            System.out.println(id);
             
            prepste = connect.prepareStatement(req4);
        // prepste.setString(1, id);
            

            ResultSet res = ste.executeQuery(req4);
            while (res.next()) {

                int i = res.getInt("id");
                String a = res.getString("commentaire");
                String b = res.getString("usernom");
                String d = res.getString("idvideo");

                commentaireVideoTest c = new commentaireVideoTest(a, b, d);
                
                list.add(c);
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(VideoTestCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
      public ObservableList<commentaireVideoTest>  afficherCommentaire(){
         
         
          try {
            Connection connect;
            connect = ConnexionSingleton.getInstance();
            data = FXCollections.observableArrayList();
            ResultSet rs = connect.createStatement().executeQuery("select * from commentaire_video");
            while (rs.next()) {
                data.add(new commentaireVideoTest(rs.getInt(1),rs.getString(4), rs.getString(2) ,rs.getString(5)));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
        return data;
         
         
         }
      
      
    public void supprimer(int id) {
        
   
         try {
             commentaireVideoTest co = new commentaireVideoTest();
             String req2= "delete from commentaire_video where id=?";
             
            prepste = connect.prepareStatement(req2);
             prepste.setInt(1,id);
             prepste.execute();
                System.out.println("leeeeeeeeeeee");
         } catch (SQLException ex) {
             Logger.getLogger(VideoTestCrud.class.getName()).log(Level.SEVERE, null, ex);
          
         }
    }

}
