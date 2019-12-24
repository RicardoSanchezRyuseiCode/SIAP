package com.ryuseicode.siap.message;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryuseicode.siap.properties.EmailProperties;

/**
 * @name @name EmailMessage
 * {@summary Class to send emails }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 20, 2019
 */
@Component
public class EmailMessage {
	/*******************************************************/
	/*					Singleton Pattern				   */
	/*******************************************************/
	/**
	 * Singleton
	 */
	//private static EmailMessage SINGLETON = null;
	/**
	 * @name GetInstance
	 * {@summary Method to get instance of email Message }
	 * @return
	 */
	//public static EmailMessage GetInstance() {
	//	return SINGLETON == null ? SINGLETON = new EmailMessage() : null;
	//}
	/*******************************************************/
	/*					   Email Methods				   */
	/*******************************************************/
	@Autowired
	private EmailProperties emailProperties;
	/**
	 * @name getProperties
	 * {@summary Method to get Properties }
	 * @return
	 */
	private Properties getProperties() {
		Properties props = new Properties();
	    props.put("mail.smtp.auth", this.emailProperties.isAuth());
	    props.put("mail.smtp.ssl.enable", this.emailProperties.isSsl());
	    props.put("mail.smtp.host", this.emailProperties.getHost());
	    props.put("mail.smtp.port", this.emailProperties.getPort());
	    return props;
	}
	/**
	 * @name getSession
	 * @{summary Method to get session to send email }
	 * @return
	 */
	private Session getSession() {
		return Session.getInstance(this.getProperties(),
			new javax.mail.Authenticator() {
		        protected PasswordAuthentication getPasswordAuthentication() {
		           return new PasswordAuthentication(emailProperties.getUser(), emailProperties.getPassword());
		        }
		});
	}
	/**
	 * @name getContent
	 * {@summary Method to get email content }
	 * @return
	 */
	private String getContent() throws Exception {
		// Create string builder
		StringBuilder fileContent = new StringBuilder();
		// Define file
		File file = new File(this.emailProperties.getTemplate());
		// Define scanner
		Scanner scanner = new Scanner(file);
		// Loop to read lines
		while (scanner.hasNextLine()) {
			// Append file content
			fileContent.append(scanner.nextLine());
		}
		// Close scanner
		scanner.close();
		// return file content
		return fileContent.toString();
	}
	/**
	 * @name setRecipients
	 * {@summary Method to set recipients }
	 * @param message
	 * @param recipientType
	 * @param address
	 * @throws Exception
	 */
	private void setRecipients(Message message, RecipientType recipientType, List<String> address) throws Exception {
		Address[] messageAddress = new Address[address.size()];
		for(int i = 0; i < address.size(); i++) {
			messageAddress[i] = new InternetAddress(address.get(i));
		}
		message.setRecipients(recipientType, messageAddress);
	}
	/**
	 * @name setRecipients
	 * {@summary Method to set recipients }
	 * @param message
	 * @param recipientType
	 * @param address
	 * @throws Exception
	 */
	private void setRecipients(Message message, RecipientType recipientType, String[] address) throws Exception {
		Address[] messageAddress = new Address[address.length];
		for(int i = 0; i < address.length; i++) {
			messageAddress[i] = new InternetAddress(address[i]);
		}
		message.setRecipients(recipientType, messageAddress);
	}
	/**
	 * @name setBody
	 * {@summary Method to set body }
	 * @param multipart
	 * @param textMessage
	 * @throws Exception
	 */
	private void setBody(Multipart multipart, String textMessage) throws Exception {
		// Set body
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(this.getContent().replaceAll("@@message", textMessage), "text/html; charset=ISO-8859-1");
		multipart.addBodyPart(messageBodyPart);
		// Set embedded images
		// Set header
		File file = new File(this.emailProperties.getTemplate());
		messageBodyPart = new MimeBodyPart();
		DataSource fds = new FileDataSource(Paths.get(file.getParentFile().getPath(), "header.png").toString());
		messageBodyPart.setDataHandler(new DataHandler(fds));
		messageBodyPart.setHeader("Content-ID", "<Header>");
		multipart.addBodyPart(messageBodyPart);
		// Set bottom
		file = new File(this.emailProperties.getTemplate());
		messageBodyPart = new MimeBodyPart();
		fds = new FileDataSource(Paths.get(file.getParentFile().getPath(), "bottom.png").toString());
		messageBodyPart.setDataHandler(new DataHandler(fds));
		messageBodyPart.setHeader("Content-ID", "<Bottom>");
		multipart.addBodyPart(messageBodyPart);
	}
	/**
	 * @name setAttachments
	 * {@summary Method to set attachments}
	 * @param multipart
	 * @param attachments
	 */
	private void setAttachments(Multipart multipart, List<String> attachments) throws Exception {
		for(String attachment: attachments) {
			File file = new File(attachment);
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(attachment);
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        messageBodyPart.setFileName(file.getName());
	        multipart.addBodyPart(messageBodyPart);	        
		}
	}
	/**
	 * @name sendEmail
	 * {@summary Method to send an email }
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param attachments
	 * @param message
	 */
	public void sendEmail(List<String> to, List<String> cc, List<String> bcc, List<String> attachments, String textMessage) throws Exception {
		// Create a default MimeMessage object
		Message message = new MimeMessage(this.getSession());
		// Set From: header field of the header
		message.setFrom(new InternetAddress(this.emailProperties.getFrom()));
		// Set recipietns
		this.setRecipients(message,Message.RecipientType.TO, to);
		this.setRecipients(message,Message.RecipientType.CC, cc);
		this.setRecipients(message,Message.RecipientType.BCC, bcc);
		// Set Subject: header field
		message.setSubject(this.emailProperties.getSubject());
		// Create a multipar message
        Multipart multipart = new MimeMultipart();
		// Set message content
		this.setBody(multipart, textMessage);
		// Set message attachments
		this.setAttachments(multipart, attachments);
		// Send the complete message parts
        message.setContent(multipart);
		// Send message
		Transport.send(message);
	}
	/**
	 * @name sendEmail
	 * {@summary Method to }
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param attachments
	 * @param textMessage
	 * @throws Exception
	 */
	public void sendEmail(String to, String cc, String bcc, List<String> attachments, String textMessage) throws Exception {
		// Create a default MimeMessage object
		Message message = new MimeMessage(this.getSession());
		// Set From: header field of the header
		message.setFrom(new InternetAddress(this.emailProperties.getFrom()));
		// Set recipietns
		if(!to.equals(""))
			this.setRecipients(message,Message.RecipientType.TO, to.split(";"));
		if(!cc.equals(""))
			this.setRecipients(message,Message.RecipientType.CC, cc.split(";"));
		if(!bcc.equals(""))
			this.setRecipients(message,Message.RecipientType.BCC, bcc.split(";"));
		// Set Subject: header field
		message.setSubject(this.emailProperties.getSubject());
		// Create a multipar message
        Multipart multipart = new MimeMultipart();
		// Set message content
		this.setBody(multipart, textMessage);
		// Set message attachments
		this.setAttachments(multipart, attachments);
		// Send the complete message parts
        message.setContent(multipart);
		// Send message
		Transport.send(message);
	}
}
