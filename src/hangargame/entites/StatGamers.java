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
public class StatGamers {
    
    private int mois;
    private int nbrGamers;

    public StatGamers(int nbrGamers, int mois) {
        this.mois = mois;
        this.nbrGamers = nbrGamers;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getNbrGamers() {
        return nbrGamers;
    }

    public void setNbrGamers(int nbrGamers) {
        this.nbrGamers = nbrGamers;
    }
    
}
