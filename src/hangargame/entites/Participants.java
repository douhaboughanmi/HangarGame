/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.entites;

/**
 *
 * @author Louay
 */
public class Participants {
    
    int id_tounoi;
      String Login ;

    public Participants() {
    }

    public Participants(int id_tounoi, String Login) {
        this.id_tounoi = id_tounoi;
        this.Login = Login;
    }

    public int getId_tounoi() {
        return id_tounoi;
    }

    public void setId_tounoi(int id_tounoi) {
        this.id_tounoi = id_tounoi;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }
  
    
    
}
