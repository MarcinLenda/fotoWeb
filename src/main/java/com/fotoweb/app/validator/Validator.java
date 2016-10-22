package com.fotoweb.app.validator;

import com.fotoweb.app.entity.UserEntity;
import com.fotoweb.app.service.user.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Promar on 22.10.2016.
 */
@Component
public class Validator {

    @Autowired
    private UserService userService;

    public ResponseEntity<?> checkingData(UserEntity userEntity) {

        List<String> response = new ArrayList<>();
        JSONObject errors = new JSONObject();
        JSONObject success = new JSONObject();


        if (userService.findByUsername(userEntity.getUsername()) != null) {
            response.clear();
            response.add("Podana nazwa jest zajęta.");
            errors.put("Error", response);
            return new ResponseEntity<Object>(errors, HttpStatus.CONFLICT);
        }
        if(!userEntity.getPassword().equals(userEntity.getConfirmPassword())){
            response.clear();
            response.add("Podane hasła się różnią.");
            errors.put("Error", response);
            return new ResponseEntity<Object>(errors, HttpStatus.CONFLICT);
        }
        if(userService.findByEmail(userEntity.getEmail()) != null){
            response.clear();
            response.add("Podany email istnieję już w bazie danych.");
            errors.put("Error", response);
            return new ResponseEntity<Object>(errors, HttpStatus.CONFLICT);
        }
        if(userEntity.getUsername().length()<5 || userEntity.getUsername().length() > 15){
            response.clear();
            response.add("Nazwa użytkownika musi zawierać: 5-15 znaków.");
            errors.put("Error", response);
            return new ResponseEntity<Object>(errors, HttpStatus.CONFLICT);
        }
        if(userEntity.getPassword().length()<5 || userEntity.getPassword().length() > 15){
            response.clear();
            response.add("Hasło musi zawierać 5-15 znaków.");
            errors.put("Error", response);
            return new ResponseEntity<Object>(errors, HttpStatus.CONFLICT);
        }
        if(userEntity.getUsername().equals(userEntity.getPassword())){
            response.clear();
            response.add("Nazwa użytkownika i hasło nie mogą być takie same.");
            errors.put("Error", response);
            return new ResponseEntity<Object>(errors, HttpStatus.CONFLICT);
        }
        if(userEntity.getUsername()==null){
            response.clear();
            response.add("Musisz podać nazwe użytkownika.");
            errors.put("Error", response);
            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        }
        if(userEntity.getPassword()==null){
            response.clear();
            response.add("Musisz podać hasło.");
            errors.put("Error", response);
            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        }
        if(userEntity.getEmail()==null){
            response.clear();
            response.add("Musisz podać email.");
            errors.put("Error", response);
            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        }

        userService.register(userEntity);
        response.clear();
        response.add("Właśnie stworzyłeś konto w serwisie Fotomaszewski.Gratulujemy!");
        success.put("Succes",response);
        return new ResponseEntity<Object>(success, HttpStatus.CREATED);
    }



}
