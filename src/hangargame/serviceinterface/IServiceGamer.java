/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.serviceinterface;

import hangargame.entites.Gamer;

/**
 *
 * @author lenovo
 */
public interface IServiceGamer {
    public boolean Inscription(String mail, String login , String password , String passwordConf, String nom,String prenom,String adresse, String tel);
    public boolean Authentification(String login, String password);
    public boolean VerifMail(String email);
    public boolean VerifLogin(String login);
    public boolean EmailValidation(String email);
    public boolean ValidationCode(String login, String codeValidation  );
    public String RecupererPassword(String email);
    public boolean ActivationCompte(String login);
    public boolean AuthentificationWithFb(String email);
     public boolean ActivationCompteFB(String email);
     public Gamer ModifierInfo(String nom, String prenom, String adresse,int tel, String login);
     public Gamer Afficher(String login);
}
