package com.fotoweb.app.entity;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Created by promar7 on 14.07.16.
 */
@Document
public class UserEntity {

    @Id
    private String ID;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Transient
    private String confirmPassword;

    @NotNull()
    @Email
    private String email;

    private String role;

    public UserEntity(String username, String email, String password,String confirmPassword, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
    }

    public UserEntity() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getID() {return ID;}

    public void setID(String ID) {this.ID = ID;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;

        UserEntity that = (UserEntity) o;

        if (getID() != null ? !getID().equals(that.getID()) : that.getID() != null) return false;
        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
            return false;
        if (getConfirmPassword() != null ? !getConfirmPassword().equals(that.getConfirmPassword()) : that.getConfirmPassword() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
        return getRole() != null ? getRole().equals(that.getRole()) : that.getRole() == null;

    }

    @Override
    public int hashCode() {
        int result = getID() != null ? getID().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getConfirmPassword() != null ? getConfirmPassword().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }
}
