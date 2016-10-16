package com.fotoweb.app.validator.user;

import com.fotoweb.app.entity.UserEntity;
import com.fotoweb.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Promar on 14.10.2016.
 */
public class RegisterValidator implements Validator {

    private final UserService userService;

    @Autowired
    public RegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserEntity userEntity = (UserEntity) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (userEntity.getUsername().length() < 5|| userEntity.getUsername().length() > 10) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(userEntity.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (userEntity.getPassword().length() < 5 || userEntity.getPassword().length() > 12) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        if (!userEntity.getConfirmPassword().equals(userEntity.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
