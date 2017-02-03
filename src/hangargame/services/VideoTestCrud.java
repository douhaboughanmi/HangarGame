/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.serviceinterface.ICrudVideoCom;
import hangargame.entites.VideoTest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author DELL
 */
public class VideoTestCrud implements ICrudVideoCom{
    
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
    public void ajouter(Object o) {
        
        VideoTest v = (VideoTest)o;
        String req1="insert into video_test (id,nom,url,description,date)values(?,?,?,?,?)";
                    
            
           
         try {
             ste.executeUpdate(req1);
         } catch (SQLException ex) {
             Logger.getLogger(VideoTestCrud.class.getName()).log(Level.SEVERE, null, ex);
         }
      
    }

    @Override
    public void supprimer(Object o) {
        
         VideoTest v = (VideoTest)o;
         try {
             String req2= "delete from video_test where id=?";
             
             prepste = connect.prepareStatement(req2);
             prepste.setInt(1, v.getId_videoTest() );
             prepste.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(VideoTestCrud.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void modifier(Object o) {
        
         VideoTest v = (VideoTest)o;
          String req3="UPDATE video_test SET id=?"
                 + "nom=?"
                 + "url=?"
                 + "description=?"
                 + "date=?" ;
         try {
             ste.executeUpdate(req3);
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
         }
    

    

   

    
    
}}
