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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author mayss
 */
public class SendMail {
    
   
    final String username = "mohamedhalim.jerbi@esprit.tn";
		final String password = "22885959";
                
                Properties props = new Properties();
                
                public void send(){
                
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
        message.setFrom(new InternetAddress("mohamedhalim.jerbi@esprit.tn"));
        message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("louay.dhyeb@esprit.tn"));
        
        message.setSubject("PI-DEV");
        
        message.setText("Bonjour si vous lisiez ce message c'est que le mail api a fonctionner parfaitement");

			Transport.send(message);

			System.out.println("Done");
        
        
        
    } catch (MessagingException ex) {
        Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
    }
                
                
                }
                
                 public void sendForum(String contenue){
                
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
        message.setFrom(new InternetAddress("mohamedhalim.jerbi@esprit.tn"));
        message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("louay.dhyeb@esprit.tn"));
        
        message.setSubject("PI-DEV");
        
        message.setText(contenue);

			Transport.send(message);

			System.out.println("Done");
        
        
        
    } catch (MessagingException ex) {
        Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
    }
                
                
                }
    
    
    
    
    
    
}
