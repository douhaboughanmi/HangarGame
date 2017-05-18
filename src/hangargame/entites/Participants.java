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
      String nomTournoi;
      String statut ;

    public Participants() {
    }

   

    public String getNomTournoi() {
        return nomTournoi;
    }

    public void setNomTournoi(String nomTournoi) {
        this.nomTournoi = nomTournoi;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Participants(int id_tounoi, String Login, String nomTournoi, String statut) {
        this.id_tounoi = id_tounoi;
        this.Login = Login;
        this.nomTournoi = nomTournoi;
        this.statut = statut;
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
