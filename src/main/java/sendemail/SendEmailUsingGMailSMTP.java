package sendemail;

import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmailUsingGMailSMTP {
    private static final String username = "arianr@gmail.com";
    private static final String password = "wekultnunivldkwf";

    public static void sendEmail(String to, String subject, String htmlBody) {
        // Configura las propiedades del correo electrónico
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Obtén la sesión con autenticación
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Crea un nuevo mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            // Establece el contenido del mensaje como HTML
            message.setContent(htmlBody, "text/html; charset=utf-8");

            // Envía el mensaje
            Transport.send(message);

            System.out.println("Correo electrónico enviado con éxito");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
