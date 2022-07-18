package backend.duka_kuu.repos;

import backend.duka_kuu.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
