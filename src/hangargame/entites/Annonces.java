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
    private int idAnnonces ;
    private String nomAnnonces ;
    private String typeAnnonces;
    private String consoleAnnonces;
    private String descriptionAnnonces;
    private int prix;
    private InputStream inputStream;
    private String gamer ;
    private String pathImage;

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public Annonces(int idAnnonces, String nomAnnonces, String typeAnnonces, String consoleAnnonces, String descriptionAnnonces, int prix, InputStream inputStream, String gamer, String pathImage) {
        this.idAnnonces = idAnnonces;
        this.nomAnnonces = nomAnnonces;
        this.typeAnnonces = typeAnnonces;
        this.consoleAnnonces = consoleAnnonces;
        this.descriptionAnnonces = descriptionAnnonces;
        this.prix = prix;
        this.inputStream = inputStream;
        this.gamer = gamer;
        this.pathImage = pathImage;
    }

    public String getGamer() {
        return gamer;
    }

    public void setGamer(String gamer) {
        this.gamer = gamer;
    }

    @Override
    public String toString() {
        return "Annonces{" + "nomAnnonces=" + nomAnnonces + ", typeAnnonces=" + typeAnnonces + ", consoleAnnonces=" + consoleAnnonces + ", descriptionAnnonces=" + descriptionAnnonces + ", prix=" + prix + ", inputStream=" + inputStream + '}';
    }

    public Annonces(int idAnnonces, String nomAnnonces, String typeAnnonces, String consoleAnnonces, String descriptionAnnonces, int prix, InputStream inputStream) {
        this.idAnnonces = idAnnonces;
        this.nomAnnonces = nomAnnonces;
        this.typeAnnonces = typeAnnonces;
        this.consoleAnnonces = consoleAnnonces;
        this.descriptionAnnonces = descriptionAnnonces;
        this.prix = prix;
        this.inputStream = inputStream;
    }

    public Annonces(int idAnnonces, String nomAnnonces, String typeAnnonces, String consoleAnnonces, String descriptionAnnonces, int prix, InputStream inputStream, String gamer) {
        this.idAnnonces = idAnnonces;
        this.nomAnnonces = nomAnnonces;
        this.typeAnnonces = typeAnnonces;
        this.consoleAnnonces = consoleAnnonces;
        this.descriptionAnnonces = descriptionAnnonces;
        this.prix = prix;
        this.inputStream = inputStream;
        this.gamer = gamer;
    }
    
    

    public int getIdAnnonces() {
        return idAnnonces;
    }
    

    public Annonces(String nomAnnonces,String typeAnnonces,String consoleAnnonces,String descriptionAnnonces, int prix ,InputStream inputStream ,String path) {
       
        this.nomAnnonces = nomAnnonces;
        this.typeAnnonces = typeAnnonces;
        this.consoleAnnonces = consoleAnnonces;
        this.descriptionAnnonces=descriptionAnnonces;
        this.prix = prix;
        this.inputStream=inputStream;
        this.pathImage=path;
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
