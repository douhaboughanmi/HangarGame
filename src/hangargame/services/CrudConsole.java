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
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
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
        List<Console> list = new ArrayList<>();
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
    public void supprimerConsole(String nom ) {
        try {
           
            String req2 = "delete from console where nom=?";

            prepste = connect.prepareStatement(req2);
            prepste.setString(1, nom);
            prepste.execute();
            System.out.println("ciiiiiiiii ");
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierConsole(Console c) {
        
        System.out.println(c.getId());
        String req3 = "update console set nom=?,"
               
               // + "image= ?,"
                
                + "description= ?,"
                + "date_sortie= ?"
                +"where id=?"
              //  + "image=?"
                //+ "nom_console=?"
                //+ "video_ba=?"
                ;
                
        try {
            prepste = connect.prepareStatement(req3);
            prepste.setString(1, c.getNom());
         //   prepste.setString(2, c.getImage());
            prepste.setString(2, c.getDescription());
            prepste.setString(3, c.getDate_sortie());
            prepste.setInt(4, c.getId());
            //System.out.println(c.getId());
            prepste.executeUpdate();
            System.out.println("c'est fait");
          
            
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
                data.add(new Console(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
          return data;
    }

    

    @Override
    public List<Console> reccuperer() {
        
        String query = "Select * from console";
        try {
            prepste = connect.prepareStatement(query);
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                int idcon = rs.getInt("id");
                String nomc = rs.getString("nom");
                

                // String datej = rs.getString("date_sortie");
                String desc = rs.getString("description");
                 String imagee = rs.getString("image");

                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
                Console con = new Console(idcon, nomc, "", desc, "");
              //  JeuxVideo jj = new JeuxVideo(nomj, genrej, "",desc, "","", "");
                
                list.add(con);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
    @Override
    public List<Console> reccuperer2() {
        
        String query = "Select nom from console";
        try {
            prepste = connect.prepareStatement(query);
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                
                String nomc = rs.getString("nom");
                  Console con = new Console(nomc);
              //  JeuxVideo jj = new JeuxVideo(nomj, genrej, "",desc, "","", "");
                
                list.add(con);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    
   
    }

}