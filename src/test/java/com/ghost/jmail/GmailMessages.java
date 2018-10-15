package com.ghost.jmail;

import javax.mail.*;
import java.util.Properties;

public class GmailMessages{

    public static void main(String[] args) {
        String host = "imap.gmail.com";// change accordingly
        String mailStoreType = "imap";
        String username = "ghost.walking.developer@gmail.com";// change accordingly
        String password = "Arlekin616";// change accordingly

        check(host, mailStoreType, username, password);
    }
        public static void check(String host, String storeType, String user, String password)
        {
            try {

                //create properties field
                Properties properties = new Properties();

                properties.put("mail."+storeType+".host", host);
                properties.put("mail."+storeType+".port", "993");
                properties.put("mail."+storeType+".starttls.enable", "true");
                Session emailSession = Session.getDefaultInstance(properties);

                //create the POP3 store object and connect with the pop server
                Store store = emailSession.getStore(storeType+"s");

                store.connect(host, user, password);

                //create the folder object and open it
                Folder emailFolder = store.getFolder("Inbox");

                emailFolder.open(Folder.READ_ONLY);

                // retrieve the messages from the folder in an array and print it
                Message[] messages = emailFolder.getMessages();
                System.out.println("messages.length---" + messages.length);

                for (int i = 0, n = messages.length; i < n; i++) {
                    Message message = messages[i];


                    Object obj = message.getContent();
                    Multipart mp = (Multipart)obj;
                    BodyPart bp = mp.getBodyPart(0);


                    System.out.println("---------------------------------");
                    System.out.println("Email Number " + (i + 1));
                    System.out.println("Subject: " + message.getSubject());
                    System.out.println("From: " + message.getFrom()[0]);
                    System.out.println("To: " + message.getAllRecipients().toString());
                    System.out.println("Received Date:" + message.getReceivedDate());
                    System.out.println("Text: " + bp.getContent().toString());
                }

                //close the store and folder objects
                emailFolder.close(false);
                store.close();

            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
