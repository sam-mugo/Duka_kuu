//package backend.duka_kuu.service;
//
//import backend.duka_kuu.domain.Role;
//import backend.duka_kuu.model.RoleDTO;
//import backend.duka_kuu.repos.RoleRepo;
//import java.util.List;
//import java.util.stream.Collectors;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//
//@Service
//public class RoleService {
//
//    private final RoleRepo roleRepository;
//
//    public RoleService(final RoleRepo roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    public List<RoleDTO> findAll() {
//        return roleRepository.findAll(Sort.by("id"))
//                .stream()
//                .map(role -> mapToDTO(role, new RoleDTO()))
//                .collect(Collectors.toList());
//    }
//
//    public RoleDTO get(final Long id) {
//        return roleRepository.findById(id)
//                .map(role -> mapToDTO(role, new RoleDTO()))
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }
//
//    public Long create(final RoleDTO roleDTO) {
//        final Role role = new Role();
//        mapToEntity(roleDTO, role);
//        return roleRepository.save(role).getId();
//    }
//
//    public void update(final Long id, final RoleDTO roleDTO) {
//        final Role role = roleRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        mapToEntity(roleDTO, role);
//        roleRepository.save(role);
//    }
//
//    public void delete(final Long id) {
//        roleRepository.deleteById(id);
//    }
//
//    private RoleDTO mapToDTO(final Role role, final RoleDTO roleDTO) {
//        roleDTO.setId(role.getId());
//        roleDTO.setName(role.getName());
//        return roleDTO;
//    }
//
//    private Role mapToEntity(final RoleDTO roleDTO, final Role role) {
//        role.setName(roleDTO.getName());
//        return role;
//    }
//
//}
