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
public class Annonces {
    
    private String nomAnnonces ;
    private String typeAnnonces;
    private String consoleAnnonces;
    private String descriptionAnnonces;
    private int prix;
    

    public Annonces(String nomAnnonces,String typeAnnonces,String consoleAnnonces,String descriptionAnnonces, int prix) {
       
        this.nomAnnonces = nomAnnonces;
        this.typeAnnonces = typeAnnonces;
        this.consoleAnnonces = consoleAnnonces;
        this.descriptionAnnonces=descriptionAnnonces;
        this.prix = prix;
    }

    public String getNomAnnonces() {
        return nomAnnonces;
    }

    public void setNomAnnonces(String nomAnnonces) {
        this.nomAnnonces = nomAnnonces;
    }

    public String getTypeAnnonces() {
        return typeAnnonces;
    }

    public void setTypeAnnonces(String typeAnnonces) {
        this.typeAnnonces = typeAnnonces;
    }

    public String getConsoleAnnonces() {
        return consoleAnnonces;
    }

    public void setConsoleAnnonces(String consoleAnnonces) {
        this.consoleAnnonces = consoleAnnonces;
    }

    public String getDescriptionAnnonces() {
        return descriptionAnnonces;
    }

    public void setDescriptionAnnonces(String descriptionAnnonces) {
        this.descriptionAnnonces = descriptionAnnonces;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Annonces() {
    }
  

    

   
    
}
