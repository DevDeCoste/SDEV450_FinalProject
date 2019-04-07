package com.sdev450_finalproject.persistance.Album;

import javax.persistence.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.Arrays;



@Entity
@Table(name = "Album")
public class AlbumEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private String id;
    private String AlbumName;
    private String Artist;
    private String[] Tracklist;
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }





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


    @Override
    public String toString() {
        return "AlbumEntity [id=" + id + ", AlbumName=" + AlbumName + ", Artist=" + Artist
                + ", genre=" + genre + "]";
    }





} //End of Class