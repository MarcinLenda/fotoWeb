package com.fotoweb.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by promar7 on 16.07.16.
 */
@Document
public class PhotoEntity {

    @Id
    private String id;
    @NotNull
    @Size(min = 3, max = 15, message = "Minimalna nazwa zdjecia musi zawierać" +
            " min:3, maksymalna:15 znaków." )
    private String nameAlbum;
    @NotNull
    @Size(min = 3, max = 20, message = "Minimalny opis zdjęcia musi zawierać" +
            " minimum:3, maksimum:20 znaków.")
    private String description;

    @NotNull
    private String url;

    public PhotoEntity(){
    }

    public PhotoEntity(String nameAlbum, String description, String url) {

        this.nameAlbum = nameAlbum;
        this.description = description;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
