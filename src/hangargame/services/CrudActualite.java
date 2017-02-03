/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Actualite;
import hangargame.serviceinterface.IActualiteCrud;
import java.sql.Connection;
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
public class CrudActualite implements IActualiteCrud{
     Connection connect;
    Statement ste ;
    PreparedStatement prepste;
    public CrudActualite() {
        try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterActualite(Actualite a) {
        try {
            String req1="insert into actualite (titre,text,date_debut,date_fin,image,video)values"
                    + "("+a.getTitre()+",' "+a.getText()+","+a.getDate_debut()+",' "+a.getDate_fin()+",' "+a.getImage()+"',' "+a.getVideo()+"')";
            
           
            ste.executeUpdate(req1);
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerActualite(Actualite a) {
        
        try {
            String req2= "delete from actualite where titre=?";
            
            prepste = connect.prepareStatement(req2);
           prepste.setString(1, a.getTitre());
           prepste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void modifierActualite(Actualite a) {

        try {
            String req3="update  actualite set titre=?"
                    + "titre= ?"
                    +"text= ?"
                    +"date_debut=?"
                    +"date_fin=?"
                    +"image=?"
                    +"video=?";
           
           
            ste.executeUpdate(req3);
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void afficherActualite() {String req4= "select * from actualite";
        try {
            ResultSet res  =ste.executeQuery(req4);
            while (res.next()) { 
                System.out.println("titre : "+res.getString("titre")+" "+
                                   "text: "+ res.getString("text")+" "+
                                    " date_debut: "+ res.getDate("date_debut")+" "+
                                     " date_fin: "+ res.getDate("date_fin")+" "+
                                    "image: "+ res.getString("image")+" "+
                                    "video :" +res.getDate("video"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

   

