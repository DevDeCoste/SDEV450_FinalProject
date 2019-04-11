package com.sdev450_finalproject.persistance.Album;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;



@Entity
@Table(name = "Album")
public class AlbumEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private String id;
    private String AlbumName;
    private String Artist;
    private String Genre;

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
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


    public String getAlbumName() {
        return AlbumName;
    }

    public void setAlbumName(String albumName) {
        AlbumName = albumName;
    }


    @Override
    public String toString() {
        return "AlbumEntity [id=" + id + ", AlbumName=" + AlbumName + ", Artist=" + Artist
                + ", Genre=" + Genre + "]";
    }

} //End of Class