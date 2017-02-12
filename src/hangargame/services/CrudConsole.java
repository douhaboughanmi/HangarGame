/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Console;
import hangargame.entites.JeuxVideo;
import hangargame.serviceinterface.IConsoleCrud;
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
public class CrudConsole implements IConsoleCrud{
    
     Connection connect;
    Statement ste ;
    PreparedStatement prepste;
        private ObservableList<Console> data;
    public CrudConsole() {
        try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void ajouterConsole(Console c) {
         String req1="insert into console (nom,image,description,date_sortie)values(?,?,?,?)";
        try {
           prepste=connect.prepareStatement(req1);
              prepste.setString(1,c.getNom() );
            prepste.setString(2,c.getImage() );
           
            prepste.setString(3,c.getDescription());
             prepste.setString(4,c.getDate_sortie());
               prepste.executeUpdate();
             
               System.out.println("c'est fait");
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerConsole(Console c) {
        try {
            String req2= "delete from console where nom=?";
            
            prepste = connect.prepareStatement(req2);
           prepste.setString(1, c.getNom());
           prepste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierConsole(Console c) {
        try {
            String req3="update  console set nom=?"
                    + "nom= ?"
                    +"image= ?"
                    +"description=?"
                    +"video_bd=?"
                    +"date_sortie=?";
           
           
            ste.executeUpdate(req3);
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 @Override
    public ObservableList<Console>  afficherConsole() {
         data = FXCollections.observableArrayList();
          try {
            ResultSet rs = connect.createStatement().executeQuery("select * from console");
            while (rs.next()) {
                data.add(new Console(rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
          return data;
    }

    public ObservableList<Console> ajouterConsole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
