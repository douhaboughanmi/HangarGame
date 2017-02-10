/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.JeuxVideo;
import hangargame.serviceinterface.IJeuxVideoCrud;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mishka
 */
public class CrudJeuxVideo implements IJeuxVideoCrud {
     Connection connect;
    Statement ste ;
    PreparedStatement prepste;
    public CrudJeuxVideo() {
        
           connect=  ConnexionSingleton.getInstance();
           
    }

    @Override
    public void ajouterJeuxVideo(String nom, String genre, String date_sortie, String description, String image, String nom_console, String video_ba) {
         JeuxVideo j = new JeuxVideo(nom, genre, date_sortie, description, image, nom_console, video_ba);
         
           String req1="insert into jeux_video(nom,genre,date_sortie,description,image,nom_console,video_ba)values(?,?,?,?,?,?,?)";
        try {
           // String req1="insert into jeux_video(nom,genre)values(?,?)";
         // InputStream inputStream= new FileInputStream(image);
          
           prepste=connect.prepareStatement(req1);
           
           prepste.setString(1,j.getNom() );
            prepste.setString(2,j.getGenre() );
           
            prepste.setString(3,j.getDate_sortie() );
             prepste.setString(4,j.getDescription() );
             prepste.setString(5,j.getImage() );
             prepste.setString(6,"testNomConsole" );
             prepste.setString(7,"testVideo" );
              



          
           prepste.executeUpdate();
             System.out.println("c'est fait");
        } catch (SQLException ex) {
            Logger.getLogger(CrudJeuxVideo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerJeuxVideo(JeuxVideo j) {
        try {
            String req2= "delete from jeux_video where nom=?";
            
            prepste = connect.prepareStatement(req2);
           prepste.setString(1, j.getNom());
           prepste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudJeuxVideo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void modifierJeuxVideo(JeuxVideo j) {
        try {
            String req3="update  jeux_video set nom=?"
                    + "nom= ?"
                    +"genre= ?"
                     +"date_sortie= ?"
                     +"description= ?"
                    +"image=?"
                    +"nom_console=?"
                    +"video_ba=?";
           
           
            ste.executeUpdate(req3);
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudJeuxVideo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void afficherJeuxVideo() {
         String req4= "select * from jeux_video";
        try {
            ResultSet res  =ste.executeQuery(req4);
            while (res.next()) { 
                System.out.println("nom : "+res.getString("nom")+" "+
                                   "genre "+ res.getString("genre")+" "+
                                    " date de sortie: "+ res.getDate("date_sortie")+" "+
                                     " description: "+ res.getString("description")+" "+
                                     " image: "+ res.getString("image")+" "+
                                    "nom console: "+ res.getString("nom_console")+" "+
                                    "video bande d'annonce :" +res.getDate("video_ba"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudJeuxVideo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

   

