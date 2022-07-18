package backend.duka_kuu.repos;

import backend.duka_kuu.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
