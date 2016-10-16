//package com.fotoweb.app.controller;
//
//import com.fotoweb.app.entity.UserEntity;
//import com.fotoweb.app.service.photo.PhotoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//import java.security.Principal;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * Created by Promar on 28.08.2016.
// */
//@RestController
//@RequestMapping(value = "/home")
//public class RestControllerUser {
//    private final PhotoService photoService;
//    private final UserService userService;
//    private final String admin="SuperAdmin";
//
//
//    @Autowired
//    public RestControllerUser(PhotoService photoService, UserService userService){
//        this.photoService = photoService;
//        this.userService = userService;
//    }
//
//
//    @CrossOrigin(origins = "*")
//    @RequestMapping(value = "/newUser", method = {RequestMethod.POST, RequestMethod.GET})
//    public String registerNewUser(@Valid @RequestBody UserEntity userEntity, HttpServletResponse httpServletResponse){
//        if(userService.findByLogin(userEntity.getLogin())==null) {
//            if(userEntity.getLogin().equals(admin)){
//                userEntity.setRole(true);
//                userService.create(userEntity);
//                return "Użytkownik dodany do bazy danych (ADMIN)";
//            }else{
//                userService.create(userEntity);
//                return "Użytkownik dodany do bazy danych (USER)";
//            }
//        }else
//        {
//            httpServletResponse.setStatus(409);
//            return "Podany login jest już zajęty!";
//        }
//    }
//
//
//
//
//
//    @RequestMapping("/user")
//    public Principal user(Principal user) {
//        return user;
//    }
//
//
//    @RequestMapping("/userRole")
//    public Map<String, Object> userRole(Principal user) {
//        Map<String, Object> map = new LinkedHashMap<String, Object>();
//        map.put("name", user.getName());
//        System.out.println(user.getName());
//        map.put("roles", AuthorityUtils.authorityListToSet(((Authentication) user)
//                .getAuthorities()));
//        return map;
//    }
//
//
//}
