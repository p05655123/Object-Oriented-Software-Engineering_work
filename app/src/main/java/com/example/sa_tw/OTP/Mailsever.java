package com.example.sa_tw.OTP;



import android.os.AsyncTask;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailsever {

    public void sendEmail(String to, String otp) {
        SendMail sendMail = new SendMail();
        sendMail.execute(to,otp);

    }
    protected class SendMail extends AsyncTask<String,Void,Boolean>{


        private String to;
        private String from ="saautisticshop@gmail.com";
        private final String title="OTP";
        private String context ;
        private final String username = "saautisticshop@gmail.com";
        private final String password = "autisticshop";
        private String host = "smtp.gmail.com";
        private Properties props = new Properties();
        private Session session ;

        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected Boolean doInBackground(String... parmeter){
            context = "Your OTP code is " + parmeter[1]+ " 。";
            to=parmeter[0];
            props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "587");
            session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            try{
                MimeMessage mm = new MimeMessage(session);
                mm.setFrom(new InternetAddress(from));
                mm.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                mm.setSubject(title);
                mm.setText(context);
                Transport.send(mm);
            }catch (MessagingException e){
                return false;

            }
            return true;
        }
        protected void onPostExecute(Boolean result){
            super.onPostExecute(result);
        }
    }
}
