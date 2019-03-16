package com.sdev450_finalproject.persistance;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Album")
public class AlbumEntity {

    @Id
    private String id;  //trinh -> possibly convert this to a long ?
    private String AlbumName;
    private String Artist;
    private String Tracklist[];



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }


    public String[] getTracklist() {
        return Tracklist;
    }

    public void setTracklist(String[] tracklist) {
        Tracklist = tracklist;
    }

    public String getAlbumName() {
        return AlbumName;
    }

    public void setAlbumName(String albumName) {
        AlbumName = albumName;
    }





}