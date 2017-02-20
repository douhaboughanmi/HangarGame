/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.entites;

/**
 *
 * @author mayss
 */
public class Signalisation {
  int  idSignalisation ;
  int idObjet;
  String typeSignalisation ;
  String GamerSignale;
  int nbrSignalisation;

    public Signalisation(int idSignalisation, int idObjet, String typeSignalisation, String GamerSignale, int nbrSignalisation) {
        this.idSignalisation = idSignalisation;
        this.idObjet = idObjet;
        this.typeSignalisation = typeSignalisation;
        this.GamerSignale = GamerSignale;
        this.nbrSignalisation = nbrSignalisation;
    }

    public int getIdSignalisation() {
        return idSignalisation;
    }

    public void setIdSignalisation(int idSignalisation) {
        this.idSignalisation = idSignalisation;
    }

    public int getIdObjet() {
        return idObjet;
    }

    public void setIdObjet(int idObjet) {
        this.idObjet = idObjet;
    }

    public String getTypeSignalisation() {
        return typeSignalisation;
    }

    public void setTypeSignalisation(String typeSignalisation) {
        this.typeSignalisation = typeSignalisation;
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
  
  
  
}
