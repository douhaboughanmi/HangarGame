/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.connexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lenovo
 */
public class ConnexionSingleton {
     String login="root";
   String pwd="";
   String url="jdbc:mysql://localhost/hangargame";
   static Connection instanceConnection;

    public ConnexionSingleton() {
        
         try {
            instanceConnection= DriverManager.getConnection(url,login, pwd);
            System.out.println("connexion reussie");
        } catch (SQLException ex) {
        }
    }
    
    public static Connection getInstance(){
        
        if(instanceConnection == null)
            new ConnexionSingleton();
        return instanceConnection;
    }
 }
