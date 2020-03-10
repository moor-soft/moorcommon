package moorcommon.services.impl;

import moorcommon.services.EmailSMSService;
import moorcommon.utils.EmailSMSData;
import moorcommon.utils.EmailSMSThread;
import org.springframework.stereotype.Component;


@Component
public class EmailSMSServiceImpl implements EmailSMSService {
    @Override
    public Boolean sendEmailAndSMS(EmailSMSData email, EmailSMSData sms,String url,Long applicationId) {
        try {
            Runnable r = new EmailSMSThread(email, sms,url,applicationId);
            new Thread(r).start();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

}
