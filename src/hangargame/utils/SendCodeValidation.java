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
public class SendCodeValidation {
    
     final String username = "hangargame.pidev@gmail.com";
		final String password = "92505082";
                
                Properties props = new Properties();
                
    public void send(String email, String code){
                
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
        
        
        message.setSubject("Activation du compte");
        
        message.setText("Merci d'avoir rejoint Hangar Game, la plate-forme des Gamers la plus divertissante au monde! \n Fais en sorte de vérifier ton adresse email afin de pouvoir restaurer ton mot de passe en copiant ce code dans le champ indiqué: " + code );

			Transport.send(message);

			System.out.println("Done");
        
        
        
    } catch (MessagingException ex) {
        Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
    }
                
                
                }
}
