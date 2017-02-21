/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.StatGamers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.remote.JMXConnectorFactory.connect;

/**
 *
 * @author lenovo
 */
public class ServicesStatGamers {
    
     Connection connect;
    Statement ste;
    PreparedStatement prepste;
    
    public ServicesStatGamers()
    {
          try {
            connect = ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<StatGamers> chart() {
        ArrayList<StatGamers> lstrr = new ArrayList<>();
        for (int j = 1; j < 32; j++) {
            lstrr.add(new StatGamers(0, j));
        }
        String req = "select DAY(`dateInscription`) as mois,COUNT(*) as nombre FROM `gamer` group by DAY(`dateInscription`)";
        try {
              prepste = connect.prepareStatement(req);
              ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                for (StatGamers s : lstrr) {
                    if (s.getMois() == rs.getInt("mois")) {
                        s.setNbrGamers(rs.getInt("nombre"));
                    }
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicesStatGamers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lstrr;
        
}
}