/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.CommentaireSujet;
import hangargame.serviceinterface.ICommentaireCrud;
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
public class CrudCommentaireForum implements ICommentaireCrud {
     Connection connect ;
    Statement stmt;
    PreparedStatement pstmnt;
    public CrudCommentaireForum(){
       connect =ConnexionSingleton.getInstance();
        try {
            stmt = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CrudCommentaireForum.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 @Override
    public void ajouterComentaire(CommentaireSujet c) {
        try {
            String ajoutStatement = "INSERT INTO `commentaire_forum`(`textcomm`,`etat`, `sujet`) "
                    + "VALUES(?,?,?)";

            pstmnt = connect.prepareStatement(ajoutStatement);
            pstmnt.setString(1, c.gettextCommnt());
            pstmnt.setString(3, c.getsujet());
            pstmnt.setInt(2, c.getetatCmnt());
            
            pstmnt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimerCommentaire(CommentaireSujet c) {
try {
            String supprimerstatment = "delete from commentaire_forum where etat=?";

            pstmnt = connect.prepareStatement(supprimerstatment);
            pstmnt.setInt(1, c.getetatCmnt());
            pstmnt.executeUpdate();

            
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Override
    public void afficherCommentaire() {
         String req4= "select * from commentaire_forum";
        try {
            ResultSet res  =stmt.executeQuery(req4);
            while (res.next()) { 
               String contcomm= res.getString("textcomm");
               Date date = res.getDate("datecomm");
               
               
            System.out.println(""+contcomm+date+"");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }}
}
