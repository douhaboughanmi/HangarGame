/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.utils;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import hangargame.entites.Gamer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author lenovo
 */
public class AuthentificationFB {
    public Gamer AuthentificationFB() throws FileNotFoundException
    {
       String accessToken = "EAACEdEose0cBAHFeJbVISogv3iKxYZBGjjxrKoiVCyE5pDkeXCemjdAqfPeiMz62VDOkycLfJmDljzRWbuiWnvzPHYeZBF6hkw48PXnlwLlDi7sb4j5BQEHeUENCtcilM5JR8s5s6sAVCx1ugsb5KC0MKiM7ipH0Tylaswyoe5LAhSw5eXkt68S6jv55cZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        
        User me = fbClient.fetchObject("me", User.class);
        String mailFB = me.getEmail();
        String NomFB = me.getLastName();
        String PrenomFB = me.getFirstName();
        String passwordFB = "hangargame";
        String adresseFB = me.getHometownName();

        InputStream inputStream = new FileInputStream("src/hangargame/images/ImageAnonyme.jpg");

        String loginFB = me.getName();
        Gamer g = new Gamer(loginFB, NomFB, PrenomFB, adresseFB, mailFB, passwordFB, inputStream);
        return g;
        
       
    }
     
}
