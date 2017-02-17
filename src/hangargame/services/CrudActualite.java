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
import java.util.ArrayList;
import java.util.List;
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
      List<Actualite> list = new ArrayList<>();
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
    public void supprimerActualite(String titre) {
        
        try {
            Actualite a = new Actualite();
            String req2= "delete from actualite where titre=?";
            
            prepste = connect.prepareStatement(req2);
           prepste.setString(1, titre);
           prepste.execute();
            System.out.println("ciiiiiiiii ");
        } catch (SQLException ex) {
            Logger.getLogger(CrudActualite.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void modifierActualite(Actualite a) {
        System.out.println(a.getId());
        
            String req3="update  actualite set titre=?,"
                   
                    +"text= ?"
                   
                  //  +"image=?"
                    + "where id=?"
                    ;
           
           try {
               prepste = connect.prepareStatement(req3);
            prepste.setString(1, a.getTitre());
            prepste.setString(2, a.getText());
             prepste.setInt(3, a.getId());
             prepste.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudActualite.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
  public ObservableList<Actualite> afficherActualite() {
         data = FXCollections.observableArrayList();
          try {
            ResultSet rs = connect.createStatement().executeQuery("select * from actualite");
            while (rs.next()) {
                data.add(new Actualite(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
          return data;
    }
  
   @Override
    public List<Actualite> reccuperer() {
        
        String query = "Select * from actualite";
        try {
            prepste = connect.prepareStatement(query);
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                int idact = rs.getInt("id");
                String titreact = rs.getString("titre");
                

               
                String txt = rs.getString("text");
                 String imagee = rs.getString("image");

                //Timestamp dateAnnonces = rs.getTimestamp("dataAjout");
              
                Actualite act = new Actualite(idact, titreact, txt, "");
              //  JeuxVideo jj = new JeuxVideo(nomj, genrej, "",desc, "","", "");
                
                list.add(act);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudActualite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
    }

   

