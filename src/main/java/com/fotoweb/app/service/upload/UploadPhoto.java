package com.fotoweb.app.service.upload;

import com.fotoweb.app.entity.PhotoEntity;
import com.fotoweb.app.repository.ThumbnailRepository;
import com.fotoweb.app.service.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Promar on 03.10.2016.
 */
@Service
public class UploadPhoto {

    @Autowired
    private PhotoService photoService;
    @Autowired
    private ThumbnailRepository thumbnailRepository;

    public ResponseEntity uploadPhoto(MultipartFile file, String nameAlbum, String description){

        if (!file.isEmpty()) {
            try {
                String mimeType = file.getContentType();
                String filename = file.getOriginalFilename();
                String path = "";
                byte[] bytes = file.getBytes();
                File serverFile = new File("/home/ubuntu/fotoweb/fotoWeb/src/main/resources/static/images" + "/"  + filename);




                path = "/images/"+filename;
                PhotoEntity photoEntity = new PhotoEntity(nameAlbum,description, path );
                photoService.create(photoEntity);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                return new ResponseEntity<>("{}", HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("{}", HttpStatus.OK);
        }
    }
}


