package com.fotoweb.app.service.photo;

import com.fotoweb.app.entity.PhotoEntity;
import com.fotoweb.app.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Promar on 16.10.2016.
 */
@Service
public class PhotoServiceImplementation implements PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImplementation(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }


    @Override
    public PhotoEntity findPhoto(PhotoEntity photoEntity) {
        return photoRepository.findOne(photoEntity.getId());
    }

    @Override
    public PhotoEntity findById(String id) {
        return photoRepository.findOne(id);
    }

    @Override
    public List<PhotoEntity> findAll() {
        return photoRepository.findAll();
    }

    @Override
    public void remove(PhotoEntity photoEntity) {
        photoRepository.delete(photoEntity);
    }

    @Override
    public PhotoEntity create(PhotoEntity photoEntity) {
        return photoRepository.save(photoEntity);
    }

    @Override
    public List<PhotoEntity> findByNameAlbum(String nameAlbum) {
        return findByNameAlbum(nameAlbum);
    }
}
