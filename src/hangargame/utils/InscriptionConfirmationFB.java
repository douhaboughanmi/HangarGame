/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hangargame.utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author lenovo
 */
public class InscriptionConfirmationFB {
    
     final String username = "hangargame.pidev@gmail.com";
		final String password = "92505082";
                
                Properties props = new Properties();
                
    public void send(String login, String motPass, String email){
                
                props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
                
                
                Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
                
                Message message = new MimeMessage(session);
                
    try {
        message.setFrom(new InternetAddress("hangargame.pidev@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
        
        
        message.setSubject("Inscription réussite");
        
        message.setText("Bonjour "+login+"\n "
                + "Merci d'avoir rejoindre Hangar Game, la plate-forme des Gamers la plus divertissante au monde! \n"
                + " Votre mot de passe attribué par défaut est:  "+motPass+", vous pouvez le changer en cliquant sur 'Modifier mon mot de passe'  " );

			Transport.send(message);

			System.out.println("Done");
        
        
        
    } catch (MessagingException ex) {
        Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
    }
                
                
                }
}
