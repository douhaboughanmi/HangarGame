/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Annonces;
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

/**
 *
 * @author lenovo
 */
public class CrudSignalisation {
    Connection connect;
    Statement ste ;
    PreparedStatement prepste;
    int nbr=0;
    int i[] =new int[5] ;
    int j,k=0;
    

    public CrudSignalisation() {
        connect=  ConnexionSingleton.getInstance(); 
    try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.createStatement();
   
    
    }   catch (SQLException ex) {
            Logger.getLogger(CrudSignalisation.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
    }
    
    
    
    public int signalisationGamer(String login){
    nbr=0;
    
         String query = "Select nbrSignalisation from signalisation where gamerSignale=? ";
      
        try {
            prepste = connect.prepareStatement(query);
           prepste.setString(1, login);
            ResultSet rs = prepste.executeQuery();
            while(rs.next()){
           
                nbr+= rs.getInt("nbrSignalisation");
              
              
            return nbr;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
    return nbr;
}}
