package com.fotoweb.app.controller;

import com.fotoweb.app.entity.PhotoEntity;
import com.fotoweb.app.service.photo.PhotoService;
import com.fotoweb.app.service.upload.UploadPhoto;
import com.fotoweb.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by promar7 on 17.07.16.
 */
@RestController

@RequestMapping(value = "/photo")
public class ControllerPhoto {

    private final PhotoService photoService;
    private final UserService userService;
    private final UploadPhoto uploadPhoto;

    @Autowired
    public ControllerPhoto(PhotoService photoService, UserService userService, UploadPhoto uploadPhoto){
        this.photoService=photoService;
        this.userService = userService;
        this.uploadPhoto = uploadPhoto;
    }

    @CrossOrigin(origins = "http://52.39.52.69:8080")
    @RequestMapping(value = "/getPhoto", method = RequestMethod.POST)
    public PhotoEntity getPhoto(@RequestBody PhotoEntity photoEntity){
        return photoService.findPhoto(photoEntity);
    }

    @CrossOrigin(origins = "http://52.39.52.69:8080")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void postPhoto(@RequestBody PhotoEntity photoEntity, BindingResult bindingResult){
        photoService.create(photoEntity);
    }

    @CrossOrigin(origins = "http://52.39.52.69:8080")
    @RequestMapping(value = "/deletePhoto", method = RequestMethod.DELETE)
    public void deletePhoto(@RequestBody  Map<String, Boolean> ID) {

        if (ID != null) {
            for (Map.Entry<String, Boolean> entry : ID.entrySet()) {
                if (entry.getValue()) {
                    PhotoEntity byId = photoService.findById(entry.getKey());
                    photoService.remove(byId);
                }
            }
        }
    }

    @CrossOrigin(origins = "http://52.39.52.69:8080")
    @RequestMapping(value = "/allPhotos", method = RequestMethod.GET)
    public List<PhotoEntity> findAllPhotos(){
        return photoService.findAll();
    }

    @CrossOrigin(origins = "http://52.39.52.69:8080")
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody
    ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file,
                                    @RequestParam("name") String nameAlbum, @RequestParam("descritpion")String description){
        return uploadPhoto.uploadPhoto(file, nameAlbum, description);
    }

    @CrossOrigin(origins = "http://52.39.52.69:8080")
    @RequestMapping(value="/findByNameAlbum", method=RequestMethod.POST)
    public @ResponseBody
    List<PhotoEntity> findByNameAlbum(String nameAlbum){
        return photoService.findByNameAlbum(nameAlbum);
    }
}
