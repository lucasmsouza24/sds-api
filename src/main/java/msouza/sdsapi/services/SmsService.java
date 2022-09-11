package msouza.sdsapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import msouza.sdsapi.entities.Sale;
import msouza.sdsapi.repositories.SaleRepository;

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

    @Autowired
    private SaleRepository saleRepository;

    // methods
    public Message sendSms(Long saleId) {
        String msg = getMessage(saleId);

        // twilio config
        Twilio.init(twilioSid, twilioKey);
        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

        // sending message
        return Message.creator(to, from, msg).create();
    }

    private String getMessage(Long saleId) {
        Sale sale = saleRepository.findById(saleId).get();
        String date = sale.getDate().getDayOfMonth() + "/" + sale.getDate().getYear();
        return String.format(
            "O vendedor %s foi destaque em %s com um total de R$ %.2f",
            sale.getSellerName(), 
            date,
            sale.getAmount());
    }

}
