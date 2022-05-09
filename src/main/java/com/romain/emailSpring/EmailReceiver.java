package com.romain.emailSpring;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailReceiver {

    public static Properties getProperties(String pop3Host, String pop3Username, String pop3Password) {
        Properties properties = new Properties();
        properties.put("mail.pop3.host", pop3Host);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");
        properties.put("mail.pop3.auth.user", pop3Username);
        properties.put("mail.pop3.auth.password", pop3Password);
        return properties;
    }

    public static Session getSessionFromProps(Properties props) {
        return Session.getInstance(props);
    }

    public List<String> getTenFirstEmailSubject(String pop3Host, String storeType, String pop3Username, String pop3Password) {
        Properties props = getProperties(pop3Host, pop3Username, pop3Password);

        Session session = getSessionFromProps(props);

        try {
            Store mailStore = session.getStore(storeType);
            mailStore.connect(pop3Host, pop3Username, pop3Password);

            Folder folder =  mailStore.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            Message[] messages = folder.getMessages();
            List<Message> tenFirstMails = new ArrayList<>();
            for (int i = 0; i < messages.length ; i++) {
                Message message = messages[i];
                tenFirstMails.add(message);
            }
            List<String> tenFirstSubjects = new ArrayList<>();
            for (Message tenFirstMail : tenFirstMails) {
                tenFirstSubjects.add(tenFirstMail.getSubject());
            }

            folder.close(false);
            mailStore.close();
            return tenFirstSubjects;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }



}
