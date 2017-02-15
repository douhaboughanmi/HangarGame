package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.VideoTest;
import hangargame.serviceinterface.ICrudVideoTest;
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
 * @author DELL
 */
public class VideoTestCrud implements ICrudVideoTest{
    private ObservableList<VideoTest> data;
    
     Connection connect;
    Statement ste ;
    PreparedStatement prepste;
    
    public VideoTestCrud (){
        

         try {
              connect=  ConnexionSingleton.getInstance();
             ste = connect.createStatement();
         } catch (SQLException ex) {
             Logger.getLogger(VideoTestCrud.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void ajouter(VideoTest v) {
        String req1= " insert into video_test ( nom,url,description,date,genre,console,user)values( ?,?,?,?,?,?,?)";
         try {
               
             	
             
             prepste = connect.prepareStatement(req1);
             prepste.setString(1,v.getNom_videoTest());
             prepste.setString(2,v.getUrl_videoTest());
             prepste.setString(3, v.getDescription_videoTest());
             prepste.setTimestamp(4, v.getDate_videoTest());
    
             prepste.setString(5, v.getGenre_videoTest());
              prepste.setString(6, v.getConsole_videoTest());
              prepste.setString(7, v.getUser_videoTest());
              
             
             
             prepste.executeUpdate();
             System.out.println("ajout avec fait");
           
         } catch (SQLException ex) {
             Logger.getLogger(VideoTestCrud.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("leeeeeeee");
            
             
         }
                    
            
         
        
      
    }

    @Override
    public void supprimer(int id) {
        
   
         try {
             VideoTest v = new VideoTest();
             String req2= "delete from video_test where id=?";
             
            prepste = connect.prepareStatement(req2);
             prepste.setInt(1,id);
             prepste.execute();
                System.out.println("leeeeeeeeeeee");
         } catch (SQLException ex) {
             Logger.getLogger(VideoTestCrud.class.getName()).log(Level.SEVERE, null, ex);
          
         }
    }

    @Override
    public void modifier(VideoTest v) {
        
         try {
           
             
             String req3="UPDATE video_test SET id=?"
                     + "nom=?"
                     + "url=?"
                     + "description=?"
                     + "date=?" ;
             
             prepste = connect.prepareStatement(req3);
             prepste.setString(1,v.getNom_videoTest());
             prepste.setString(2,v.getUrl_videoTest());
             prepste.setString(3, v.getDescription_videoTest());
             prepste.setTimestamp(4, v.getDate_videoTest());
             
             
             prepste.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(VideoTestCrud.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        
        
    }

    @Override
    public void afficher() {
        
        
           String req4= "select * from video_test";
          
         try {
          
             
             ResultSet res  =ste.executeQuery(req4);
             while (res.next()) {
                 System.out.println(
                         ": "+ res.getString("nom")+" "+
                           "url: "+ res.getString("url")+" "+
                           "description: "+ res.getString("description")+" "+
                         "date :" +res.getDate("date"));
                 
                 
                 
             }    } catch (SQLException ex) {
             Logger.getLogger(VideoTestCrud.class.getName()).log(Level.SEVERE, null, ex);
         }}
         
         public ObservableList<VideoTest>  afficherVideoTest(){
         
         
          try {
            Connection connect;
            connect = ConnexionSingleton.getInstance();
            data = FXCollections.observableArrayList();
            ResultSet rs = connect.createStatement().executeQuery("select * from video_test");
            while (rs.next()) {
                data.add(new VideoTest(rs.getInt(1),rs.getString(2), rs.getString(3) ,rs.getString(4),rs.getTimestamp(5), rs.getString(6),rs.getString(7),rs.getString(8)));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
        return data;
         
         
         }
    

    
   

    
    
}