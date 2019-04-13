package com.sdev450_finalproject.persistance.Artist;


//import com.opencsv.bean.CsvBindByName;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "Artist")
public class ArtistEntity implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    //Table Variables
    
//    @Column(name = "ID")
//    @CsvBindByName
    private String id;
    
//    @CsvBindByName(column = "artist")
    private String artist;
    
//    @CsvBindByName(column = "album")
    private String album;
    
//    @CsvBindByName(column = "genre")
    private String genre;
   
    /*********Entry Id**********/
    public String getArtistId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    /*******************Entry Artist************************/
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    
    /*****************Entry Artist's Album**********************/
    public String getAlbumName() {
        return album;
    }

    public void setAlbumName(String albumName) {
        this.album = albumName;
    }
    
    /********************Entry Genre*************************/
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    /********************Return Entry with Formatting******************/
     @Override
    public String toString() {
        return "AlbumEntity [id=" + id + ", Artist Name=" + artist +
                ", Album Name=" + artist
                + ", Genre=" + genre + "]";
    }
}
