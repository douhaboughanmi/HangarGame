/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Annonces;
import hangargame.controller.AccueilAnnonceController;
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
 * @author mayss
 */
public class CrudFavoris {
  int  idAnnonce=0;
     String   signaleurr="";
    
    
     Connection connect;
    Statement ste;
    PreparedStatement prepste;
    List<Annonces>list = new ArrayList<>() ;

    public CrudFavoris() {
  connect = ConnexionSingleton.getInstance();
        try {
            connect = ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
  
    
    
     public String ajouterAnnoncesFavoris() {
         
         
        idAnnonce=0;
        signaleurr="";
        String req5 = "Select * from favoris"
                + " where idAnnonce=? AND login= ? ";
        try {System.out.println("select");
            prepste = connect.prepareStatement(req5);
            prepste.setInt(1, Integer.parseInt(AccueilAnnonceController.indexAnnonce));
            
            prepste.setString(2,"Halim");
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                idAnnonce = rs.getInt("id");
                signaleurr = rs.getString("login");
                
                //System.out.println("test" + nbrSignalisation + idSignalisation);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if(idAnnonce==Integer.parseInt(AccueilAnnonceController.indexAnnonce)&&
                signaleurr.equals("Halim")){
        
            return"vous avez déja aimez cette annonce";
        
        }
        else{
  
        String req1 = " insert into favoris (login,idAnnonce)"
                + " values (?, ?)";

        try {

            prepste = connect.prepareStatement(req1);
            prepste.setString(1, "LoginController.LoginStatic");
            prepste.setInt(2, Integer.parseInt(AccueilAnnonceController.indexAnnonce));
            prepste.executeUpdate();
            System.out.println("c'est fait");

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }

        //SendMail sendMail = new SendMail();
        //  sendMail.send();
      return null;
    }}
     
     
     public List<Annonces> afficherFavoris(){
     String req5 ="SELECT * from annonces,favoris   WHERE annonces.idAnnonces = favoris.idAnnonce AND "
             + "favoris.login='hamza'";
         
      try {
          prepste = connect.prepareStatement(req5);
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                String nomA = rs.getString("nomAnnonces");
                String typeAnnonces = rs.getString("typeAnnonces");
                int prixAnnonces = rs.getInt("prixAnnonces");
                int id = rs.getInt("idAnnonces");
                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Blob image = rs.getBlob("imageAnnonces");

                InputStream inputStream = image.getBinaryStream();
                Annonces annonces = new Annonces(id,nomA, typeAnnonces, "", "", prixAnnonces, inputStream);
                list.add(annonces);
                

            }
          return list;
          
          
      } catch (SQLException ex) {
          Logger.getLogger(CrudFavoris.class.getName()).log(Level.SEVERE, null, ex);
      }
     
     return null;}
    
    
}
