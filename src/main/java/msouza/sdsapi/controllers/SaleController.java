package msouza.sdsapi.controllers;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.twilio.rest.api.v2010.account.Message;
import msouza.sdsapi.entities.Sale;
import msouza.sdsapi.services.SaleService;
import msouza.sdsapi.services.SmsService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    // services
    @Autowired
    private SaleService service;

    @Autowired
    private SmsService smsService;

    // methods
    @GetMapping
    public Page<Sale> findSales(
            @RequestParam(value = "minDate", defaultValue = "") String minDate, 
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate, 
            Pageable pageable
        ) {
        return service.findSales(minDate, maxDate, pageable);
    }

    @PostMapping("/{id}/notification")
    public ResponseEntity<Message> notifySms(@PathVariable Long id) {
        return ResponseEntity.created(URI.create("/api/sales/notification")).body(smsService.sendSms(id));
    }

}
