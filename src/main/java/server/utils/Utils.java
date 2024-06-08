package server.utils;

import server.elements.interfaces.Documents;
import server.environment.Environment;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Utils {
    final static int ADULT_AGE = 16;

    public static boolean isAdult(Date dateOfThePerson) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -ADULT_AGE);
        Date adultAgeDate = cal.getTime();

        return dateOfThePerson.before(adultAgeDate);
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean setDefaultMail(Documents documents) {
        final String fromEmail = Environment.FROM_EMAIL;
        final String password = Environment.PASSWORD; // correct password for gmail id
        final String toEmail = Environment.TO_Email;

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", Environment.TLS_PORT); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        try {
            Utils.sendEmail(session, toEmail,"BrettSoft (mail test)", "Bonsoir \n, le document : " + documents.toString() + "\nest disponible.");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
