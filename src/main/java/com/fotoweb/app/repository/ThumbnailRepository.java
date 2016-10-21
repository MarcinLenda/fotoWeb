package com.fotoweb.app.repository;

import com.fotoweb.app.entity.ThumbnailEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Promar on 18.10.2016.
 */
@Repository
public interface ThumbnailRepository extends MongoRepository<ThumbnailEntity, String> {
}
