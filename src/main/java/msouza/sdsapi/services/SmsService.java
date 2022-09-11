package msouza.sdsapi.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {
    
    // attributes
    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;
    
    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    // methods
    public Message sendSms() {
        Twilio.init(twilioSid, twilioKey);
        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);
        return Message.creator(to, from, "Teste").create();
    }

}
