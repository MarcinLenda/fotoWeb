package com.fotoweb.app.controller;

import com.fotoweb.app.entity.UserEntity;
import com.fotoweb.app.service.user.UserService;
import com.fotoweb.app.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public  UserService userService;
    @Resource
    private Validator validator;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> regestriation(@RequestBody UserEntity userEntity,
                                 BindingResult bindingResult) {
//        if(bindingResult.hasErrors()){
//            List<FieldError> errors = bindingResult.getFieldErrors();
//            errors.forEach(error-> response.put("chuja", "chuja dodano"));
//        }
//          us
//        System.out.println(response.toString());



        return validator.checkingData(userEntity);
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
