package ua.nure.voitenkom.SummaryTask4.service.account;

import ua.nure.voitenkom.SummaryTask4.Mappings;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailService {

    private String host;
    private String port;

    public MailService(String port, String host) {
        this.port = port;
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void sendEmail(String email, final String user, final String pass, String token) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", this.getHost());
        properties.put("mail.smtp.port", this.getPort());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        };

        Session session = Session.getInstance(properties, auth);
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(user));
        InternetAddress[] toAddresses = {new InternetAddress(email)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject("Registration confirmation");
        msg.setSentDate(new Date());
        msg.setText("Hello, to finish your registration you should click this link: " + Mappings.HOST + Mappings.CONFIRMATION_MAPPING + "/" + token);

        Transport.send(msg);
    }
}
