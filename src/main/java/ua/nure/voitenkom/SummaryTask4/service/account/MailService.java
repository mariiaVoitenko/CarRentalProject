package ua.nure.voitenkom.SummaryTask4.service.account;

import ua.nure.voitenkom.SummaryTask4.util.Mappings;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class MailService {

    public static void sendEmail(String host, String port, String email, String user, String password, String token) throws MessagingException {
        Properties properties = configureProperties(host, port);
        Authenticator auth = getAuthenticator(user, password);
        String subject = "Registration confirmation";
        Message message = configureMessage(properties, auth, user, email, subject);
        message.setSentDate(new Date());
        message.setText("Hello, to finish your registration you should click this link: " + Mappings.HOST + Mappings.CONFIRMATION_MAPPING + "/" + token);

        Transport.send(message);
    }

    public static void sendEmailWithDocument(String host, String port, String email, String user, String password, String fileName) throws MessagingException {
        Properties properties = configureProperties(host, port);
        Authenticator auth = getAuthenticator(user, password);
        String subject = "Check Payment";
        Message message = configureMessage(properties, auth, user, email, subject);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("To finish your rental application process you have to pay this check in any bank and send us photo of the payed check back to this email");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        BodyPart data = new MimeBodyPart();
        DataSource source = new FileDataSource(fileName);
        data.setDataHandler(new DataHandler(source));
        data.setFileName(fileName);
        multipart.addBodyPart(data);
        message.setContent(multipart);

        Transport.send(message);
    }

    private static Properties configureProperties(String host, String port) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        return properties;
    }

    private static Authenticator getAuthenticator(final String user, final String password) {
        return new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        };
    }

    private static Message configureMessage(Properties properties, Authenticator auth, String user, String email, String subject) throws MessagingException {
        Session session = Session.getInstance(properties, auth);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        InternetAddress[] toAddresses = {new InternetAddress(email)};
        message.setRecipients(Message.RecipientType.TO, toAddresses);
        message.setSubject(subject);
        return message;
    }

}
