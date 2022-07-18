package backend.duka_kuu.repos;

import backend.duka_kuu.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
