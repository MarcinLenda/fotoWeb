package com.fotoweb.app.service.user;

import com.fotoweb.app.entity.UserEntity;
import com.fotoweb.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Promar on 14.10.2016.
 */
@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public UserServiceImplementation(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void register(UserEntity newUserEntity) {
        newUserEntity.setPassword(bCryptPasswordEncoder.encode(newUserEntity.getPassword()));
        if (checkIfAdminExists()) {
            newUserEntity.setRole("USER");
        } else {
            newUserEntity.setRole("ADMIN");
        }
        userRepository.save(newUserEntity);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity findByEmail(String email) {return userRepository.findByEmail(email);}

    @Override
    public void editMyAccount(UserEntity newUserEntity, Principal principal) {
        UserEntity userEntity = userRepository.findOne(principal.getName());
        userEntity.setPassword(bCryptPasswordEncoder.encode(newUserEntity.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public void editUser(UserEntity newUserEntity) {
        UserEntity userEntity = userRepository.findOne(newUserEntity.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(newUserEntity.getPassword()));
        userEntity.setRole(newUserEntity.getRole());
        userRepository.save(userEntity);
    }

    @Override
    public void deleteMyAccount(String password, Principal principal) {
        UserEntity userEntity = userRepository.findOne(principal.getName());
        if (bCryptPasswordEncoder.matches(password, userEntity.getPassword())) {
            userRepository.delete(userEntity.getUsername());
            SecurityContextHolder.clearContext();
        }
    }

    @Override
    public void deleteUser(String username, Principal principal) {
          userRepository.delete(username);
          if(principal.getName().equals(username)) {
              SecurityContextHolder.clearContext();
           }
    }

    @Override
    public List<UserEntity> getAllUsers() {
        Iterator<UserEntity> iterator = userRepository.findAll().iterator();
        List<UserEntity> userEntities = new ArrayList<>();
        iterator.forEachRemaining(userEntities::add);
        return userEntities;

    }

    @Override
    public String getRoleOfLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.iterator().next().toString();
    }

    @Override
    public boolean checkIfPasswordMatchWithLoggedUserPassword(String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findOne(authentication.getName());
        if (bCryptPasswordEncoder.matches(password, userEntity.getPassword())) {
            return true;
        }
        return false;
    }


    private boolean checkIfAdminExists() {
        List<UserEntity> userEntityList = userRepository.findByRole("ADMIN");
        for (UserEntity userEntity : userEntityList) {
            if (userEntity.getRole().equals("ADMIN")) {
                return true;
            }
        }
        return false;
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//
//        if(userRepository.findByUsername("admin")==null) {
//
//            UserEntity user = new UserEntity("admin","mar@wp.pl","admin","admin","ADMIN");
//            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//            userRepository.save(user);
//        }
//    }
}
