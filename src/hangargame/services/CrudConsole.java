/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Console;
import hangargame.serviceinterface.IConsoleCrud;
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
public class CrudConsole implements IConsoleCrud{
    
     Connection connect;
    Statement ste ;
    PreparedStatement prepste;
    public CrudConsole() {
        try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void ajouterConsole(Console c) {
        try {
            String req1="insert into console (nom,image,description,video_bd,date_sortie)values"
                    + "("+c.getNom()+",' "+c.getImage()+","+c.getDescription()+",' "+c.getVideo_bd()+",' "+c.getDate_sortie()+"')";
            
           
            ste.executeUpdate(req1);
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerConsole(Console c) {
        try {
            String req2= "delete from console where nom=?";
            
            prepste = connect.prepareStatement(req2);
           prepste.setString(1, c.getNom());
           prepste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierConsole(Console c) {
        try {
            String req3="update  console set nom=?"
                    + "nom= ?"
                    +"image= ?"
                    +"description=?"
                    +"video_bd=?"
                    +"date_sortie=?";
           
           
            ste.executeUpdate(req3);
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void afficherConsole() {
         String req4= "select * from console";
        try {
            ResultSet res  =ste.executeQuery(req4);
            while (res.next()) { 
                System.out.println("nom : "+res.getString("nom")+" "+
                                   "image: "+ res.getString("image")+" "+
                                    " description: "+ res.getString("description")+" "+
                                    "video bande d'annonce: "+ res.getString("video_bd")+" "+
                                    "Date de sortie :" +res.getDate("date_sortie"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
}
