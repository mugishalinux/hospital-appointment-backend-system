package com.mugisha.hospital.mail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class MailService {


    public void sendTextEmail(String recieverName, String emailReciever, String appointMentStatus) throws IOException {
        // the sender email should be the same as we used to Create a Single Sender Verification
        Email from = new Email("jumpmantest79@gmail.com");
        String subject = "Appointment";
        Email to = new Email(emailReciever);
        Content content = new Content("text/plain", "Dear " + recieverName + " your appointment is " + appointMentStatus);
        Mail mail = new Mail(from, subject, to, content);
        String SENDGRID_API_KEY="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";
        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            response.getBody();
            System.out.println(response.getBody());
            System.out.println(response.getStatusCode());
        } catch (IOException ex) {
            throw ex;

        }


    }

}
