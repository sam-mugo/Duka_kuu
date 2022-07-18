package backend.duka_kuu;

import backend.duka_kuu.domain.AppUser;
import backend.duka_kuu.domain.Role;
import backend.duka_kuu.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;


@SpringBootApplication
public class DukaKuuApplication {

    public static void main(String[] args) {
        SpringApplication.run(DukaKuuApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(AppUserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));


//            userService.saveUser(new AppUser(null, "mugo", "1234", new ArrayList<>()));
//            userService.saveUser(new AppUser(null, "sam", "1234", new ArrayList<>()));
            userService.saveUser(new AppUser(null, "admin", "1234", new ArrayList<>()));


//            userService.addRoleToUser("mugo", "ROLE_USER");
//            userService.addRoleToUser("sam", "ROLE_ADMIN");
            userService.addRoleToUser("admin", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("admin", "ROLE_ADMIN");
            userService.addRoleToUser("admin", "ROLE_USER");
        };
    }
}
