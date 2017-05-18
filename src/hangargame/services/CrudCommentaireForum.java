/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.CommentaireSujet;
import hangargame.entites.Sujet;
import hangargame.serviceinterface.ICommentaireCrud;
import java.sql.Connection;
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

    Connection connect;
    Statement stmt;
    PreparedStatement pstmnt;

    public CrudCommentaireForum() {
        connect = ConnexionSingleton.getInstance();
        try {
            stmt = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CrudCommentaireForum.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterComentaire(CommentaireSujet c,int s) {
        try {
            String ajoutStatement = "INSERT INTO `forum_commentaire`(`contenue`, `sujet`) "
                    + "VALUES(?,?)";

            pstmnt = connect.prepareStatement(ajoutStatement);
            pstmnt.setString(1, c.gettextCommnt());
            pstmnt.setInt(2,s);

            pstmnt.executeUpdate();
            
            System.out.println("sujet  ate ajouyer");
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
        String req = "SELECT id,contenue,date,Username from forum_commentaire where sujet =(select id from forum_sujet where titre = ?) ORDER by `date` DESC";

        try {

            pstmnt = connect.prepareStatement(req);
            pstmnt.setString(1, s);
            ResultSet rs = pstmnt.executeQuery();
            while (rs.next()) {
                String textComm = rs.getString("contenue");
                Timestamp dateComm = rs.getTimestamp("date");
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
    public void updateEtat(String s) {

        try {
            String supprimerstatment = "UPDATE `forum_signalisation` SET `nb_signalisation` =`nb_signalisation`+1 WHERE `sujet`=(select id from forum_sujet where titre = ?) ";

            pstmnt = connect.prepareStatement(supprimerstatment);
            pstmnt.setString(1, s);
            pstmnt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CrudSujet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int recupId(String s) {
        String req = "select id from forum_sujet where titre = ?";
        int id = 0;
        try {

            pstmnt = connect.prepareStatement(req);
            pstmnt.setString(1, s);
            ResultSet rs = pstmnt.executeQuery();
            while (rs.next()) {

                 id = rs.getInt("id");
                System.out.println(id);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;

    }

    @Override
    public void afficherCommentaire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
