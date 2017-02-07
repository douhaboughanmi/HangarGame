/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Gamer;
import hangargame.serviceinterface.IServiceGamer;
import hangargame.utils.SendCodeValidation;
import hangargame.utils.SendPassword;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String req = "Insert into Gamer(login,nom,prenom,adresse,tel,email,password,dateInscription,codeValidation,LastModifMdp,validation) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try {

            if (password.equals(passwordConf)) {
                prepste = connect.prepareStatement(req);

                Random rand = new Random();
                int codeValid = rand.nextInt(100001);
                String code = Integer.toString(codeValid);

                int telephone = Integer.parseInt(tel);
                int validation = 0;

                prepste.setString(1, login);
                prepste.setString(2, nom);
                prepste.setString(3, prenom);
                prepste.setString(4, adresse);
                prepste.setInt(5, telephone);
                prepste.setString(6, mail);

                prepste.setString(7, password);
                prepste.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
                prepste.setString(9, code);
                prepste.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
                prepste.setInt(11, validation);
                prepste.executeUpdate();
                SendCodeValidation sendMail = new SendCodeValidation();
                sendMail.send(mail, code);
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
            ResultSet resultat = prepste.executeQuery();

            if (resultat.next()) {
                String resultEmail = resultat.getString(6);
                if (resultEmail.equals(email)) { //mail existant
                  return false;
                }
                resultat.close();

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return true;
       
    }

    @Override
    public boolean VerifLogin(String login) {
        String req2 = "select login from Gamer ";

          try {

            prepste = connect.prepareStatement(req2);
            ResultSet resultat = prepste.executeQuery();

            if (resultat.next()) {
                String resultLogin = resultat.getString(1);
                if (resultLogin.equals(login)) { //login existant
                  return false;
                }
                resultat.close();

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return true;
    }

    @Override
    public boolean EmailValidation(String email) {
        boolean status = false;
        String email_Pattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(email_Pattern);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    @Override
    public boolean ValidationCode(String email, String codeValidation) {

        String req1 = "select codeValidation from gamer where email= '" + email + "'";
        String req = " update gamer set validation = '1' where email= '" + email + "'and codeValidation = '" + codeValidation + "'";
        try {

            prepste = connect.prepareStatement(req1);
            ResultSet resultat = prepste.executeQuery();

            if (resultat.next()) {
                String code = resultat.getString(1);
                if (code.equals(codeValidation)) {
                    prepste = connect.prepareStatement(req);
                    prepste.executeUpdate();
                    return true;
                }
                resultat.close();

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    @Override
    public String RecupererPassword(String email) {
        String req = "select password from gamer where email='" + email + "'";
        try {

            prepste = connect.prepareStatement(req);
            ResultSet resultat = prepste.executeQuery();

            if (resultat.next()) {
                String passwordL = resultat.getString(1);
                SendPassword sendMail = new SendPassword();
                sendMail.send(email,passwordL);
                return passwordL;

            }
            resultat.close();

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

}
