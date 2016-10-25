package com.fotoweb.app.repository;

import com.fotoweb.app.entity.PhotoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by promar7 on 17.07.16.
 */
@Repository
public interface PhotoRepository extends MongoRepository<PhotoEntity,String> {

    PhotoEntity findOne(String id);

    PhotoEntity findById(String Id);

    List<PhotoEntity> findByNameAlbum(String nameAlbum);


}
