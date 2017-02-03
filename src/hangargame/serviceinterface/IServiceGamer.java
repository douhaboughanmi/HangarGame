/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.serviceinterface;

import hangargame.entites.Gamer;

/**
 *
 * @author lenovo
 */
public interface IServiceGamer {
    public void Inscription(Gamer g);
    public boolean Authentification(String login, String password);
    
}
