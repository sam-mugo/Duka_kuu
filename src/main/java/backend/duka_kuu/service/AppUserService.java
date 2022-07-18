package backend.duka_kuu.service;

import backend.duka_kuu.domain.Role;
import backend.duka_kuu.domain.AppUser;


import java.util.List;


public interface AppUserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);


    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser>getUsers();
}

