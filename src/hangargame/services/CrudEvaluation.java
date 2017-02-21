/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Evaluation;
import hangargame.serviceinterface.IEvaluationCrud;
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
public class CrudEvaluation implements IEvaluationCrud {
     Connection connect;
    Statement ste ;
    PreparedStatement prepste;
    private ObservableList<Evaluation> data;
    public CrudEvaluation() {
        try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CrudEvaluation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


    @Override
    public void ajouterEvaluation(Evaluation e) {
        try {
            String req1="insert into evaluation_jeu (nom,email_client,nom_jeu,note)values"
                    + "(?,?,?,?)";
            
           prepste = connect.prepareStatement(req1);
           prepste.setString(1, e.getnom());
            prepste.setString(2, e.getEmail_client());

            prepste.setString(3, e.getNom_jeu());
            prepste.setInt(4, e.getNote());
             prepste.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudEvaluation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public void afficherEvaluation() {
      String req2= "select * from evaluation_jeu";
        try {
            ResultSet res  =ste.executeQuery(req2);
            while (res.next()) { 
                System.out.println("nom de l'evalutuion : "+res.getString("nom")+" "+
                        "email du client : "+res.getString("email_client")+" "+
                                   "nom jeu : "+ res.getString("nom_jeu")+" "+
                                    
                                   "Note :" +res.getInt("note"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudEvaluation.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
    
    @Override
    public ObservableList<Evaluation> getTopEvaluation() {

        try {
            Connection connect;
            connect = ConnexionSingleton.getInstance();
            data = FXCollections.observableArrayList();
            ResultSet rs = connect.createStatement().executeQuery("select * from evaluation_jeu ORDER BY note DESC limit 10 ");
            while (rs.next()) {
                data.add(new Evaluation(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
        return data;
    }
}

