/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.entites;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Hamza
 */
public class EvaluationSujet {
    
    private int idEvalu;
    private String nomSujetEvaluer;
    private String emailClientEvaluer;
    private int Evaluation ;
    private Date dateEvaluation;
    
    public EvaluationSujet(){
        
    }
    
    public int getIdEvaluation (){
        
        return idEvalu;
    }
    
    public String getNOmSujetEval(){
        return nomSujetEvaluer;
    }
    
    public String getEmailClientEval(){
        return emailClientEvaluer;
    }
     public int getEvalu(){
      return Evaluation ;    
     }
     
     public Date getDateEvaluation(){
         return dateEvaluation;
     }
     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EvaluationSujet other = (EvaluationSujet) obj;
        
        if (!Objects.equals(this.nomSujetEvaluer, other.getNOmSujetEval())) {
            return false;
        }
         
        if (!Objects.equals(this.emailClientEvaluer, other.getEmailClientEval())) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.emailClientEvaluer);
        hash = 59 * hash + Objects.hashCode(this.nomSujetEvaluer);
        return hash ;
    }
}