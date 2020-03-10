package moorcommon.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sistem on 29/03/2017.
 */
public class EmailSMSThread implements Runnable {
    private String url;
    private EmailSMSData email;
    private EmailSMSData sms;
    private Long applicationId;


    public EmailSMSThread(EmailSMSData email, EmailSMSData sms,String url,Long applicationId) {
        this.email = email;
        this.sms = sms;
        this.url = url;
        this.applicationId = applicationId;
    }

    public EmailSMSData getEmail() {
        return email;
    }

    public void setEmail(EmailSMSData email) {
        this.email = email;
    }

    public EmailSMSData getSms() {
        return sms;
    }

    public void setSms(EmailSMSData sms) {
        this.sms = sms;
    }

    public void run() {
        try {
            Long applicationId = this.applicationId;
            if (email != null) {
                sendEMail(applicationId, getEmail().getNickname(), getEmail().getTo(), getEmail().getSubject(), getEmail().getBody(), getEmail().getEmailId());
            }
            if (sms != null) {
                sendSMS(applicationId, getSms().getTo(), getSms().getBody(), getSms().getSmsId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void sendEMail(Long applicationId, String nickname, List<String> to, String subject, String msg, Long emailId) {
        String sendEmailURI = url;
        EmailSMSData emailSMSData = new EmailSMSData();
        emailSMSData.setEmailId(emailId);
        emailSMSData.setTo(to);
        emailSMSData.setNickname(nickname);
        emailSMSData.setBody(msg);
        emailSMSData.setSubject(subject);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type","application/json");
        requestHeaders.add("ApplicationId", String.valueOf(applicationId));

        HttpEntity<EmailSMSData> httpEntity = new HttpEntity<>(emailSMSData, requestHeaders);
        try {
            ResponseEntity<ResultObject> result = restTemplate.exchange(
                    sendEmailURI,
                    HttpMethod.POST,
                    httpEntity, ResultObject.class
            );
            if (result != null
                    && result.getBody() != null
                    && result.getBody().isSuccessful()) {
                HashMap<String, Object> data = (HashMap<String, Object>) result.getBody().getData();
                System.out.println("E-posta gönderildi");
            } else {
                System.out.println("E-posta gönderilirken hata oluştu => Subject : " + emailSMSData.getSubject() + " //// Body : " + emailSMSData.getBody());
            }
        } catch (Exception e) {
            System.out.println("E-posta gönderilirken hata oluştu => To : " + emailSMSData.getTo().toString() + " //// Body : " + emailSMSData.getBody() + " //// Error : " + e.getMessage());
        }
    }

    private void sendSMS(Long applicationId, List<String> to, String body, Long smsId) {
        String sendSMSURI = url;
        EmailSMSData emailSMSData = new EmailSMSData();
        emailSMSData.setTo(to);
        emailSMSData.setBody(body);
        emailSMSData.setSmsId(smsId);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("ApplicationId", String.valueOf(applicationId));

        HttpEntity<EmailSMSData> httpEntity = new HttpEntity<>(emailSMSData, requestHeaders);
        try {
            ResponseEntity<ResultObject> result = restTemplate.exchange(
                    sendSMSURI,
                    HttpMethod.POST,
                    httpEntity,
                    ResultObject.class
            );
            if (result != null
                    && result.getBody() != null
                    && result.getBody().isSuccessful()) {
                HashMap<String, Object> data = (HashMap<String, Object>) result.getBody().getData();
                System.out.println("SMS gönderildi");
            } else {
                System.out.println("SMS gönderilirken hata oluştu => To : " + emailSMSData.getTo().toString() + " //// Body : " + emailSMSData.getBody());
            }
        } catch (Exception e) {
            System.out.println("SMS gönderilirken hata oluştu => To : " + emailSMSData.getTo().toString() + " //// Body : " + emailSMSData.getBody() + " //// Error : " + e.getMessage());
        }
    }
}
