package com.fotoweb.app.repository;

import com.fotoweb.app.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by promar7 on 23.07.16.
 */
@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    List<UserEntity> findByRole(String role);

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    UserEntity findByPassword(String password);

}
