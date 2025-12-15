import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailClientImplementation implements EmailClient
{
    private final String username;
    private final String password;
    private final String smtpHost;
    private final int smtpPort;
    private final Session session;

    public EmailClientImplementation(String smtpHost, int smtpPort, String username, String password)
    {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.username = username;
        this.password = password;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        this.session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
    @Override
    public void send(String to, String subject, String body) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Email sent successfully to " + to);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }

    // Example usage
    public static void main(String[] args)
    {
        // Configure with your SMTP settings
        EmailClient client = new EmailClientImplementation(
                "smtp.gmail.com",
                587,
                "your-email@gmail.com",
                "your-app-password"
        );

        client.send(
                "recipient@example.com",
                "Test Email",
                "This is a test email body."
        );
    }
}