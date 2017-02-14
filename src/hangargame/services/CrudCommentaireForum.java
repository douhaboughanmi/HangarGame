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
import java.sql.Timestamp;
import java.util.ArrayList;
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
            String ajoutStatement = "INSERT INTO `commentaire_forum`(`textcomm`, `sujet`) "
                    + "VALUES(?,?)";

            pstmnt = connect.prepareStatement(ajoutStatement);
            pstmnt.setString(1, c.gettextCommnt());
            pstmnt.setString(2, c.getsujet());
            
            
            pstmnt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimerCommentaire(CommentaireSujet c) {
try {
            String supprimerstatment = "delete from commentaire_forum where etat=5";

            pstmnt = connect.prepareStatement(supprimerstatment);
            pstmnt.setInt(1, c.getetatCmnt());
            pstmnt.executeUpdate();

            
        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Override
    public ArrayList<CommentaireSujet> afficherCommentaire(String s) {
        ArrayList<CommentaireSujet> ls = new ArrayList<>();
        String req = "SELECT `textcomm`,`datecomm` from commentaire_forum where `sujet`=?";

        try {

            pstmnt = connect.prepareStatement(req);
            pstmnt.setString(1, s);
            ResultSet rs = pstmnt.executeQuery();
            while (rs.next()) {
                String textComm = rs.getString("textcomm");
                Timestamp dateComm = rs.getTimestamp("datecomm");
                CommentaireSujet cm = new CommentaireSujet(textComm, dateComm);
                ls.add(cm);
                System.out.println(textComm);
                System.out.println(dateComm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;

    }
       @Override
    public void  updateEtat(String s) {
     
       
        try {
            String supprimerstatment = "UPDATE `sujet_forum` SET `etatsujet` =`etatsujet`+1 WHERE `nomsujet`=?";

            pstmnt = connect.prepareStatement(supprimerstatment);
            pstmnt.setString(1,s);
            pstmnt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void afficherCommentaire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
