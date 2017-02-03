/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.serviceinterface.ITournoiCrud;
import hangargame.entites.Tournoi;
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
public class  TournoiCrud implements ITournoiCrud{
    
    Connection connect;
    Statement ste ;
    PreparedStatement prepste;
    public TournoiCrud() {
        try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(TournoiCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterTournoi(Tournoi e) {
      
        String req1="insert into tournoi (nom,nom_jeu,nbr_max,datedebut,datefin,id_gamer)values(?,?,?,?,?,?)";
        try {
           
            prepste=connect.prepareStatement(req1);
            prepste.setString(1,e.getNom());
            prepste.setString(2,e.getNom_jeu());
            prepste.setInt(3,e.getNbr_max());
            prepste.setTimestamp(4,e.getDatedebut());
            prepste.setTimestamp(5,e.getDatefin());
            prepste.setString(6,e.getId_gamer());
            prepste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TournoiCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @Override
    public void supprimerTournoi(Tournoi e) {
       
         try {
             String req2= "delete from tournoi where id=?";
             
             prepste = connect.prepareStatement(req2);
             prepste.setInt(1, e.getId());
             prepste.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(Tournoi.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void modifierTournoi(Tournoi e) {
        
          String req3="UPDATE tournoi SET id=?"
                 + "nom=?"
                 + "nom_jeu=?"
                 + "nbr_max=?"
                 + "datedebut=?"
                  + "datefin=?";
         try {
             ste.executeUpdate(req3);
         } catch (SQLException ex) {
             Logger.getLogger(Tournoi.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void afficherTournoi() {
        String req4= "select * from tournoi";
          
         try {
          
             
             ResultSet res  =ste.executeQuery(req4);
             while (res.next()) {
                 System.out.println(
                         ": "+ res.getString("nom")+" "+
                           "nom_jeu: "+ res.getString("nom_jeu")+" "+
                           "nbr_max: "+ res.getInt("nbr_max")+" "+
                         "Date Debut: "+ res.getTimestamp("datedebut")+" "+
                                    "Date Fin :" +res.getTimestamp("datefin"));
                 
                 
                 
             }    } catch (SQLException ex) {
             Logger.getLogger(Tournoi.class.getName()).log(Level.SEVERE, null, ex);
    }

   
   
    
}}
