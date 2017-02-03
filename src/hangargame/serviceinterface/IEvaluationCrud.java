/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.serviceinterface;

import hangargame.entites.Evaluation;

/**
 *
 * @author mishka
 */
public interface IEvaluationCrud {
    public void ajouterEvaluation(Evaluation e);
   
    public void afficherEvaluation();
}
