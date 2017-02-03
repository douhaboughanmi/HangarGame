/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.services;

import hangargame.entites.Gamer;
import hangargame.serviceinterface.IServiceGamer;
import hangargame.connexionDB.ConnexionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author lenovo
 */
public class ServicesGamer implements IServiceGamer{

    
    Connection connect;
    Statement ste ;
    PreparedStatement prepste;
    public ServicesGamer() {
        try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void Inscription(Gamer g) {
       
        String req2 = "select * from Gamer where email= '" + g.getEmail() + "'";
        String req3 = "select * from Gamer where login= '" + g.getLogin() + "'";
        String req = "Insert into Gamer(login,nom,prenom,adresse,tel,email,password,dateInscription,codeValidation,LastModifMdp) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try {
          prepste = connect.prepareStatement(req2);
          
           prepste.executeUpdate();
           prepste = connect.prepareStatement(req3);
          
           prepste.executeUpdate();
            int x = prepste.getMaxRows();
            int y = prepste.getMaxRows();
            if ((x != 0) && (y != 0)) {
                System.out.println("Votre E-mail et login existe deja");
            }
            if (x != 0) {
                System.out.println("Votre E-mail existe deja");
            }
            if (y != 0) {
                System.out.println("Votre login existe deja");
            } else if ((x == 0) && (y == 0)) {

                prepste = connect.prepareStatement(req);

                 prepste.setString(1, g.getLogin());
                 prepste.setString(2, g.getNom());
                 prepste.setString(3, g.getPrenom());
                 prepste.setString(4, g.getAdresse());
                 prepste.setString(5, g.getTel());
                 prepste.setString(6, g.getEmail());
               
                 prepste.setString(7, g.getPassword());
                 prepste.setDate(8, g.getDateInscription());
                 prepste.setString(9, g.getCodeValidation());
                prepste.setDate(10, g.getLastModifMdp());
                
              
                 prepste.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gamer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

     @Override
    public boolean Authentification(String login, String password) {
        System.out.println(login);
        System.out.println(password);
    String req4= "select * from Gamer where Login = '" + login + "'and Password = '" + password + "'";
        try {
            ResultSet res  =ste.executeQuery(req4);
            while (res.next()) { 
              
              return true;
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
  }

    
    

