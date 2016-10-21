package com.fotoweb.app.controller;

import com.fotoweb.app.entity.UserEntity;
import com.fotoweb.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Promar on 14.10.2016.
 */
@RestController
@RequestMapping("/myAccount")
public class SecurityController {

    @Autowired
    private  UserService userService;
    private final Map<String, Object> response = new LinkedHashMap<>();

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Map<String, Object> regestriation(@RequestBody UserEntity userEntity,
                                                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(error-> response.put("chuja", "chuja dodano"));
        }else{

            userService.register(userEntity);
            response.put("Succes","Dodano użytkownika do bazdy danych!");
        }
        System.out.println(response.toString());
        return response;
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
