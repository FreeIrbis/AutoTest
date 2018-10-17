package com.ghost.jmail;

import com.beust.jcommander.Parameter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.mail.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Properties;

@Parameters("domenName")
public class GmailMessages{
    public static void main(String[] args) {
        String host = "imap.gmail.com";// change accordingly
        String mailStoreType = "imap";
        String username = "ghost.walking.developer@gmail.com";// change accordingly
        String password = "Arlekin616";// change accordingly
        String domenName = "https://localhost:8080/registration/confirm?token";
        check(host, mailStoreType, username, password, domenName);
    }
        public static void check(String host, String storeType, String user, String password, String domenName)
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
                String confirmLink = null;
                // retrieve the messages from the folder in an array and print it
                Message[] messages = emailFolder.getMessages();
                System.out.println("messages.length---" + messages.length);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                String resultMail=null;
                for (int i = 0, n = messages.length; i < n; i++) {
                    Message message = messages[i];
                    Object obj = message.getContent();
                    BodyPart bp = null;
                    if (obj instanceof String)
                    {
                        Multipart mp = (Multipart)obj;
                        bp = mp.getBodyPart(0);
                        resultMail=bp.getContent().toString();
                    }
                    else if (obj instanceof Multipart)
                    {
                        Multipart multipart = (Multipart) obj;
                        multipart.writeTo(os);
                        String finalString = new String(os.toString());
                        resultMail=finalString;
                    }

                    System.out.println("---------------------------------");
                    System.out.println("Email Number " + (i + 1));
                    System.out.println("Subject: " + message.getSubject());
                    System.out.println("From: " + message.getFrom()[0]);
                    System.out.println("To: " + message.getAllRecipients().toString());
                    System.out.println("Received Date:" + message.getReceivedDate());
                    System.out.println("Text: " + resultMail );
                    if (message.getFrom()[0].toString().equals("darklordofmordor8@gmail.com")){
                        int indexFirst = resultMail.indexOf(domenName);
                        int indexLast = indexFirst+domenName.length()+42;
                        confirmLink = resultMail.substring(indexFirst,indexLast);
                       /* confirmLink.replace("3D","");
                        indexLast = confirmLink.lastIndexOf("=");
                        confirmLink=confirmLink.substring(0,indexLast).concat(confirmLink.substring(indexLast+3));*/

                        System.out.println("---------------"+confirmLink+" ------------------");
                    }
                    resultMail=null;
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
