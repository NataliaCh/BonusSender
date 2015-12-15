package rootPack;

import org.apache.commons.io.FileUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    static final String ENCODING = "UTF-8";

    public static void main(String[] args) throws Exception {
        for (int c = 0; c<readUsers().size(); c++) {
            sendMail(getMessage().get(c));
        }
    }

    public static List<MessageToUser> getMessage() throws Exception {
        List<MessageToUser> messages = new ArrayList<MessageToUser>();
        for (User each : readUsers()) {
            messages.add(new MessageToUser(each));
        }
        return messages;
    }

    private static List<User> readUsers() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");

        Connection c = DriverManager.getConnection("jdbc:xls:file:test.xlsx");
        ResultSet rs = c.createStatement().executeQuery("SELECT * FROM August_15");

        List<User> users = new ArrayList<User>();
        while (rs.next()) {
            String maybeEmail = rs.getString(1);
            if (maybeEmail != null && maybeEmail.trim().length() > 0) {
                users.add(new User(maybeEmail, rs, 22));
            }
        }
        return users;
    }

    private static void sendMail(MessageToUser each) throws Exception {
        File message = new File(".", each.getEmail() + ".html");
        FileUtils.writeStringToFile(message, each.createHtml());

        final String username = "gis-notifier@intetics.com";//"n.chernonog@intetics.com";
        final String password = "g1$0b0NU";//"everSucce$$";
        final String provider = "mail.intetics.com.ua";
        final String host = "mail.intetics.com.ua";
        final String port = "25";

        Properties props = new Properties();
        //props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host); // mail.intetics.com.ua
        props.put("mail.smtp.port", port);  // 143 / 993
        props.put("mail.mime.charset", ENCODING);
        //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "true");

        Authenticator auth = new MyAuthenticator(username, password);
        Session session = Session.getDefaultInstance(props, auth);

        String to = each.getEmail();
        String from = "n.chernonog@intetics.com";

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject("Bonus");
        msg.setContent(each.createHtml(), "text/html");

        Transport.send(msg);
    }
    private static class MyAuthenticator extends Authenticator {
        private String user;
        private String password;

        public MyAuthenticator(String user, String password) {
            this.user = user;
            this.password = password;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            String user = this.user;
            String password = this.password;
            return new PasswordAuthentication(user, password);
        }
    }
}
