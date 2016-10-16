package com.fotoweb.app.service.photo;

import com.fotoweb.app.entity.PhotoEntity;
import com.fotoweb.app.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}


