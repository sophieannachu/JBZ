package Utility;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	
	// �]�w�ǰe�l��:�ܦ��H�H��Email�H�c,Email�D��,Email���e
	public void sendMail(String to, String subject, String messageText, String contextPath) {
			
	   try {
		   // �]�w�ϥ�SSL�s�u�� Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

       // ���]�w gmail ���b�� & �K�X (�N�ǥѧA��Gmail�ӶǰeEmail)
       // �����NmyGmail���i�w���ʸ��C�����ε{���s���v�j���}
	     final String myGmail = "AA103G3@gmail.com";//ixlogic.wu@gmail.com
	     final String myGmail_password = "java03perfect";//AAA45678
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		   //�]�w�H�����D��  
		   message.setSubject(subject);
		   //�]�w�H�������e 
//		   String url="http://localhost:8081/JBZ_back2/back-end/login/login.jsp";
		   String html=messageText + "�n�J�s���b��: <a href=\"http://localhost:8081" + contextPath + "/back-end/login/login.jsp\">Here</a>";
		   message.setContent(html, "text/html; charset=utf-8");
//		   message.setText(messageText);

		   Transport.send(message);
		   System.out.println("�ǰe���!");
     }catch (MessagingException e){
	     System.out.println("�ǰe����!");
	     e.printStackTrace();
     }
   }
	
//	 public static void main (String args[]){
//
//      String to = "s80091901@yahoo.com.tw";//ixlogic@pchome.com.tw
//      
//      String subject = "�K�X�q��";
//      
//      String ch_name = "peter1";
//      String passRandom = "111";
//      String messageText = "Hello! " + ch_name + " ���԰O���K�X: " + passRandom + "\n" +" (�w�g�ҥ�)"; 
//       
//      MailService mailService = new MailService();
//      mailService.sendMail(to, subject, messageText);
//
//   }


}
