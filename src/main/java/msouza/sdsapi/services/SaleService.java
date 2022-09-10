package msouza.sdsapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import msouza.sdsapi.entities.Sale;
import msouza.sdsapi.repositories.SaleRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;
    
    public List<Sale> findSales() {
        return repository.findAll();
    }

}
