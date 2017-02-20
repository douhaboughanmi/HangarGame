/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.serviceinterface.ITournoiCrud;
import hangargame.entites.Tournoi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Louay
 */
public class TournoiCrud implements ITournoiCrud {

    private ObservableList<Tournoi> data;
    Connection connect;
    Statement ste;
    PreparedStatement prepste;
    ResultSet Rs;

    public TournoiCrud() {
        try {
            connect = ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(TournoiCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterTournoi(Tournoi e) {

        String req1 = "insert into tournoi (nom,nom_jeu,nbr_max,datedebut,datefin,id_gamer)values(?,?,?,?,?,?)";
        try {

            prepste = connect.prepareStatement(req1);
            prepste.setString(1, e.getNom());
            prepste.setString(2, e.getNom_jeu());
            prepste.setInt(3, e.getNbr_max());
            prepste.setDate(4, java.sql.Date.valueOf(e.getDatedebut()));
            prepste.setDate(5,java.sql.Date.valueOf(e.getDatefin()));
            prepste.setString(6, e.getId_gamer());
            prepste.executeUpdate();
            // System.out.println("Louaaayyy");
        } catch (SQLException ex) {
            Logger.getLogger(TournoiCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimerTournoi(int id) {

        try {
            String req2 = "delete from tournoi where id=?";

            prepste = connect.prepareStatement(req2);
            prepste.setInt(1, id);
            prepste.executeUpdate();
            System.out.println("Tournoi Supp");
        } catch (SQLException ex) {
            Logger.getLogger(Tournoi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierTournoi(Tournoi e) {

        String req3 = "UPDATE tournoi SET id=?"
                + "nom=?"
                + "nom_jeu=?"
                + "nbr_max=?"
                + "datedebut=?"
                + "datefin=?";
        try {
            ste.executeUpdate(req3);
        } catch (SQLException ex) {
            Logger.getLogger(Tournoi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Tournoi> afficherTournoi() {
        try {
            connect = ConnexionSingleton.getInstance();
            data = FXCollections.observableArrayList();
            ResultSet rs = connect.createStatement().executeQuery("select * from tournoi");
            while (rs.next()) {

                data.add(new Tournoi(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5).toLocalDate(), rs.getDate(6).toLocalDate(), rs.getString(7)));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
        return data;

    }

  

}
