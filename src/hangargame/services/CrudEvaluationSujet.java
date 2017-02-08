/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.EvaluationSujet;
import hangargame.serviceinterface.IEvaluSujetCrud;
import hangargame.services.CrudEvaluation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hamza
 */
public class CrudEvaluationSujet implements IEvaluSujetCrud {
     public Connection conn ;
  public Statement stm;
  public PreparedStatement prstm;
    
    public CrudEvaluationSujet(){
      
        conn = ConnexionSingleton.getInstance();
      try {
          stm =conn.createStatement();
      } catch (SQLException ex) {
          Logger.getLogger(CrudEvaluation.class.getName()).log(Level.SEVERE, null, ex);
      }
        
    }
    

    @Override
    public void afficherSelonEvaluation() {
        String req4= "select * from evaluation_sujet ";
        try {
            ResultSet res  =stm.executeQuery(req4);
            while (res.next()) { 
               String gamer= res.getString("gamer");
               int note = res.getInt("note");
               String sujet = res.getString("sujet");
               
            System.out.println(""+sujet+note+gamer+"");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterEvaluation(EvaluationSujet e) {
        try {
            String ajoutStatement = "INSERT INTO `evaluation_sujet`(`gamer`, `sujet`, `note`) "
                    + "VALUES(?,?,?)";

            prstm = conn.prepareStatement(ajoutStatement);
            prstm.setString(1, e.getEmailClientEval());
            prstm.setString(2, e.getNOmSujetEval());
            prstm.setInt(3, e.getEvalu());
           
            
            prstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
}
}
