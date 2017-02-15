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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
    
    List<Gamer> list = new ArrayList<>();

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
    public boolean Inscription(String mail, String login, String password, String passwordConf, String nom, String prenom, String adresse, String tel, String image) {

        String req = "Insert into Gamer(login,nom,prenom,adresse,tel,email,password,dateInscription,codeValidation,LastModifMdp,validation,image,role) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            InputStream inputStream= new FileInputStream(image);

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
                prepste.setBlob(12, inputStream);
                prepste.setString(13,"gamer");
                prepste.executeUpdate();
                SendCodeValidation sendMail = new SendCodeValidation();
                sendMail.send(mail, code);
                return true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Gamer.class.getName()).log(Level.SEVERE, null, ex);
        }catch (FileNotFoundException ex) {
            Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean Authentification(String login, String password) {
     //   System.out.println(login);
       // System.out.println(password);
       String role="gamer";
        String req4 = "select * from Gamer where login = '" + login + "'and password = '" + password +"'and role = '" + role +       "'";
        try {
            ResultSet res = ste.executeQuery(req4);
            while (res.next()) {

                 //String resultLogin = res.getString(13);
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
    public boolean ValidationCode(String login, String codeValidation) {

        String req1 = "select codeValidation from gamer where login= '" + login + "'";
        String req = " update gamer set validation = '1' where login= '" + login + "'and codeValidation = '" + codeValidation + "'";
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
                sendMail.send(email, passwordL);
                return passwordL;

            }
            resultat.close();

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    @Override
    public boolean ActivationCompte(String login) {
        String req = "select validation from gamer where login= '" + login + "'";
        try {

            prepste = connect.prepareStatement(req);
            ResultSet resultat = prepste.executeQuery();

            if (resultat.next()) {

                int resultValidation = resultat.getInt(1);

                if (resultValidation == 0) {
                    return false;
                } else if (resultValidation == 1) {
                    return true;
                }

            }

            resultat.close();

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    @Override
    public boolean ActivationCompteFB(String email) {
        String req = "select validation from gamer where email= '" + email + "'";
        try {

            prepste = connect.prepareStatement(req);
            ResultSet resultat = prepste.executeQuery();

            if (resultat.next()) {

                int resultValidation = resultat.getInt(1);

                if (resultValidation == 0) {
                    return false;
                } else if (resultValidation == 1) {
                    return true;
                }

            }

            resultat.close();

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

  

    @Override
    public Gamer ModifierInfo(String nom, String prenom, String adresse, int tel, String login,InputStream input) {
        String req = "update gamer set nom=?, prenom=?, adresse=?, tel=? , image=? where login='" + login + "'";
        try {
             
            prepste = connect.prepareStatement(req);
            prepste.setString(1, nom);
            prepste.setString(2, prenom);
            prepste.setString(3, adresse);
            prepste.setInt(4, tel);
            prepste.setBlob(5, input);
            prepste.executeUpdate();
            Gamer g = new Gamer(nom, prenom, adresse, tel,input);
            return g;
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
return null;
    }

    @Override
    public Gamer Afficher(String login) {
        String req = "select nom,prenom,adresse,tel,email,dateInscription,image from gamer where login= '" + login + "'";
        try {

            prepste = connect.prepareStatement(req);
            ResultSet resultat = prepste.executeQuery();

            if (resultat.next()) {
                String resultNom = resultat.getString(1);
                String resultPrenom = resultat.getString(2);
                String resultAdresse = resultat.getString(3);
                int resultTel = resultat.getInt(4);
                String resultEmail = resultat.getString(5);
                Timestamp resultDateInscri = resultat.getTimestamp(6);
                InputStream image = resultat.getBlob(7).getBinaryStream();
                
                Gamer g = new Gamer(resultNom, resultPrenom, resultAdresse, resultTel, resultEmail, resultDateInscri,image);
                return g;
            }
            resultat.close();

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    @Override
    public boolean ChangePassword(String password1, String password2, String login) {
        String req = "update gamer set password=? where login='" + login + "'";
         try {
             if (password1.equals(password2)) {
                prepste = connect.prepareStatement(req);

                prepste.setString(1,password1);
                prepste.executeUpdate();
              
                return true;
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
    }

    @Override
    public boolean AuthentificationAdmin(String login, String password) {
        String role="admin";
        String req4 = "select * from Gamer where login = '" + login + "'and password = '" + password +"'and role = '" + role +       "'";

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
    public List<Gamer>AfficherListeGamer() {
        String role="gamer";
      String query = "Select * from gamer where role ='" + role +   "'";
    
        try {
            prepste = connect.prepareStatement(query);
            ResultSet rs = prepste.executeQuery();
            while(rs.next()){
            String loginG =rs.getString("login");
            String nomG = rs.getString("nom");
             String prenomG = rs.getString("prenom");
            String emailG= rs.getString("email");
            String adresseG= rs.getString("adresse");
            int telG= rs.getInt("tel");
            Timestamp dateInsriG = rs.getTimestamp("dateInscription");
            Blob imageG=rs.getBlob("image");
            int compte= rs.getInt("etat");
            
     
            Gamer gamers = new Gamer(loginG, nomG, prenomG, adresseG, telG, emailG, dateInsriG,imageG.getBinaryStream(),compte);
                
                   list.add(gamers);
              
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
    }

   

    @Override
    public boolean BloquerGamer(String login) {
       String req = "update gamer set etat=? where login='" + login + "'";
        try {
             
            prepste = connect.prepareStatement(req);
           
            prepste.setInt(1, 1);
            prepste.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean InscriptionFB(Gamer g) {
        String req = "Insert into Gamer(login,nom,prenom,adresse,email,password,dateInscription,LastModifMdp,validation,image,role) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        
         try {
             
               InputStream inputStream = g.getImage();
                prepste = connect.prepareStatement(req);

                
                int validation = 1;

                prepste.setString(1, g.getLogin());
                prepste.setString(2, g.getNom());
                prepste.setString(3, g.getPrenom());
                prepste.setString(4, g.getAdresse());
                prepste.setString(5, g.getEmail());
                prepste.setString(6, g.getPassword());
                prepste.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
               prepste.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
                prepste.setInt(9, validation);
                prepste.setBlob(10, inputStream );
                prepste.setString(11,"gamer");
                prepste.executeUpdate();
               
                return true;

            
        } catch (SQLException ex) {
            Logger.getLogger(Gamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

  
}
