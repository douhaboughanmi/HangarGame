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
import java.sql.Blob;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mayss
 */
public class CrudAnnonces {
      Connection connect;
    Statement ste ;
    PreparedStatement prepste;
    List<Annonces> list = new ArrayList<>();
    
    public CrudAnnonces(){
     connect=  ConnexionSingleton.getInstance(); try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
    
    
  public void  ajouterAnnonces(Annonces a
         ){
      
      
      String req1= " insert into annonces (nomAnnonces, typeAnnonces, consoleAnnonces, descriptionAnnonces, prixAnnonces,imageAnnonces,emailGamer)"
        + " values (?, ?, ?, ?, ?,?,?)";
      
        
      
      
        try {
            
            
            
            prepste = connect.prepareStatement(req1);
            prepste.setString(1,a.getNomAnnonces());
            prepste.setString(2,a.getTypeAnnonces());
            prepste.setString(3, "");
            prepste.setString(4, a.getDescriptionAnnonces());
            prepste.setInt(5,a.getPrix());
            prepste.setBlob(6, a.getInputStream());
            prepste.setString(7,"");
           // prepste.setTimestamp(8,null);
            prepste.executeUpdate();
            System.out.println("c'est fait");
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        } 
  
  //SendMail sendMail = new SendMail();
       //  sendMail.send();
  
  }
  
    
  public List<Annonces> reccupererSimple() {
  
      String query = "Select * from annonces";
      
        try {
            prepste = connect.prepareStatement(query);
            ResultSet rs = prepste.executeQuery();
            while(rs.next()){
            String nomA =rs.getString("nomAnnonces");
            String typeAnnonces = rs.getString("typeAnnonces");
            int prixAnnonces = rs.getInt("prixAnnonces");
            //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
            Blob image = rs.getBlob("imageAnnonces");
                
                   InputStream inputStream= image.getBinaryStream();
                     Annonces annonces = new Annonces(nomA, typeAnnonces,"", "",prixAnnonces,  inputStream);
                     list.add(annonces);
                
          
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
  
  
  }
  
  
  public List<Annonces> reccupererSelonEchange() {
  
      String query = "Select * from annonces where typeAnnonces=?";
      
        try {
            
            prepste = connect.prepareStatement(query);
            prepste.setString(1,"Echange");
            ResultSet rs = prepste.executeQuery();
            while(rs.next()){
            String nomA =rs.getString("nomAnnonces");
            String typeAnnonces = rs.getString("typeAnnonces");
            int prixAnnonces = rs.getInt("prixAnnonces");
            //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
            Blob image = rs.getBlob("imageAnnonces");
                
                   InputStream inputStream= image.getBinaryStream();
                     Annonces annonces = new Annonces(nomA, typeAnnonces,"", "",prixAnnonces,  inputStream);
                     list.add(annonces);
                
          
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
  
  
  }
  
    
  public List<Annonces> reccupererSelonVente() {
  
      String query = "Select * from annonces where typeAnnonces=?";
      
        try {
            
            prepste = connect.prepareStatement(query);
            prepste.setString(1,"Vente");
            ResultSet rs = prepste.executeQuery();
            while(rs.next()){
            String nomA =rs.getString("nomAnnonces");
            String typeAnnonces = rs.getString("typeAnnonces");
            int prixAnnonces = rs.getInt("prixAnnonces");
            //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
            Blob image = rs.getBlob("imageAnnonces");
                
                   InputStream inputStream= image.getBinaryStream();
                     Annonces annonces = new Annonces(nomA, typeAnnonces,"", "",prixAnnonces,  inputStream);
                     list.add(annonces);
                
          
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
  
  
  }
  
  
   public List<Annonces> reccupererSelonPC() {
  
      String query = "Select * from annonces where consoleAnnonces=?";
      
        try {
            
            prepste = connect.prepareStatement(query);
            prepste.setString(1,"PC");
            ResultSet rs = prepste.executeQuery();
            while(rs.next()){
            String nomA =rs.getString("nomAnnonces");
            String typeAnnonces = rs.getString("typeAnnonces");
            int prixAnnonces = rs.getInt("prixAnnonces");
            //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
            Blob image = rs.getBlob("imageAnnonces");
                
                   InputStream inputStream= image.getBinaryStream();
                     Annonces annonces = new Annonces(nomA, typeAnnonces,"", "",prixAnnonces,  inputStream);
                     list.add(annonces);
                
          
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
  
  
  }
  
  
   public List<Annonces> reccupererSelonPS4() {
  
      String query = "Select * from annonces where consoleAnnonces=?";
      
        try {
            
            prepste = connect.prepareStatement(query);
            prepste.setString(1,"PS4");
            ResultSet rs = prepste.executeQuery();
            while(rs.next()){
            String nomA =rs.getString("nomAnnonces");
            String typeAnnonces = rs.getString("typeAnnonces");
            int prixAnnonces = rs.getInt("prixAnnonces");
            //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
            Blob image = rs.getBlob("imageAnnonces");
                
                   InputStream inputStream= image.getBinaryStream();
                     Annonces annonces = new Annonces(nomA, typeAnnonces,"", "",prixAnnonces,  inputStream);
                     list.add(annonces);
                
          
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
  
  
  }
   
    public List<Annonces> reccupererSelonPS3() {
  
      String query = "Select * from annonces where consoleAnnonces=?";
      
        try {
            
            prepste = connect.prepareStatement(query);
            prepste.setString(1,"PS3");
            ResultSet rs = prepste.executeQuery();
            while(rs.next()){
            String nomA =rs.getString("nomAnnonces");
            String typeAnnonces = rs.getString("typeAnnonces");
            int prixAnnonces = rs.getInt("prixAnnonces");
            //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
            Blob image = rs.getBlob("imageAnnonces");
                
                   InputStream inputStream= image.getBinaryStream();
                     Annonces annonces = new Annonces(nomA, typeAnnonces,"", "",prixAnnonces,  inputStream);
                     list.add(annonces);
                
          
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
  
  
  }
   
    public List<Annonces> reccupererSelonXbox360() {
  
      String query = "Select * from annonces where consoleAnnonces=?";
      
        try {
            
            prepste = connect.prepareStatement(query);
            prepste.setString(1,"Xbox 360");
            ResultSet rs = prepste.executeQuery();
            while(rs.next()){
            String nomA =rs.getString("nomAnnonces");
            String typeAnnonces = rs.getString("typeAnnonces");
            int prixAnnonces = rs.getInt("prixAnnonces");
            //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
            Blob image = rs.getBlob("imageAnnonces");
                
                   InputStream inputStream= image.getBinaryStream();
                     Annonces annonces = new Annonces(nomA, typeAnnonces,"", "",prixAnnonces,  inputStream);
                     list.add(annonces);
                
          
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
  
  
  }
    
     public List<Annonces> reccupererSelonXboxOne() {
  
      String query = "Select * from annonces where consoleAnnonces=?";
      
        try {
            
            prepste = connect.prepareStatement(query);
            prepste.setString(1,"Xbox One");
            ResultSet rs = prepste.executeQuery();
            while(rs.next()){
            String nomA =rs.getString("nomAnnonces");
            String typeAnnonces = rs.getString("typeAnnonces");
            int prixAnnonces = rs.getInt("prixAnnonces");
            //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
            Blob image = rs.getBlob("imageAnnonces");
                
                   InputStream inputStream= image.getBinaryStream();
                     Annonces annonces = new Annonces(nomA, typeAnnonces,"", "",prixAnnonces,  inputStream);
                     list.add(annonces);
                
          
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
  
  
  }
     public List<Annonces> reccupererSelonPSVita() {
  
      String query = "Select * from annonces where consoleAnnonces=?";
      
        try {
            
            prepste = connect.prepareStatement(query);
            prepste.setString(1,"PS Vita");
            ResultSet rs = prepste.executeQuery();
            while(rs.next()){
            String nomA =rs.getString("nomAnnonces");
            String typeAnnonces = rs.getString("typeAnnonces");
            int prixAnnonces = rs.getInt("prixAnnonces");
            //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
            Blob image = rs.getBlob("imageAnnonces");
                
                   InputStream inputStream= image.getBinaryStream();
                     Annonces annonces = new Annonces(nomA, typeAnnonces,"", "",prixAnnonces,  inputStream);
                     list.add(annonces);
                
          
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
  
  
  }
    
}
