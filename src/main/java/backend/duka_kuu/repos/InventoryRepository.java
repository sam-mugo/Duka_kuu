package backend.duka_kuu.repos;

import backend.duka_kuu.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
