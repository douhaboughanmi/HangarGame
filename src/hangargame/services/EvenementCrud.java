/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.serviceinterface.IEvenementCrud;
import hangargame.entites.Evenement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import hangargame.connexionDB.ConnexionSingleton;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.runtime.regexp.joni.Syntax;

/**
 *
 * @author Louay
 */
public class EvenementCrud implements IEvenementCrud {

    Connection connect;
    Statement ste;
    PreparedStatement prepste;
    private ObservableList<Evenement> data;

    public EvenementCrud() {
        try {
            connect = ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterEvenement(Evenement e) {
        String req1 = "insert into evenement (id,nom,description,adresse,datedebut,datefin)values(?,?,?,?,?,?)";
        try {

            prepste = connect.prepareStatement(req1);
            prepste.setInt(1, e.getId());
            prepste.setString(2, e.getNom());
            prepste.setString(4, e.getDescription());
            prepste.setString(3, e.getAdresse());
            prepste.setDate(5, java.sql.Date.valueOf(e.getDatedebut()));
            prepste.setDate(6, java.sql.Date.valueOf(e.getDatefin()));

            prepste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerEvenement(int id) {
        try {
            Evenement e = new Evenement();
            String req2 = "delete from evenement where id=?";

            prepste = connect.prepareStatement(req2);
            prepste.setInt(1, id);
            prepste.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierEvenement(Evenement e) {
        String req1 = "UPDATE evenement SET nom=?, description=?,adresse=?"
                + ",datedebut=?,datefin=?"
                + " WHERE id = ?";

        try {

            prepste = connect.prepareStatement(req1);
            prepste.setString(1,e.getNom());
            prepste.setString(2,e.getDescription());
            prepste.setString(3,e.getAdresse());
            prepste.setDate(4,java.sql.Date.valueOf(e.getDatedebut()));
            prepste.setDate(5,java.sql.Date.valueOf(e.getDatefin()));
            prepste.setInt(6,e.getId());
            prepste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Evenement> afficherEvenement() {

        try {
            Connection connect;
            connect = ConnexionSingleton.getInstance();
            data = FXCollections.observableArrayList();
            ResultSet rs = connect.createStatement().executeQuery("select * from evenement");
            while (rs.next()) {
                data.add(new Evenement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), rs.getDate(6).toLocalDate()));
            }
        } catch (SQLException ex) {
            System.err.println("Erreur" + ex);
        }
        return data;
    }

}
