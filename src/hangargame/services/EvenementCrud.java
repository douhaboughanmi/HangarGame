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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Louay
 */
public class EvenementCrud implements IEvenementCrud{
    Connection connect;
    Statement ste ;
    PreparedStatement prepste;
     private ObservableList<Evenement> data;
    public EvenementCrud() {
        try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterEvenement(Evenement e) {
        String req1="insert into evenement (nom,description,adresse,datedebut,datefin)values(?,?,?,?,?)";
        try {
            
            prepste=connect.prepareStatement(req1);
            prepste.setString(1,e.getNom());
            prepste.setString(3,e.getDescription());
            prepste.setString(2,e.getAdresse());
            prepste.setString(4,e.getDatedebut());
            prepste.setString(5,e.getDatefin());
            
            prepste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerEvenement(int id) {
       try {
           Evenement e = new Evenement();
             String req2= "delete from evenement where id=?";
             
             prepste = connect.prepareStatement(req2);
             prepste.setInt(1,id);
             prepste.execute();
             
         } catch (SQLException ex) {
             Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void modifierEvenement(Evenement e) {
         try { 
        String req3="UPDATE evenement SET id=?"
                 + "nom=?"
                 + "description=?"
                 + "adresse=?"
                 + "datedebut=?"
                  + "datefin=?";
        
             ste.executeUpdate(req3);
         } catch (SQLException ex) {
             Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public ObservableList<Evenement> afficherEvenement() {
       
          
        try {
            Connection connect;
            connect = ConnexionSingleton.getInstance();
            data = FXCollections.observableArrayList();
            ResultSet rs = connect.createStatement().executeQuery("select * from evenement");
            while (rs.next()) {
                data.add(new Evenement(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
        return data;
    }

    
    
}
