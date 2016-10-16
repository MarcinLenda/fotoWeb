package com.fotoweb.app.service.security;

import com.fotoweb.app.entity.UserEntity;
import com.fotoweb.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Promar on 14.10.2016.
 */
@Service
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private  UserService userService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = getUserDetail(username);
        UserDetails userDetails = new User(userEntity.getUsername(), userEntity.getPassword(), getAuthorities(userEntity.getRole()));

        return userDetails;
    }

    public List<GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authList = new ArrayList<>();
        if (role.equalsIgnoreCase("ADMIN")) {
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        } else if (role.equalsIgnoreCase("USER")) {
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authList;
    }

    public UserEntity getUserDetail(String username) {
        UserEntity userEntity = userService.findByUsername(username);
        return userEntity;
    }
}
