/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.services;

import hangargame.entites.Annonces;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import hangargame.connexionDB.ConnexionSingleton;
import hangargame.utils.SendMail;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author mayss
 */
public class CrudAnnonces {
    Connection connect;
    Statement ste ;
    PreparedStatement prepste;
    
    public CrudAnnonces(){
     connect=  ConnexionSingleton.getInstance(); try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
    
    
  public void  ajouterAnnonces(
          String nomAnnonces, String typeAnnonces,String consoleAnnonces,String descriptionAnnonces, int prix, String image){
      Annonces a = new Annonces(nomAnnonces,typeAnnonces,consoleAnnonces,descriptionAnnonces,prix);
      
      String req1= " insert into annonces (nomAnnonces, typeAnnonces, consoleAnnonces, descriptionAnnonces, prixAnnonces,imageAnnonces,emailGamer,dataAjout)"
        + " values (?, ?, ?, ?, ?,?,?,?)";
      
        
      
      
        try {
            
            InputStream inputStream= new FileInputStream(image);
            
            prepste = connect.prepareStatement(req1);
            prepste.setString(1,a.getNomAnnonces());
            prepste.setString(2,a.getTypeAnnonces());
            prepste.setString(3, a.getConsoleAnnonces());
            prepste.setString(4, a.getDescriptionAnnonces());
            prepste.setInt(5,a.getPrix());
            prepste.setBlob(6, inputStream);
            prepste.setString(7, "");
            prepste.setDate(8, null);
            prepste.executeUpdate();
            System.out.println("c'est fait");
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  SendMail sendMail = new SendMail();
         sendMail.send();
  
  }
    
}
