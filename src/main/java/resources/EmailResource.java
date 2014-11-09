package resources;


import com.yammer.metrics.annotation.Timed;
import models.Email;
import rest.EmailResponse;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

@Path("/send-email")
@Produces(MediaType.APPLICATION_JSON)
public class EmailResource {
    private final AtomicLong counter;

    public EmailResource() {
        this.counter = new AtomicLong();
    }

    @POST
    @Timed
    public EmailResponse send(Email email) throws IOException {

        // Recipient's email ID needs to be mentioned.
        String to = "07009003@hope.ac.uk";//change accordingly

        // Sender's email ID needs to be mentioned
        String from = email.getEmail();//change accordingly
        final String username = "07009003@hope.ac.uk";//change accordingly
        final String password = "1qazxsw2";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(String.format("Message from %s - Tel: %s", email.getName(), email.getPhoneNumber()));
            message.setText(email.getMessage());

            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return new EmailResponse("success");
    }
}