/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.serviceinterface;

import hangargame.entites.EvaluationSujet;

/**
 *
 * @author Hamza
 */
public interface IEvaluSujetCrud {
    public void ajouterEvaluation(String gamer,String sujet,int note);
    public void afficherSelonEvaluation();
    public int verifierGamer(String gamer);
    public void UpdateEvaluation(String gamer);
}
