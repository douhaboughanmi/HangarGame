/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.entites;

/**
 *
 * @author lenovo
 */
public class Signalisation {
    
    int id ;
    int idObjet ;
    String type;//Annonce ou Sujet

    public Signalisation(int id, int idObjet, String type, String GamerSignale, int nbrSignalisation) {
        this.id = id;
        this.idObjet = idObjet;
        this.type = type;
        this.GamerSignale = GamerSignale;
        this.nbrSignalisation = nbrSignalisation;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdObjet() {
        return idObjet;
    }

    public void setIdObjet(int idObjet) {
        this.idObjet = idObjet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGamerSignale() {
        return GamerSignale;
    }

    public void setGamerSignale(String GamerSignale) {
        this.GamerSignale = GamerSignale;
    }

    public int getNbrSignalisation() {
        return nbrSignalisation;
    }

    public void setNbrSignalisation(int nbrSignalisation) {
        this.nbrSignalisation = nbrSignalisation;
    }
    String GamerSignale;
    int nbrSignalisation;
    
}
