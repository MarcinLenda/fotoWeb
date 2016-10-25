package com.fotoweb.app.service.photo;

import com.fotoweb.app.entity.PhotoEntity;

import java.util.List;

/**
 * Created by promar7 on 17.07.16.
 */

public interface PhotoService {

    PhotoEntity findPhoto(PhotoEntity photoEntity);
    PhotoEntity findById(String id);
    List<PhotoEntity> findAll();
    void remove(PhotoEntity photoEntity);
    PhotoEntity create(PhotoEntity photoEntity);
    List<PhotoEntity> findByNameAlbum(String nameAlbum);
}


