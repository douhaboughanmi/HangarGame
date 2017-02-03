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

/**
 *
 * @author mishka
 */
public class CrudEvaluation implements IEvaluationCrud {
     Connection connect;
    Statement ste ;
    PreparedStatement prepste;
    public CrudEvaluation() {
        try {
           connect=  ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


    @Override
    public void ajouterEvaluation(Evaluation e) {
        try {
            String req1="insert into evaluation (nom,email_client,nom_jeu,note)values"
                    + "("+e.getnom()+",'"+e.getEmail_client()+",' "+e.getNom_client()+","+e.getNote()+"')";
            
           
             ste.executeUpdate(req1);
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CrudConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
}

