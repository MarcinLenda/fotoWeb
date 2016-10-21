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


    @RequestMapping(value = "/getPhoto", method = RequestMethod.POST)
    public PhotoEntity getPhoto(@RequestBody PhotoEntity photoEntity){
        return photoService.findPhoto(photoEntity);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void postPhoto(@RequestBody PhotoEntity photoEntity, BindingResult bindingResult){
        photoService.create(photoEntity);
    }

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

    @RequestMapping(value = "/allPhotos", method = RequestMethod.GET)
    public List<PhotoEntity> findAllPhotos(){
        return photoService.findAll();
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody
    ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file,
                                    @RequestParam("name") String nameAlbum, @RequestParam("descritpion")String description){
        return uploadPhoto.uploadPhoto(file, nameAlbum, description);
    }

    @RequestMapping(value = "/uploadThumbnails", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity handleFileUploadThumbnail(@RequestParam("file") MultipartFile file,
                                                                  @RequestParam("name") String nameAlbum){
        return null;
    }
}
