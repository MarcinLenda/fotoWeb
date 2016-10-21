package com.fotoweb.app.service.thumbnail;

import com.fotoweb.app.entity.ThumbnailEntity;
import com.fotoweb.app.repository.ThumbnailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Promar on 18.10.2016.
 */
@Service
public class ThumbnailServiceImplementation implements ThumbnailService{

    private final ThumbnailRepository thumbnailRepository;

    @Autowired
    public ThumbnailServiceImplementation(ThumbnailRepository thumbnailRepository) {
        this.thumbnailRepository = thumbnailRepository;
    }

    @Override
    public void create(ThumbnailEntity thumbnailEntity) {
        thumbnailRepository.save(thumbnailEntity);
    }
}
