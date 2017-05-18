/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.utils;

import java.io.UnsupportedEncodingException;
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
    

    public void send(String email, String code) {

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

          message.setText("Merci d'avoir rejoindre Hangar Game, la plate-forme des Gamers la plus divertissante au monde! \n Fais en sorte de vérifier ton adresse email afin de pouvoir restaurer ton mot de passe en copiant ce code dans le champ indiqué: " + code );
           // message.setText(body);
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public  boolean SendPers(String toAddress,String emailBody) throws UnsupportedEncodingException, MessagingException {
	 	final String password="92505082";
                final String from = "hangargame.pidev@gmail.com";
                final String name="Hangar Game";
                String subject="Activation du compte";
                
                Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		if(Session.getDefaultInstance(props)!=null){
			System.out.println("connected");
		}else{
			System.out.println("not connected");
		}
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {                                      
                                        @Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from, password);
					}
				  });
                
		
		try {
			Message message = new MimeMessage(session);	 
			message.setFrom(new InternetAddress(from));			
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(toAddress));
			message.setSubject(subject);
			message.setContent(emailBody,"text/html");
			InternetAddress fromAddress=new InternetAddress(from, name);
			message.setFrom(fromAddress);
			Transport.send(message);
			return true;
		} catch (MessagingException c) {
			return false;
		}



}
}
