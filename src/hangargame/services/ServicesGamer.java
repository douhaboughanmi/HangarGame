/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Gamer;
import hangargame.serviceinterface.IServiceGamer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author lenovo
 */
public class ServicesGamer implements IServiceGamer {

    Connection connect;
    Statement ste;
    PreparedStatement prepste;

    public ServicesGamer() {
        try {
            connect = ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean Inscription(String mail, String login, String password, String passwordConf, String nom, String prenom, String adresse, String tel) {

      
        String req = "Insert into Gamer(login,nom,prenom,adresse,tel,email,password,dateInscription,codeValidation,LastModifMdp) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try {
         
                if (password.equals(passwordConf)) {
                    prepste = connect.prepareStatement(req);
                    Random rand = new Random();
                    int codeValid = rand.nextInt(1001);
                    int telephone = Integer.parseInt(tel);

                    prepste.setString(1, login);
                    prepste.setString(2, nom);
                    prepste.setString(3, prenom);
                    prepste.setString(4, adresse);
                    prepste.setInt(5, telephone);
                    prepste.setString(6, mail);

                    prepste.setString(7, password);
                    prepste.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
                    prepste.setInt(9,codeValid);
                    prepste.setTimestamp(10, new Timestamp(System.currentTimeMillis()));

                    prepste.executeUpdate();

                    return true;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean Authentification(String login, String password) {
        System.out.println(login);
        System.out.println(password);
        String req4 = "select * from Gamer where login = '" + login + "'and password = '" + password + "'";
        try {
            ResultSet res = ste.executeQuery(req4);
            while (res.next()) {

                return true;

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean VerifMail(String email) {
        String req2 = "select * from Gamer where email= '" + email + "'";

        try {
            prepste = connect.prepareStatement(req2);
            prepste.executeQuery();
            int x = prepste.getMaxRows();
            if (x != 0) {

                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public boolean VerifLogin(String login) {
        String req2 = "select * from Gamer where login= '" + login + "'";

        try {
            prepste = connect.prepareStatement(req2);
            prepste.executeQuery();
            int x = prepste.getMaxRows();
            if (x != 0) {

                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }
}
