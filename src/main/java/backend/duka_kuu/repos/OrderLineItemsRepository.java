package backend.duka_kuu.repos;

import backend.duka_kuu.domain.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderLineItemsRepository extends JpaRepository<OrderLineItems, Long> {
}
