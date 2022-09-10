package msouza.sdsapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import msouza.sdsapi.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    
}
