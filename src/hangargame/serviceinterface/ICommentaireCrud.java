/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.serviceinterface;

import hangargame.entites.CommentaireSujet;
import java.util.ArrayList;

/**
 *
 * @author Hamza
 */
public interface ICommentaireCrud {
    public void ajouterComentaire(CommentaireSujet c);
 public void supprimerCommentaire(CommentaireSujet c);
 public void afficherCommentaire();
   public ArrayList<CommentaireSujet> afficherCommentaire(String s);
    public void  updateEtat(String s);
    
}
