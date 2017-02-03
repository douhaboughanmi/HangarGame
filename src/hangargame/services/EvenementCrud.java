/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.serviceinterface.IEvenementCrud;
import hangargame.entites.Evenement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import hangargame.connexionDB.ConnexionSingleton;

/**
 *
 * @author Louay
 */
public class EvenementCrud implements IEvenementCrud{
    Connection connect;
    Statement ste ;
    PreparedStatement prepste;
    public EvenementCrud() {
        try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(TournoiCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterEvenement(Evenement e) {
        String req1="insert into evenement (nom,description,adresse,datedebut,datefin)values(?,?,?,?,?)";
        try {
            System.out.println("Wiouwwwww");
            
            prepste=connect.prepareStatement(req1);
            prepste.setString(1,e.getNom());
            prepste.setString(2,e.getDescription());
            prepste.setString(3,e.getAdresse());
            prepste.setDate(4,e.getDatedebut());
            prepste.setDate(5,e.getDatefin());
            
            prepste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerEvenement(Evenement e) {
       try {
             String req2= "delete from evenement where id=?";
             
             prepste = connect.prepareStatement(req2);
             prepste.setInt(1, e.getId());
             prepste.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void modifierEvenement(Evenement e) {
          String req3="UPDATE evenement SET id=?"
                 + "nom=?"
                 + "description=?"
                 + "adresse=?"
                 + "datedebut=?"
                  + "datefin=?";
         try {
             ste.executeUpdate(req3);
         } catch (SQLException ex) {
             Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void afficherEvenement() {
       String req4= "select * from evenement";
          
         try {
          
             
             ResultSet res  =ste.executeQuery(req4);
             while (res.next()) {
                 System.out.println(
                         ": "+ res.getString("nom")+" "+
                           "Description: "+ res.getString("description")+" "+
                           "adresse: "+ res.getInt("adresse")+" "+
                         "Date Debut: "+ res.getTimestamp("datedebut")+" "+
                                    "Date Fin :" +res.getTimestamp("datefin"));
                 
                 
                 
             }    } catch (SQLException ex) {
             Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    
    
}
