package com.fotoweb.app.controller;

import com.fotoweb.app.entity.UserEntity;
import com.fotoweb.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * Created by Promar on 14.10.2016.
 */
@RestController
@RequestMapping("/myAccount")
public class SecurityController {

    @Autowired
    private  UserService userService;



    @Resource
    Validator registerValidator;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String regestriation(@RequestBody UserEntity userEntity, BindingResult bindingResult) {
        registerValidator.validate(userEntity, bindingResult);
        if (bindingResult.hasErrors()) {
            return "Nie udało się zarejestrować użytkownika.";
        }
        userService.register(userEntity);
        return "Zarejestrowanao użytkownika.";
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {

        return user;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public UserEntity userFind(@RequestParam("username") String username){
        return userService.findByUsername(username);
    }
}
