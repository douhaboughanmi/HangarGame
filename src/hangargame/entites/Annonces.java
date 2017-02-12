/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.entites;

import java.io.InputStream;

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
    private InputStream inputStream;

    @Override
    public String toString() {
        return "Annonces{" + "nomAnnonces=" + nomAnnonces + ", typeAnnonces=" + typeAnnonces + ", consoleAnnonces=" + consoleAnnonces + ", descriptionAnnonces=" + descriptionAnnonces + ", prix=" + prix + ", inputStream=" + inputStream + '}';
    }
    

    public Annonces(String nomAnnonces,String typeAnnonces,String consoleAnnonces,String descriptionAnnonces, int prix ,InputStream inputStream) {
       
        this.nomAnnonces = nomAnnonces;
        this.typeAnnonces = typeAnnonces;
        this.consoleAnnonces = consoleAnnonces;
        this.descriptionAnnonces=descriptionAnnonces;
        this.prix = prix;
        this.inputStream=inputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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
