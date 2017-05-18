/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.serviceinterface;

import hangargame.entites.Annonces;
import java.util.List;

/**
 *
 * @author mayss
 */
public interface IAnnonce {
    
  
    public void ajouterAnnonces(Annonces a);

    public void modifierAnnonces(Annonces a) ;

    public void supprimerAnnonces(Annonces a);

    public List<Annonces> reccupererSimple() ;

    public List<Annonces> reccupererSimple2() ;
       

    public List<Annonces> reccupererSelonEchange() ;

    public List<Annonces> reccupererSelonVente();

    public List<Annonces> reccupererSelonPC();

    public List<Annonces> reccupererSelonPS4() ;

    public List<Annonces> reccupererSelonPS3();


    public List<Annonces> reccupererSelonXbox360();

    public List<Annonces> reccupererSelonXboxOne() ;

    public List<Annonces> reccupererSelonPSVita() ;

    public Annonces reccupererAnnonce() ;

    public Annonces reccupererAnnonce2() ;
}
