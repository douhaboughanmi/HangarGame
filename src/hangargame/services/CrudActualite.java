/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Actualite;
import hangargame.serviceinterface.IActualiteCrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mishka
 */
public class CrudActualite implements IActualiteCrud{
     
     Connection connect;
    Statement ste ;
    PreparedStatement prepste;
     private ObservableList<Actualite> data;
    public CrudActualite() {
        try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void ajouterActualite(Actualite a) {
       // 
      String req1="insert into actualite (titre,text,image)values(?,?,?)";
        try {
           prepste=connect.prepareStatement(req1);
              prepste.setString(1,a.getTitre() );
            prepste.setString(2,a.getText() );
           
            prepste.setString(3,a.getImage() );
               prepste.executeUpdate();
             
               System.out.println("c'est fait");
                   
            
           
         
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerActualite(Actualite a) {
        
        try {
            String req2= "delete from actualite where titre=?";
            
            prepste = connect.prepareStatement(req2);
           prepste.setString(1, a.getTitre());
           prepste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void modifierActualite(Actualite a) {

        try {
            String req3="update  actualite set titre=?"
                    + "titre= ?"
                    +"text= ?"
                    +"date_debut=?"
                    +"date_fin=?"
                    +"image=?"
                    +"video=?";
           
           
            ste.executeUpdate(req3);
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
  public ObservableList<Actualite> afficherActualite() {
         data = FXCollections.observableArrayList();
          try {
            ResultSet rs = connect.createStatement().executeQuery("select * from actualite");
            while (rs.next()) {
                data.add(new Actualite(rs.getString(2),rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
          return data;
    }
    }

   

