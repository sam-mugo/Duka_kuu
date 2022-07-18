package backend.duka_kuu.repos;

import backend.duka_kuu.domain.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}
