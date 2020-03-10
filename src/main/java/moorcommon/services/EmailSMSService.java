package moorcommon.services;


import moorcommon.utils.EmailSMSData;

public interface EmailSMSService {
    Boolean sendEmailAndSMS(EmailSMSData email, EmailSMSData sms,String url,Long applicationId);
}
