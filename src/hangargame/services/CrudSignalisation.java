/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.xml.AccueilAnnonceController;
import hangargame.xml.LoginController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mayss
 */
public class CrudSignalisation {
    Connection connect;
    Statement ste;
    PreparedStatement prepste;
    int nbrSignalisation;
    int idSignalisation;
    String signaleur;
       int     idAnnonce;
        String   signaleurr;
         int nbr=0;
    public CrudSignalisation() {
        
        connect = ConnexionSingleton.getInstance();
        try {
            connect = ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
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
}
    
    
    public void ajoutSignaleur(){
          String req=  "INSERT INTO signaleurannonce(id,login) "
                    + "VALUES (?,?)";
          
           try {
                prepste = connect.prepareStatement(req);
                prepste.setInt(1, Integer.parseInt(AccueilAnnonceController.indexAnnonce));
                prepste.setString(2, "Halim");
               
                
                prepste.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
            }

}
    
    
    public String signalerAnnonce(String gamer) {
        idAnnonce=0;
        signaleurr="";
        String req5 = "Select * from signaleurannonce"
                + " where id=? AND login= ? ";
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
        
            return"vous avez déja signaler cette annonce";
        
        }
        
        
        
        else{
        
        
        
        
       
        String req0 = "Select idSignalisation,nbrSignalisation from Signalisation where idObjet=? AND typeSignalisation= ? ";
        try {System.out.println("select");
            prepste = connect.prepareStatement(req0);
            prepste.setInt(1, Integer.parseInt(AccueilAnnonceController.indexAnnonce));
            
            prepste.setString(2,"Annonce");
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                nbrSignalisation = rs.getInt("nbrSignalisation");
                idSignalisation = rs.getInt("idSignalisation");
                
                System.out.println("test" + nbrSignalisation + idSignalisation);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       

        if (nbrSignalisation == 4) {

            String req1 = "Delete  from annonces where idAnnonces = ?";

            try {

                prepste = connect.prepareStatement(req1);
                prepste.setInt(1,Integer.parseInt(AccueilAnnonceController.indexAnnonce));

                prepste.executeUpdate();
                System.out.println("c'est fait");

            } catch (SQLException ex) {
                Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (nbrSignalisation == 0) {
            ajoutSignaleur();
            System.out.println("insert");
            String req1 = "INSERT INTO Signalisation(idObjet,typeSignalisation,gamerSignale,nbrSignalisation) "
                    + "VALUES (?,?,?,?)";

            try {
                prepste = connect.prepareStatement(req1);
                prepste.setInt(1, Integer.parseInt(AccueilAnnonceController.indexAnnonce));
                prepste.setString(2, "Annonce");
                prepste.setString(3, gamer);
                prepste.setInt(4, nbrSignalisation + 1);
                
                prepste.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            ajoutSignaleur();
            System.out.println("update");
            String req1
                    = //" INSERT INTO Signalisation(idObjet,typeSignalisation,gamerSignale,nbrSignalisation) "
                    //+ "VALUES (?,?,?,?)";
                    "UPDATE Signalisation SET idObjet = ?, typeSignalisation = ?,gamerSignale=?"
                    + ",nbrSignalisation=?"
                    + " WHERE idObjet = ?";

            try {

                prepste = connect.prepareStatement(req1);
                prepste.setInt(1,Integer.parseInt(AccueilAnnonceController.indexAnnonce) );
                prepste.setString(2, "Annonce");
                prepste.setString(3, "mayssa");
                prepste.setInt(4, nbrSignalisation + 1);
                prepste.setInt(5, Integer.parseInt(AccueilAnnonceController.indexAnnonce));
                prepste.executeUpdate();
                System.out.println("c'est fait");

            } catch (SQLException ex) {
                Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //SendMail sendMail = new SendMail();
        //  sendMail.send();
        return "";
    }}
    
    
}
