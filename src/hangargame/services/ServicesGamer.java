/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangargame.services;

import hangargame.connexionDB.ConnexionSingleton;
import hangargame.entites.Gamer;
import hangargame.serviceinterface.IServiceGamer;
import hangargame.utils.AuthentificationFB;
import hangargame.utils.InscriptionConfirmationFB;
import hangargame.utils.SendCodeValidation;
import hangargame.utils.SendMessage;
import hangargame.utils.SendPassword;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.MessagingException;

/**
 *
 * @author lenovo
 */
public class ServicesGamer implements IServiceGamer {

    List<Gamer> list = new ArrayList<>();

    Connection connect;
    Statement ste;
    PreparedStatement prepste;

    public ServicesGamer() {
        try {
            connect = ConnexionSingleton.getInstance();

            ste = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean Inscription(String mail, String login, String password, String passwordConf, String nom, String prenom, String adresse, String tel, String image) {
      

                String req = "Insert into Gamer(username,nom,prenom,adresse,tel,email,password,dateInscription,codeValidation,LastModifMdp,validation,image,roles,username_canonical,email_canonical,enabled) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                try {
                    InputStream inputStream = new FileInputStream(image);

                    if (password.equals(passwordConf)) {
                        prepste = connect.prepareStatement(req);

                        Random rand = new Random();
                        int codeValid = rand.nextInt(100001);
                        String code = Integer.toString(codeValid);

                        int telephone = Integer.parseInt(tel);
                        int validation = 0;

                        prepste.setString(1, login);
                        prepste.setString(2, nom);
                        prepste.setString(3, prenom);
                        prepste.setString(4, adresse);
                        prepste.setInt(5, telephone);
                        prepste.setString(6, mail);
                        prepste.setString(7, password);
                        prepste.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
                        prepste.setString(9, code);
                        prepste.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
                        prepste.setInt(11, validation);
                        prepste.setBlob(12, inputStream);
                        prepste.setString(13, "a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
                        prepste.setString(14, login);
                        prepste.setString(15, mail);
                        prepste.setInt(16, 1);
                        prepste.executeUpdate();
                        
                        SendMessage mes = new SendMessage();
                        mes.sendMessageCode(tel, code);
                       
                        SendCodeValidation sendMail = new SendCodeValidation();
                        
                        String body = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
            + "\n"
            + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
            + "<head>\n"
            + "	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
            + "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n"
            + "	<title>[SUBJECT]</title>\n"
            + "	<style type=\"text/css\">\n"
            + "\n"
            + "@media screen and (max-width: 600px) {\n"
            + "    table[class=\"container\"] {\n"
            + "        width: 95% !important;\n"
            + "    }\n"
            + "}\n"
            + "\n"
            + "	#outlook a {padding:0;}\n"
            + "		body{width:100% !important; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0;}\n"
            + "		.ExternalClass {width:100%;}\n"
            + "		.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height: 100%;}\n"
            + "		#backgroundTable {margin:0; padding:0; width:100% !important; line-height: 100% !important;}\n"
            + "		img {outline:none; text-decoration:none; -ms-interpolation-mode: bicubic;}\n"
            + "		a img {border:none;}\n"
            + "		.image_fix {display:block;}\n"
            + "		p {margin: 1em 0;}\n"
            + "		h1, h2, h3, h4, h5, h6 {color: black !important;}\n"
            + "\n"
            + "		h1 a, h2 a, h3 a, h4 a, h5 a, h6 a {color: blue !important;}\n"
            + "\n"
            + "		h1 a:active, h2 a:active,  h3 a:active, h4 a:active, h5 a:active, h6 a:active {\n"
            + "			color: red !important; \n"
            + "		 }\n"
            + "\n"
            + "		h1 a:visited, h2 a:visited,  h3 a:visited, h4 a:visited, h5 a:visited, h6 a:visited {\n"
            + "			color: purple !important; \n"
            + "		}\n"
            + "\n"
            + "		table td {border-collapse: collapse;}\n"
            + "\n"
            + "		table { border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; }\n"
            + "\n"
            + "		a {color: #000;}\n"
            + "\n"
            + "		@media only screen and (max-device-width: 480px) {\n"
            + "\n"
            + "			a[href^=\"tel\"], a[href^=\"sms\"] {\n"
            + "						text-decoration: none;\n"
            + "						color: black; /* or whatever your want */\n"
            + "						pointer-events: none;\n"
            + "						cursor: default;\n"
            + "					}\n"
            + "\n"
            + "			.mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\n"
            + "						text-decoration: default;\n"
            + "						color: orange !important; /* or whatever your want */\n"
            + "						pointer-events: auto;\n"
            + "						cursor: default;\n"
            + "					}\n"
            + "		}\n"
            + "\n"
            + "\n"
            + "		@media only screen and (min-device-width: 768px) and (max-device-width: 1024px) {\n"
            + "			a[href^=\"tel\"], a[href^=\"sms\"] {\n"
            + "						text-decoration: none;\n"
            + "						color: blue; /* or whatever your want */\n"
            + "						pointer-events: none;\n"
            + "						cursor: default;\n"
            + "					}\n"
            + "\n"
            + "			.mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\n"
            + "						text-decoration: default;\n"
            + "						color: orange !important;\n"
            + "						pointer-events: auto;\n"
            + "						cursor: default;\n"
            + "					}\n"
            + "		}\n"
            + "\n"
            + "		@media only screen and (-webkit-min-device-pixel-ratio: 2) {\n"
            + "			/* Put your iPhone 4g styles in here */\n"
            + "		}\n"
            + "\n"
            + "		@media only screen and (-webkit-device-pixel-ratio:.75){\n"
            + "			/* Put CSS for low density (ldpi) Android layouts in here */\n"
            + "		}\n"
            + "		@media only screen and (-webkit-device-pixel-ratio:1){\n"
            + "			/* Put CSS for medium density (mdpi) Android layouts in here */\n"
            + "		}\n"
            + "		@media only screen and (-webkit-device-pixel-ratio:1.5){\n"
            + "			/* Put CSS for high density (hdpi) Android layouts in here */\n"
            + "		}\n"
            + "		/* end Android targeting */\n"
            + "		h2{\n"
            + "			color:#181818;\n"
            + "			font-family:Helvetica, Arial, sans-serif;\n"
            + "			font-size:22px;\n"
            + "			line-height: 22px;\n"
            + "			font-weight: normal;\n"
            + "		}\n"
            + "		a.link1{\n"
            + "\n"
            + "		}\n"
            + "		a.link2{\n"
            + "			color:#fff;\n"
            + "			text-decoration:none;\n"
            + "			font-family:Helvetica, Arial, sans-serif;\n"
            + "			font-size:16px;\n"
            + "			color:#fff;border-radius:4px;\n"
            + "		}\n"
            + "		p{\n"
            + "			color:#555;\n"
            + "			font-family:Helvetica, Arial, sans-serif;\n"
            + "			font-size:16px;\n"
            + "			line-height:160%;\n"
            + "		}\n"
            + "	</style>\n"
            + "\n"
            + "<script type=\"colorScheme\" class=\"swatch active\">\n"
            + "  {\n"
            + "    \"name\":\"Default\",\n"
            + "    \"bgBody\":\"ffffff\",\n"
            + "    \"link\":\"fff\",\n"
            + "    \"color\":\"555555\",\n"
            + "    \"bgItem\":\"ffffff\",\n"
            + "    \"title\":\"181818\"\n"
            + "  }\n"
            + "</script>\n"
            + "\n"
            + "</head>\n"
            + "<body>\n"
            + "	<!-- Wrapper/Container Table: Use a wrapper table to control the width and the background color consistently of your email. Use this approach instead of setting attributes on the body tag. -->\n"
            + "	<table cellpadding=\"0\" width=\"100%\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" class='bgBody'>\n"
            + "	<tr>\n"
            + "		<td>\n"
            + "	<table cellpadding=\"0\" width=\"620\" class=\"container\" align=\"center\" cellspacing=\"0\" border=\"0\">\n"
            + "	<tr>\n"
            + "		<td>\n"
            + "		<!-- Tables are the most common way to format your email consistently. Set your table widths inside cells and in most cases reset cellpadding, cellspacing, and border to zero. Use nested tables as a way to space effectively in your message. -->\n"
            + "		\n"
            + "\n"
            + "		<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"600\" class=\"container\">\n"
            + "			<tr>\n"
            + "				<td class='movableContentContainer bgItem'>\n"
            + "\n"
            + "					<div class='movableContent'>\n"
            + "						<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"600\" class=\"container\">\n"
            + "							<tr height=\"40\">\n"
            + "								<td width=\"200\">&nbsp;</td>\n"
            + "								<td width=\"200\">&nbsp;</td>\n"
            + "								<td width=\"200\">&nbsp;</td>\n"
            + "							</tr>\n"
            + "							<tr>\n"
            + "								<td width=\"200\" valign=\"top\">&nbsp;</td>\n"
            + "								<td width=\"200\" valign=\"top\" align=\"center\">\n"
            + "									<div class=\"contentEditableContainer contentImageEditable\">\n"
            + "					                	<div class=\"contentEditable\" align='center' >\n"
            + "					                  		<img src=\"http://img11.hostingpics.net/pics/988324e51ead21aa0f2344e3e5983837af4dfc.png\" width=\"200\" height=\"155\"  alt='Logo'  data-default=\"placeholder\" />\n"
            + "					                	</div>\n"
            + "					              	</div>\n"
            + "								</td>\n"
            + "								<td width=\"200\" valign=\"top\">&nbsp;</td>\n"
            + "							</tr>\n"
            + "							<tr height=\"25\">\n"
            + "								<td width=\"200\">&nbsp;</td>\n"
            + "								<td width=\"200\">&nbsp;</td>\n"
            + "								<td width=\"200\">&nbsp;</td>\n"
            + "							</tr>\n"
            + "						</table>\n"
            + "					</div>\n"
            + "\n"
            + "					<div class='movableContent'>\n"
            + "						<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"600\" class=\"container\">\n"
            + "							<tr>\n"
            + "								<td width=\"100%\" colspan=\"3\" align=\"center\" style=\"padding-bottom:10px;padding-top:25px;\">\n"
            + "									<div class=\"contentEditableContainer contentTextEditable\">\n"
            + "					                	<div class=\"contentEditable\" align='center' >\n"
            + "					                  		<h2 >Hangar Game </h2>\n"
            + "					                	</div>\n"
            + "					              	</div>\n"
            + "								</td>\n"
            + "							</tr>\n"
            + "							<tr>\n"
            + "								<td width=\"100\">&nbsp;</td>\n"
            + "								<td width=\"400\" align=\"center\">\n"
            + "									<div class=\"contentEditableContainer contentTextEditable\">\n"
            + "					                	<div class=\"contentEditable\" align='left' >\n"
            + "					                  		<p >Bonjour " + login+ ",\n"
            + "					                  			<br/>\n"
            + "					                  			<br/>\n"
            + "												Votre code de validation est: <br/>\n <span style=\"color:rgb(80,80,80);\">" + code + "</span></p>\n"
            + "					                	</div>\n"
            + "					              	</div>\n"
            + "								</td>\n"
            + "								<td width=\"100\">&nbsp;</td>\n"
            + "							</tr>\n"
            + "						</table>\n"
            + "					</div>\n"
            + "\n"
            + "\n"
            + "					<div class='movableContent'>\n"
            + "						<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"600\" class=\"container\">\n"
            + "							<tr>\n"
            + "								<td width=\"100%\" colspan=\"2\" style=\"padding-top:65px;\">\n"
            + "									<hr style=\"height:1px;border:none;color:#333;background-color:#ddd;\" />\n"
            + "								</td>\n"
            + "							</tr>\n"
            + "							<tr>\n"
            + "								<td width=\"60%\" height=\"70\" valign=\"middle\" style=\"padding-bottom:20px;\">\n"
            + "									<div class=\"contentEditableContainer contentTextEditable\">\n"
            + "					                	<div class=\"contentEditable\" align='left' >\n"
            + "					                  		<span style=\"font-size:13px;color:#181818;font-family:Helvetica, Arial, sans-serif;line-height:200%;\">Sent to " + "Email" + " by Hangar Game </span>\n"
            + "											<br/>\n"
            + "											<span style=\"font-size:11px;color:#555;font-family:Helvetica, Arial, sans-serif;line-height:200%;\"> Ariana Soghra Tunis | +216.92.505.082</span>\n"
            + "											<br/>\n"
            + "											<br/>\n"
            + "					                	</div>\n"
            + "					              	</div>\n"
            + "								</td>\n"
            + "								<td width=\"40%\" height=\"70\" align=\"right\" valign=\"top\" align='right' style=\"padding-bottom:20px;\">\n"
            + "									<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align='right'>\n"
            + "										<tr>\n"
            + "											<td width='57%'></td>\n"
            + "											<td valign=\"top\" width='34'>\n"
            + "												<div class=\"contentEditableContainer contentFacebookEditable\" style='display:inline;'>\n"
            + "							                        <div class=\"contentEditable\" >\n"
            + "							                            <img src=\"http://www.theboatsafe.com/wp-content/uploads/shopp/Facebook.Circle.png\" data-default=\"placeholder\" data-max-width='30' data-customIcon=\"true\" width='30' height='30' alt='facebook' style='margin-right:40x;'>\n"
            + "							                        </div>\n"
            + "							                    </div>\n"
            + "											</td>\n"
            + "											<td valign=\"top\" width='34'>\n"
            + "												<div class=\"contentEditableContainer contentTwitterEditable\" style='display:inline;'>\n"
            + "							                      <div class=\"contentEditable\" >\n"
            + "							                        <img src=\"http://icons.iconarchive.com/icons/sicons/basic-round-social/512/twitter-icon.png\" data-default=\"placeholder\" data-max-width='30' data-customIcon=\"true\" width='30' height='30' alt='twitter' style='margin-right:40x;'>\n"
            + "							                      </div>\n"
            + "							                    </div>\n"
            + "											</td>\n"
            + "											<td valign=\"top\" width='34'>\n"
            + "												<div class=\"contentEditableContainer contentImageEditable\" style='display:inline;'>\n"
            + "							                      <div class=\"contentEditable\" >\n"
            + "														<img src=\"http://kizeeva.com/images/deliciousladies_google.png\" width=\"30\" height=\"30\" data-max-width=\"30\" alt='pinterest' style='margin-right:40x;' />\n"
            + "							                      </div>\n"
            + "							                    </div>\n"
            + "											</td>\n"
            + "										</tr>\n"
            + "									</table>\n"
            + "								</td>\n"
            + "							</tr>\n"
            + "						</table>\n"
            + "					</div>\n"
            + "\n"
            + "\n"
            + "				</td>\n"
            + "			</tr>\n"
            + "		</table>\n"
            + "\n"
            + "		\n"
            + "		\n"
            + "\n"
            + "	</td></tr></table>\n"
            + "	\n"
            + "		</td>\n"
            + "	</tr>\n"
            + "	</table>\n"
            + "	<!-- End of wrapper table -->\n"
            + "\n"
            + "<!--Default Zone\n"
            + "\n"
            + "    <div class=\"customZone\" data-type=\"image\">\n"
            + "        <div class=\"movableContent\">\n"
            + "        	<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"600\">        		\n"
            + "				<tr><td colspan='3' height='30'></td></tr>\n"
            + "				<tr>\n"
            + "					<td width=\"50\">&nbsp;</td>\n"
            + "					<td width=\"500\" colspan=\"3\" align=\"center\" style=\"padding-bottom:10px;padding-top:25px;\">\n"
            + "						<div class=\"contentEditableContainer contentImageEditable\">\n"
            + "			                <div class=\"contentEditable\">\n"
            + "			                   <img src=\"/Users/oualha/NetBeansProjects/e_shop/src/images/logoomar.png\" data-default=\"placeholder\" data-max-width=\"500\">\n"
            + "			                </div>\n"
            + "			            </div>\n"
            + "					</td>\n"
            + "					<td width=\"50\">&nbsp;</td>\n"
            + "				</tr>\n"
            + "			</table>\n"
            + "        </div>\n"
            + "    </div>\n"
            + "\n"
            + "    <div class=\"customZone\" data-type=\"text\">\n"
            + "        <div class='movableContent'>\n"
            + "			<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"600\">\n"
            + "				<tr><td colspan='3' height='30'></td></tr>\n"
            + "				<tr>\n"
            + "					<td width=\"50\">&nbsp;</td>\n"
            + "					<td width=\"500\"  align=\"center\" style=\"padding-bottom:10px;padding-top:25px;\">\n"
            + "						<div class=\"contentEditableContainer contentTextEditable\" >\n"
            + "	                        <div class=\"contentEditable\" >\n"
            + "	                            \n"
            + "								<h2 >Make sure you’re recognizable</h2>\n"
            + "	                        </div>\n"
            + "	                    </div>\n"
            + "					</td>\n"
            + "					<td width=\"50\">&nbsp;</td>\n"
            + "				</tr>\n"
            + "				<tr>\n"
            + "					<td width=\"50\">&nbsp;</td>\n"
            + "					<td width=\"500\" align=\"center\">\n"
            + "						<div class=\"contentEditableContainer contentTextEditable\" >\n"
            + "	                        <div class=\"contentEditable\" >\n"
            + "	                            <p >\n"
            + "								<p>Include both the name of the person who’s sending the email as well as the name of the company, and even better: send using your own domain.</p>\n"
            + "								</p>\n"
            + "	                        </div>\n"
            + "	                    </div>\n"
            + "						\n"
            + "					</td>\n"
            + "					<td width=\"50\">&nbsp;</td>\n"
            + "				</tr>\n"
            + "				<tr><td colspan=\"3\" height='30'></td></tr>\n"
            + "				<tr>\n"
            + "					<td width=\"50\">&nbsp;</td>\n"
            + "					<td width=\"500\" align=\"center\" >\n"
            + "						<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"400\" height=\"50\">\n"
            + "							<tr>\n"
            + "								<td bgcolor=\"#ED006F\" align=\"center\" style=\"border-radius:4px;\" width=\"400\" height=\"50\">\n"
            + "									<div class=\"contentEditableContainer contentTextEditable\" >\n"
            + "				                        <div class=\"contentEditable\" style='text-align:center;'>\n"
            + "				                            <a target='_blank' href=\"[CLIENTS.WEBSITE]\" class='link2'>Read the 3 rules of email marketing sender etiquette</a>\n"
            + "				                        </div>\n"
            + "				                    </div>\n"
            + "								</td>\n"
            + "							</tr>\n"
            + "\n"
            + "						</table>\n"
            + "					</td>\n"
            + "					<td width=\"50\">&nbsp;</td>\n"
            + "				</tr>\n"
            + "				<tr><td height=\"10\" colspan=\"3\"></td></tr>\n"
            + "			</table>\n"
            + "		</div>\n"
            + "    </div>\n"
            + "\n"
            + "    <div class=\"customZone\" data-type=\"imageText\">\n"
            + "        <div class=\"movableContent\">\n"
            + "            <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width='600'>\n"
            + "            	<tr><td colspan=\"5\" height='30'></td></tr>\n"
            + "                <tr>\n"
            + "                	<td width='50'></td>\n"
            + "                    <td valign=\"top\" width='150'>\n"
            + "                        <div class=\"contentEditableContainer contentImageEditable\">\n"
            + "                            <div class=\"contentEditable\">\n"
            + "                                <img src=\"/Users/oualha/NetBeansProjects/e_shop/src/images/logoomar.png\" data-default=\"placeholder\" width='150' data-max-width=\"150\">\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                    </td>\n"
            + "                    <td width='20'></td>\n"
            + "                    <td valign=\"top\"  width='250'>\n"
            + "                        <div class=\"contentEditableContainer contentTextEditable\">\n"
            + "                            <div class=\"contentEditable\" style=\"color:#555;font-family:Helvetica, Arial, sans-serif;font-size:16px;line-height:160%;\">\n"
            + "                                <h2 >This is a subtitle</h2>\n"
            + "                                <p style=\"text-align:left;\">Etiam bibendum nunc in lacus bibendum porta. Vestibulum nec nulla et eros ornare condimentum. Proin facilisis, dui in mollis blandit. Sed non dui magna, quis tincidunt enim. Morbi vehicula pharetra lacinia.</p>\n"
            + "                                <p style=\"text-align:right;\"><a target='_blank' href=\"\">Read more</a></p>\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                    </td>\n"
            + "                    <td width='50'></td>\n"
            + "                </tr>\n"
            + "            </table>\n"
            + "        </div>\n"
            + "    </div>\n"
            + "\n"
            + "    <div class=\"customZone\" data-type=\"Textimage\">\n"
            + "        <div class=\"movableContent\">\n"
            + "            <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width='600'>\n"
            + "            	<tr><td colspan=\"5\" height='30'></td></tr>\n"
            + "                <tr>\n"
            + "                	<td width='50'></td>\n"
            + "                    <td valign=\"top\" width='230'>\n"
            + "                        <div class=\"contentEditableContainer contentTextEditable\">\n"
            + "                            <div class=\"contentEditable\" style=\"color:#555;font-family:Helvetica, Arial, sans-serif;font-size:16px;line-height:160%;\">\n"
            + "                                <h2 >This is a subtitle</h2>\n"
            + "                                <p style=\"text-align:left;\">Etiam bibendum nunc in lacus bibendum porta. Vestibulum nec nulla et eros ornare condimentum. Proin facilisis, dui in mollis blandit. Sed non dui magna, quis tincidunt enim. Morbi vehicula pharetra lacinia. </p>\n"
            + "                                <p style=\"text-align:right;\"><a target='_blank' href=\"\">Read more</a></p>\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                    </td>\n"
            + "                    <td width='20'></td>\n"
            + "                    <td valign=\"top\" width='150'>\n"
            + "                        <div class=\"contentEditableContainer contentImageEditable\">\n"
            + "                            <div class=\"contentEditable\">\n"
            + "                                <img src=\"/Users/oualha/NetBeansProjects/e_shop/src/images/logoomar.png\" data-default=\"placeholder\" width='150' data-max-width=\"150\">\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                    </td>\n"
            + "                    <td width='50'></td>\n"
            + "                </tr>\n"
            + "            </table>\n"
            + "        </div>\n"
            + "    </div>\n"
            + "\n"
            + "    <div class=\"customZone\" data-type=\"textText\">\n"
            + "        <div class=\"movableContent\">\n"
            + "            <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width='600'>\n"
            + "            	<tr><td height='30' colspan='5'></td></tr>\n"
            + "                <tr>\n"
            + "                	<td width='50'></td>\n"
            + "                    <td valign=\"top\" width=\"230\">\n"
            + "                        <div class=\"contentEditableContainer contentTextEditable\">\n"
            + "                            <div class=\"contentEditable\" style=\"color:#555;font-family:Helvetica, Arial, sans-serif;font-size:16px;line-height:160%;\">\n"
            + "                                <h2 >This is a subtitle</h2>\n"
            + "                                <p >Etiam bibendum nunc in lacus bibendum porta. Vestibulum nec nulla et eros ornare condimentum. Proin facilisis, dui in mollis blandit. Sed non dui magna, quis tincidunt enim.</p>\n"
            + "                                <p style=\"text-align:right;\"><a target='_blank' href=\"\">Read more</a></p>\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                    </td>\n"
            + "                    <td width='40'></td>\n"
            + "                    <td valign=\"top\" width='230'>\n"
            + "                        <div class=\"contentEditableContainer contentTextEditable\">\n"
            + "                            <div class=\"contentEditable\" style=\"color:#555;font-family:Helvetica, Arial, sans-serif;font-size:16px;line-height:160%;\">\n"
            + "                                <h2 >This is a subtitle</h2>\n"
            + "                                <p style=\"text-align:left;\">Etiam bibendum nunc in lacus bibendum porta. Vestibulum nec nulla et eros ornare condimentum. Proin facilisis, dui in mollis blandit. Sed non dui magna, quis tincidunt enim.</p>\n"
            + "                                <p style=\"text-align:right;\"><a target='_blank' href=\"\">Read more</a></p>\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                    </td>\n"
            + "                    <td width='50'></td>\n"
            + "                </tr>\n"
            + "            </table>\n"
            + "        </div>\n"
            + "    </div>\n"
            + "\n"
            + "    <div class=\"customZone\" data-type=\"qrcode\">\n"
            + "        <div class=\"movableContent\">\n"
            + "            <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width='600'>\n"
            + "            	<tr><td height='30' colspan='5'></td></tr>\n"
            + "                <tr>\n"
            + "                	<td width='50'></td>\n"
            + "                    <td valign=\"top\" >\n"
            + "                        <div class=\"contentQrcodeEditable contentEditableContainer\">\n"
            + "                            <div class=\"contentEditable\">\n"
            + "                                <img src=\"/Users/oualha/NetBeansProjects/e_shop/src/images/logoomar.png\" width=\"75\" height=\"75\" data-default=\"placeholder\">\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                    </td>\n"
            + "                    <td width='20'></td>\n"
            + "                    <td valign=\"top\">\n"
            + "                        <div class=\"contentEditableContainer contentTextEditable\">\n"
            + "                            <div class=\"contentEditable\" style=\"color:#555;font-family:Helvetica, Arial, sans-serif;font-size:16px;line-height:160%;\">\n"
            + "                                <h2 >This is a subtitle</h2>\n"
            + "                                <p style=\"text-align:left;\">Etiam bibendum nunc in lacus bibendum porta. Vestibulum nec nulla et eros ornare condimentum. Proin facilisis, dui in mollis blandit. Sed non dui magna, quis tincidunt enim. Morbi vehicula pharetra lacinia. Cras tincidunt, justo at fermentum feugiat, eros orci accumsan dolor, eu ultricies eros dolor quis sapien. Curabitur in turpis sem, a sodales purus. Pellentesque et risus at mauris aliquet gravida.</p>\n"
            + "                                <p style=\"text-align:left;\">Integer in elit in tortor posuere molestie non a velit. Pellentesque consectetur, nisi a euismod scelerisque.</p>\n"
            + "                                <p style=\"text-align:right;\"><a target='_blank' href=\"\">Read more</a></p>\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                    </td>\n"
            + "                    <td width='50'></td>\n"
            + "                </tr>\n"
            + "            </table>\n"
            + "        </div>\n"
            + "    </div>\n"
            + "\n"
            + "    <div class=\"customZone\" data-type=\"social\">\n"
            + "        <div class=\"movableContent\" align='center'>\n"
            + "            <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width='600'>\n"
            + "            	<tr><td height='30' colspan='5'></td></tr>\n"
            + "                <tr>\n"
            + "                	<td width='50'></td>\n"
            + "                    <td valign=\"top\" width=\"230\" align='center'>\n"
            + "                        <div class=\"contentEditableContainer contentFacebookEditable\">\n"
            + "                            <div class=\"contentEditable\">\n"
            + "                                <img data-default=\"placeholder\" src=\"images/facebook.png\" data-max-width='60' data-customIcon=\"true\" data-noText=\"true\" width='60' height='60'>\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                        <div class=\"contentEditableContainer contentTextEditable\">\n"
            + "                            <div class=\"contentEditable\" style=\"color:#555;font-family:Helvetica, Arial, sans-serif;font-size:16px;line-height:160%;\">\n"
            + "                                <h2 >Facebook</h2>\n"
            + "                                <p>Like us on Facebook to keep up with our news, updates and other discussions.</p>\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                    </td>\n"
            + "                    <td width='40'></td>\n"
            + "                    <td valign=\"top\" width=\"230\" align='center'>\n"
            + "                        <div class=\"contentEditableContainer contentTwitterEditable\">\n"
            + "                            <div class=\"contentEditable\">\n"
            + "                                <img data-default=\"placeholder\" src=\"images/twitter.png\" data-max-width='60' data-customIcon=\"true\" data-noText=\"true\" width='60' height='60'>\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                        <div class=\"contentEditableContainer contentTextEditable\">\n"
            + "                            <div class=\"contentEditable\" style=\"color:#555;font-family:Helvetica, Arial, sans-serif;font-size:16px;line-height:160%;\">\n"
            + "                                <h2 >Twitter</h2>\n"
            + "                                <p>Follow us on twitter to stay up to date with company news and other information.</p>\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                    </td>\n"
            + "                    <td width='50'></td>\n"
            + "                </tr>\n"
            + "            </table>\n"
            + "        </div>\n"
            + "    </div>\n"
            + "\n"
            + "    <div class=\"customZone\" data-type=\"twitter\">\n"
            + "        <div class=\"movableContent\" align='center'>\n"
            + "            <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width='600'>\n"
            + "            	<tr><td height='30' colspan='3'></td></tr>\n"
            + "                <tr>\n"
            + "                	<td width='50'></td>\n"
            + "                    <td valign=\"top\" align='center'>\n"
            + "                        <div class=\"contentEditableContainer contentTwitterEditable\">\n"
            + "                            <div class=\"contentEditable\">\n"
            + "                                <img data-default=\"placeholder\" src=\"images/twitter.png\" data-max-width='60' data-customIcon=\"true\" data-noText=\"true\" width='60' height='60'>\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                        <div class=\"contentEditableContainer contentTextEditable\">\n"
            + "                            <div class=\"contentEditable\" style=\"color:#555;font-family:Helvetica, Arial, sans-serif;font-size:16px;line-height:160%;\">\n"
            + "                                <h2 >Twitter</h2>\n"
            + "                                <p>Follow us on twitter to stay up to date with company news and other information.</p>\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                    </td>\n"
            + "                    <td width='50'></td>\n"
            + "                </tr>\n"
            + "            </table>\n"
            + "        </div>\n"
            + "   </div>\n"
            + "\n"
            + "    <div class=\"customZone\" data-type=\"facebook\" >\n"
            + "        <div class=\"movableContent\" align='center'>\n"
            + "            <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width='600'>\n"
            + "            	<tr><td height='30' colspan='3'></td></tr>\n"
            + "                <tr>\n"
            + "                	<td width='50'></td>\n"
            + "                    <td valign=\"top\" align='center'>\n"
            + "                        <div class=\"contentEditableContainer contentFacebookEditable\">\n"
            + "                            <div class=\"contentEditable\">\n"
            + "                                <img data-default=\"placeholder\" src=\"images/facebook.png\" data-max-width='60' data-customIcon=\"true\" data-noText=\"true\" width='60' height='60'>\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                        <div class=\"contentEditableContainer contentTextEditable\">\n"
            + "                            <div class=\"contentEditable\" style=\"color:#555;font-family:Helvetica, Arial, sans-serif;font-size:16px;line-height:160%;\">\n"
            + "                                <h2>Facebook</h2>\n"
            + "                                <p>Like us on Facebook to keep up with our news, updates and other discussions.</p>\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                    </td>\n"
            + "                    <td width='50'></td>\n"
            + "                </tr>\n"
            + "            </table>\n"
            + "        </div>\n"
            + "    </div>\n"
            + "\n"
            + "    <div class=\"customZone\" data-type=\"gmap\">\n"
            + "        <div class=\"movableContent\">\n"
            + "            <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width='600'>\n"
            + "                <tr><td height='30' colspan='5'></td></tr>\n"
            + "                <tr>\n"
            + "                	<td width='50'></td>\n"
            + "                    <td valign=\"top\" >\n"
            + "                        <div class=\"contentGmapEditable contentEditableContainer\">\n"
            + "                            <div class=\"contentEditable\">\n"
            + "                                <img src=\"/applications/Mail_Interface/3_3/modules/User_Interface/core/v31_campaigns/images/neweditor/default/gmap_example.png\" width=\"75\" data-default=\"placeholder\">\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                    </td>\n"
            + "                    <td width='20'></td>\n"
            + "                    <td valign=\"top\">\n"
            + "                        <div class=\"contentEditableContainer contentTextEditable\">\n"
            + "                            <div class=\"contentEditable\" style=\"color:#555;font-family:Helvetica, Arial, sans-serif;font-size:16px;line-height:160%;\">\n"
            + "                                <h2 >This is a subtitle</h2>\n"
            + "                                <p style=\"text-align:left;\">Etiam bibendum nunc in lacus bibendum porta. Vestibulum nec nulla et eros ornare condimentum. Proin facilisis, dui in mollis blandit. Sed non dui magna, quis tincidunt enim. Morbi vehicula pharetra lacinia. Cras tincidunt, justo at fermentum feugiat, eros orci accumsan dolor, eu ultricies eros dolor quis sapien. Curabitur in turpis sem, a sodales purus. Pellentesque et risus at mauris aliquet gravida.</p>\n"
            + "                                <p style=\"text-align:left;\">Integer in elit in tortor posuere molestie non a velit. Pellentesque consectetur, nisi a euismod scelerisque.</p>\n"
            + "                                <p style=\"text-align:right;\"><a target='_blank' href=\"\">Read more</a></p>\n"
            + "                            </div>\n"
            + "                        </div>\n"
            + "                    </td>\n"
            + "                    <td width='50'></td>\n"
            + "                </tr>\n"
            + "            </table>\n"
            + "        </div>\n"
            + "    </div>\n"
            + "\n"
            + "\n"
            + "	 <div class=\"customZone\" data-type=\"colums1v2\"><div class='movableContent'>\n"
            + "          	<table width=\"600\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" >\n"
            + "	            <tr><td height=\"30\" colspan='3'>&nbsp;</td></tr>\n"
            + "	            <tr>\n"
            + "	            	<td width='50'></td>\n"
            + "	              	<td width='500' align=\"center\" valign=\"top\" class=\"newcontent\">\n"
            + "	                \n"
            + "	              	</td>\n"
            + "	              	<td width='50'></td>\n"
            + "	            </tr>\n"
            + "          	</table>\n"
            + "    	</div>\n"
            + "      </div>\n"
            + "\n"
            + "      <div class=\"customZone\" data-type=\"colums2v2\"><div class='movableContent'>\n"
            + "          <table width=\"600\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" >\n"
            + "	            <tr><td height=\"30\" colspan='3'>&nbsp;</td></tr>\n"
            + "	            <tr>\n"
            + "	            	<td width='50'></td>\n"
            + "	              	<td width='235' align=\"center\" valign=\"top\" class=\"newcontent\">\n"
            + "	                \n"
            + "	              	</td>\n"
            + "					<td width='30'></td>\n"
            + "	              	<td width='235' align=\"center\" valign=\"top\" class=\"newcontent\">\n"
            + "	                \n"
            + "	              	</td>\n"
            + "	              	<td width='50'></td>\n"
            + "	            </tr>\n"
            + "          	</table>\n"
            + "    	</div>\n"
            + "      </div>\n"
            + "\n"
            + "      <div class=\"customZone\" data-type=\"colums3v2\"><div class='movableContent'>\n"
            + "         <table width=\"600\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" >\n"
            + "	            <tr><td height=\"30\" colspan='3'>&nbsp;</td></tr>\n"
            + "	            <tr>\n"
            + "	            	<td width='50'></td>\n"
            + "	              	<td width='158' align=\"center\" valign=\"top\" class=\"newcontent\">\n"
            + "	                \n"
            + "	              	</td>\n"
            + "					<td width='12'></td>\n"
            + "	              	<td width='158' align=\"center\" valign=\"top\" class=\"newcontent\">\n"
            + "	                \n"
            + "	              	</td>\n"
            + "					<td width='12'></td>\n"
            + "	              	<td width='158' align=\"center\" valign=\"top\" class=\"newcontent\">\n"
            + "	                \n"
            + "	              	</td>\n"
            + "	              	<td width='50'></td>\n"
            + "	            </tr>\n"
            + "          	</table>\n"
            + "    	</div>\n"
            + "      </div>\n"
            + "\n"
            + "      <div class=\"customZone\" data-type=\"textv2\">\n"
            + "		<div class=\"contentEditableContainer contentTextEditable\" >\n"
            + "            <div class=\"contentEditable\" >\n"
            + "                \n"
            + "				<h2 >Make sure you’re recognizable</h2>\n"
            + "            </div>\n"
            + "        </div>\n"
            + "		<div class=\"contentEditableContainer contentTextEditable\" >\n"
            + "            <div class=\"contentEditable\" >\n"
            + "				<p>Include both the name of the person who’s sending the email as well as the name of the company, and even better: send using your own domain.</p>\n"
            + "            </div>\n"
            + "        </div>\n"
            + "		<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"79%\" height=\"50\">\n"
            + "			<tr>\n"
            + "				<td bgcolor=\"#ED006F\" align=\"center\" style=\"border-radius:4px;\" width=\"100%\" height=\"50\">\n"
            + "					<div class=\"contentEditableContainer contentTextEditable\" >\n"
            + "                        <div class=\"contentEditable\" style='text-align:center;'>\n"
            + "                            <a target='_blank' href=\"[CLIENTS.WEBSITE]\" class='link2'>Read the 3 rules of email marketing sender etiquette</a>\n"
            + "                        </div>\n"
            + "                    </div>\n"
            + "				</td>\n"
            + "			</tr>\n"
            + "		</table>\n"
            + "      </div>\n"
            + "\n"
            + "\n"
            + "\n"
            + "\n"
            + "-->\n"
            + "<!--Default Zone End-->\n"
            + "\n"
            + "</body>\n"
            + "</html>";
                        
                        
                        sendMail.SendPers(mail, body);
                        
                        

                        return true;
                       

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Gamer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CrudAnnonces.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }

    
        return true;
    }

    @Override
    public int Authentification(String login, String password) {

        String req4 = "select * from Gamer where username=? or email= ? and password = ? ";
        try {
            prepste = connect.prepareStatement(req4);
            prepste.setString(1, login);
            prepste.setString(2, login);
            prepste.setString(3, password);
          
            ResultSet res = prepste.executeQuery();

            while (res.next()) {
                    
                String resultLogin = res.getString(9);
                String resultemail = res.getString(11);
                String resultPassword = res.getString(15);
                String resultRole = res.getString(19);
                int resultEtat = res.getInt(21);
                if(resultEtat==0)
                {
                if (resultRole.equals("a:1:{i:0;s:11:\"ROLE_CLIENT\";}")) {
                    if ((resultLogin.equals(login) && resultPassword.equals(password)) || (resultemail.equals(login) && resultPassword.equals(password))) {
                        return 1;
                    }

                } else if (resultRole.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
                    if (resultLogin.equals(login) && resultPassword.equals(password) || (resultemail.equals(login) && resultPassword.equals(password))) {
                        System.out.println(resultemail + resultPassword);
                        return 2;
                    }
                }

            } else if(resultEtat==1)
                    {
                        return 3;
                    }
            }
            res.close();

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
     public int recTelephone(int gamer){
        
        
      String query = "Select * from gamer where id =?";
    
        try {
            prepste = connect.prepareStatement(query);
            prepste.setInt(1, gamer);
            ResultSet rs = prepste.executeQuery();
            while(rs.next()){
           
            int telG= rs.getInt("tel");
           
            
     
           return telG;
              
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return 0;
}

    @Override
    public boolean VerifMail(String email) {
        String req2 = "select * from Gamer where email= '" + email + "'";
        try {

            prepste = connect.prepareStatement(req2);
            ResultSet resultat = prepste.executeQuery();

            if (resultat.next()) {
                String resultEmail = resultat.getString(11);
                if (resultEmail.equals(email)) { //mail existant
                    return false;
                }
                resultat.close();

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return true;

    }

    @Override
    public boolean VerifLogin(String login) {
        String req2 = "select * from Gamer where username= '" + login + "'";
        try {

            prepste = connect.prepareStatement(req2);
            ResultSet resultat = prepste.executeQuery();

            if (resultat.next()) {
                String resultLogin = resultat.getString(6);
                if (resultLogin.equals(login)) { //login existant
                    return false;
                }
                resultat.close();

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return true;
    }

    @Override
    public boolean EmailValidation(String email) {
        boolean status = false;
        String email_Pattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(email_Pattern);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    @Override
    public boolean ValidationCode(String login, String codeValidation) {
        int a = 0;
        String req1 = "select codeValidation from gamer where username=? or email=?";

        try {

            prepste = connect.prepareStatement(req1);
            prepste.setString(1, login);
            prepste.setString(2, login);
            ResultSet resultat = prepste.executeQuery();

            if (resultat.next()) {
                String code = resultat.getString(1);
                if (code.equals(codeValidation)) {
                    a = 1;

                }
                resultat.close();

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }

        String req = " update gamer set validation = '1' "
                + "where username=? or email=?  and codeValidation=?";

        if (a == 1) {
            try {
                prepste = connect.prepareStatement(req);
                prepste.setString(1, login);
                prepste.setString(2, login);
                prepste.setString(3, codeValidation);

                prepste.executeUpdate();
                System.out.println("ci bi=on");
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public String RecupererPassword(String email) {
        String req = "select password from gamer where email ='" + email + "'";
        try {

            prepste = connect.prepareStatement(req);
            ResultSet resultat = prepste.executeQuery();

            if (resultat.next()) {
                String passwordL = resultat.getString(1);
                SendPassword sendMail = new SendPassword();
                sendMail.send(email, passwordL);
                return passwordL;

            }
            resultat.close();

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    @Override
    public boolean ActivationCompte(String login) {
        String role = "a:1:{i:0;s:11:\"ROLE_CLIENT\";}";
        String req = "select * from gamer where username= ? or email = ? and roles = ? ";

        try {

            prepste = connect.prepareStatement(req);
            prepste.setString(1, login);
            prepste.setString(2, login);
            prepste.setString(3, role);

            ResultSet resultat = prepste.executeQuery();

            if (resultat.next()) {
                String resultEmail = resultat.getString(11);
                String resultLogin = resultat.getString(9);
                int resultValidation = resultat.getInt(8);
                System.out.println(resultValidation);
               

                if ((resultEmail.equals(login)) || (resultLogin.equals(login))) {
                    if (resultValidation == 0) {
                        
                        return false;
                    } else if (resultValidation == 1) {
                        
                        return true;
                    }
                }

            }

            resultat.close();

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    @Override
    public Gamer ModifierInfo(String nom, String prenom, String adresse, int tel, String login, String input) {
        String req = "update gamer set nom=?, prenom=?, adresse=?, tel=? , image=? where username='" + login + "'";
        try {
            InputStream inputStream;

            inputStream = new FileInputStream(input);

            prepste = connect.prepareStatement(req);
            prepste.setString(1, nom);
            prepste.setString(2, prenom);
            prepste.setString(3, adresse);
            prepste.setInt(4, tel);
            prepste.setBlob(5, inputStream);
            prepste.executeUpdate();
            Gamer g = new Gamer(nom, prenom, adresse, tel, inputStream);
            return g;
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Gamer Afficher(String login) {
       
        String req = "select nom,prenom,adresse,tel,email,dateInscription,image from gamer where username= ?";
        try {

            prepste = connect.prepareStatement(req);
            prepste.setString(1, login);
            ResultSet resultat = prepste.executeQuery();

            if (resultat.next()) {
                String resultNom = resultat.getString(1);
                String resultPrenom = resultat.getString(2);
                String resultAdresse = resultat.getString(3);
                int resultTel = resultat.getInt(4);
                String resultEmail = resultat.getString(5);
                Timestamp resultDateInscri = resultat.getTimestamp(6);
                InputStream image = resultat.getBlob(7).getBinaryStream();

                Gamer g = new Gamer(resultNom, resultPrenom, resultAdresse, resultTel, resultEmail, resultDateInscri, image);
                return g;
            }
            resultat.close();

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    @Override
    public boolean ChangePassword(String password1, String password2, String login) {
        String req = "update gamer set password=? where username= ?";
        try {
            if (password1.equals(password2)) {
                prepste = connect.prepareStatement(req);

                prepste.setString(1, password1);
                prepste.setString(2, login);
                prepste.executeUpdate();

                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Gamer> AfficherListeGamer() {
        String role = "a:1:{i:0;s:11:\"ROLE_CLIENT\";}";
        String query = "Select * from gamer where roles ='" + role + "'";

        try {
            prepste = connect.prepareStatement(query);
            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                String loginG = rs.getString("username");
                String nomG = rs.getString("nom");
                String prenomG = rs.getString("prenom");
                String emailG = rs.getString("email");
                String adresseG = rs.getString("adresse");
                int telG = rs.getInt("tel");
                Timestamp dateInsriG = rs.getTimestamp("dateInscription");
                Blob imageG = rs.getBlob("image");
                int compte = rs.getInt("etat");

                Gamer gamers = new Gamer(loginG, nomG, prenomG, adresseG, telG, emailG, dateInsriG, imageG.getBinaryStream(), compte);

                list.add(gamers);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean BloquerGamer(String login) {
        String req = "update gamer set etat=? where username='" + login + "'";
        try {

            prepste = connect.prepareStatement(req);

            prepste.setInt(1, 1);
            prepste.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean InscriptionFB(Gamer g) {
        String req = "Insert into Gamer(username,nom,prenom,adresse,email,password,dateInscription,LastModifMdp,validation,image,roles,username_canonical,email_canonical,enabled) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {

            InputStream inputStream = g.getImage();
            prepste = connect.prepareStatement(req);

            int validation = 1;

            prepste.setString(1, g.getLogin());
            prepste.setString(2, g.getNom());
            prepste.setString(3, g.getPrenom());
            prepste.setString(4, g.getAdresse());
            prepste.setString(5, g.getEmail());
            prepste.setString(6, g.getPassword());
            prepste.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            prepste.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            prepste.setInt(9, validation);
            prepste.setBlob(10, inputStream);
            prepste.setString(11, "a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
             prepste.setString(12, g.getLogin());
              prepste.setString(13, g.getEmail());
               prepste.setInt(14, 1);
            prepste.executeUpdate();
            InscriptionConfirmationFB sendMail = new InscriptionConfirmationFB();
            sendMail.send(g.getLogin(), g.getPassword(), g.getEmail());

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Gamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String RecupererLoginFromEmail(String email) {
        String req = "select username from gamer where email = ? or username =?";
        try {
            prepste = connect.prepareStatement(req);

            prepste.setString(1, email);
            prepste.setString(2, email);

            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                String resulEmail = rs.getString(1);
                return resulEmail;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return " ";
    }

    
    public int RecupererIdFromEmail(String email) {
        String req = "select id from gamer where email = ? or username =?";
        try {
            prepste = connect.prepareStatement(req);

            prepste.setString(1, email);
            prepste.setString(2, email);

            ResultSet rs = prepste.executeQuery();
            while (rs.next()) {
                int resulEmail = rs.getInt(1);
                return resulEmail;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    @Override
    public String RecupererPasswordLogin(String login) {
        String req = "select password from gamer where username ='" + login + "'";
        try {

            prepste = connect.prepareStatement(req);
            ResultSet resultat = prepste.executeQuery();

            if (resultat.next()) {
                String passwordL = resultat.getString(1);
              

                return passwordL;

            }
            resultat.close();

        } catch (SQLException ex) {
            Logger.getLogger(ServicesGamer.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

}
