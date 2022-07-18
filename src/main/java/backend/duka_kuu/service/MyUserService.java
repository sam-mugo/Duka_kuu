//package backend.duka_kuu.service;
//
//
//import backend.duka_kuu.domain.AppUser;
//import backend.duka_kuu.domain.Role;
//import backend.duka_kuu.model.AppUserDTO;
//import backend.duka_kuu.repos.AppUserRepository;
//import backend.duka_kuu.repos.RoleRepo;
//import backend.duka_kuu.repos.RoleRepository;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//import javax.transaction.Transactional;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//
//@Transactional
//@Service
//public class MyUserService {
//
//    private final AppUserRepository appUserRepository;
//    private final RoleRepo roleRepo;
//
//    public MyUserService(final AppUserRepository appUserRepository,
//                          final RoleRepo roleRepository) {
//        this.appUserRepository = appUserRepository;
//        this.roleRepo = roleRepository;
//    }
//
//    public List<AppUserDTO> findAll() {
//        return appUserRepository.findAll(Sort.by("id"))
//                .stream()
//                .map(appUser -> mapToDTO(appUser, new AppUserDTO()))
//                .collect(Collectors.toList());
//    }
//
//    public AppUserDTO get(final Long id) {
//        return appUserRepository.findById(id)
//                .map(appUser -> mapToDTO(appUser, new AppUserDTO()))
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }
//
//    public Long create(final AppUserDTO appUserDTO) {
//        final AppUser appUser = new AppUser();
//        mapToEntity(appUserDTO, appUser);
//        return appUserRepository.save(appUser).getId();
//    }
//
//    public void update(final Long id, final AppUserDTO appUserDTO) {
//        final AppUser appUser = appUserRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        mapToEntity(appUserDTO, appUser);
//        appUserRepository.save(appUser);
//    }
//
//    public void delete(final Long id) {
//        appUserRepository.deleteById(id);
//    }
//
//    private AppUserDTO mapToDTO(final AppUser appUser, final AppUserDTO appUserDTO) {
//        appUserDTO.setId(appUser.getId());
//        appUserDTO.setUserName(appUser.getUserName());
//        appUserDTO.setEmail(appUser.getEmail());
//        appUserDTO.setPassword(appUser.getPassword());
//        appUserDTO.setUsers(appUser.getUserRoles() == null ? null : appUser.getUserRoles().stream()
//                .map(role -> role.getId())
//                .collect(Collectors.toList()));
//        return appUserDTO;
//    }
//
//    private AppUser mapToEntity(final AppUserDTO appUserDTO, final AppUser appUser) {
//        appUser.setUserName(appUserDTO.getUserName());
//        appUser.setEmail(appUserDTO.getEmail());
//        appUser.setPassword(appUserDTO.getPassword());
//        final List<Role> users = roleRepository.findAllById(
//                appUserDTO.getUsers() == null ? Collections.emptyList() : appUserDTO.getUsers());
//        if (users.size() != (appUserDTO.getUsers() == null ? 0 : appUserDTO.getUsers().size())) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of users not found");
//        }
//        appUser.setUserRoles(roleRepo.findByName("ROLE_USER"));
//        return appUser;
//    }
//
//    public boolean emailExists(final String email) {
//        return appUserRepository.existsByEmailIgnoreCase(email);
//    }
//
//    public boolean passwordExists(final String password) {
//        return appUserRepository.existsByPasswordIgnoreCase(password);
//    }
//
//}