package ir.dotin.core.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;
import java.util.Date;
import java.util.Properties;

public class MailUtils {

    public static boolean sendMail(String from, String to, String subject, String body) {
        try {
            InitialContext ic = new InitialContext();
            String snName = "java:/Mail";
            Session session = (Session) ic.lookup(snName);
            Properties props = session.getProperties();
            props.put("mail.from", from);
            Message msg = new MimeMessage(session);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setFrom();
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText(body);
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp);
            msg.setContent(mp);
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean receiveMail() {
        try {
            InitialContext ic = new InitialContext();
            String snName = "java:comp/env/mail/DefaultMail";
            Session session = (Session) ic.lookup(snName);
            Properties props = session.getProperties();
            props.put("mail.from", "user2@mailserver.com");
            Store store = session.getStore();
            store.connect("MailServer", "MailUser", "secret");
            Folder folder = store.getDefaultFolder();
            folder = folder.getFolder("INBOX");
            Message[] messages = folder.getMessages();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}