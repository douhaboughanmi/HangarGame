/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.serviceinterface;

import hangargame.entites.Participants;
import javafx.collections.ObservableList;

/**
 *
 * @author Louay
 */
public interface IParticipantCrud {
    public  String ajouterParticipants(Participants e);
    public ObservableList<String> afficherParticipants(int id);
    public int recupererParticipant(int id);
}
