package com.fotoweb.app.service.user;

import com.fotoweb.app.entity.UserEntity;

import java.security.Principal;
import java.util.List;

/**
 * Created by Promar on 14.10.2016.
 */
public interface UserService {

    void register(UserEntity userEntity);
    UserEntity findByUsername(String username);
    void editMyAccount(UserEntity newUserEntity, Principal principal);
    void editUser(UserEntity  newUserEntity);
    void deleteMyAccount(String password, Principal principal);
    void deleteUser(String username, Principal principal);
    List<UserEntity> getAllUsers();
    String getRoleOfLoggedUser();
    boolean checkIfPasswordMatchWithLoggedUserPassword(String password);
}
