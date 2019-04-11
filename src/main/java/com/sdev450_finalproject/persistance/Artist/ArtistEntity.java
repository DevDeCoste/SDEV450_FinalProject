package com.sdev450_finalproject.persistance.Artist;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Artist")
public class ArtistEntity implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
   
    //Table Variables
    private String artist_id;
    private String artist_name;
    private String album_name;
    private String[] tracks;
    private String genre;

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user") 
//    private MappingTest mTAlbums;
//    
    
    /*********Entry Id**********/
    public String getArtistId() {
        return artist_id;
    }

    public void setId(String artist_id) {
        this.artist_id = artist_id;
    }

    
    /*******************Entry Artist************************/
    public String getArtistName() {
        return artist_name;
    }

    public void setArtistName(String artist_name) {
        this.artist_name = artist_name;
    }
    
    
    /*****************Entry Artist's Album**********************/
    public String getAlbumName() {
        return album_name;
    }

    public void setAlbumName(String album_name) {
        this.album_name = album_name;
    }
    
    
    /************Entry Artist's Albums Tracks***************/
    public String[] getTracks() {
        return tracks;
    }

    public void setTracks(String[] tracks) {
        this.tracks = tracks;
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
        return "ArtistEntity [id= " + artist_id + "artist_name= " + artist_name
                + ", track_name= " + tracks;
    }
}
