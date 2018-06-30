import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test {
		
	public void sendPlainTextEmail(String host,String port, String username, String password , String toAddress , String subject , String message) throws AddressException, MessagingException {
		
		Properties prop=new Properties();
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", port);
		prop.put("mail.smtp.auth","true");
		prop.put("mail.smtp.starttls", "true");
		
		Authenticator auth=new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		};
		
		Session session=Session.getInstance(prop,auth);
		
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(username));
		InternetAddress[] toAddresses= { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(message);
		
		Transport.send(msg);
		
	}
	
	public static void main(String[] args) {
		
		String host="smtp.gmail.com";
		String port="587";
		String mailFrom="antoniojjcitarella@gmail.com";
		String password="3rdJrPassword?!";
		
		String mailTo="antoniojjcitarella@hotmail.it";
		String subject="SOGGETTO";
		String message="messaggio";
		
		Test sender=new Test();
		try {
			sender.sendPlainTextEmail(host, port, mailFrom, password, mailTo, subject, message);
			System.out.println("email-inviata");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("email-non-inviata");
		}
	}
}
