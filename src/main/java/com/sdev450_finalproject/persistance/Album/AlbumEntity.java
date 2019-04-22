package com.sdev450_finalproject.persistance.Album;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;



@Entity
@Table(name = "Album")
public class AlbumEntity implements Serializable {

    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")


    private String id;
    private String albumName;
    private String artist;
    private String Genre;
    
    //Trinh: changed albumTracks to array 
    //Trinh: also changed getter and setters. Original albumTracks is a String. 
    private String[] albumTracks;

    private String trackLength;






    public String[] getAlbumTracks() {
        return albumTracks;
    }

    public void setAlbumTracks(String[] albumTracks) {
        this.albumTracks = albumTracks;
    }



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
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }


    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }


    @Override
    public String toString() {
        return "AlbumEntity [id=" + id + ", Album Name=" + albumName + ", artist=" + artist
                + ", Genre=" + Genre + "Tracks: " + albumTracks + "]";
    }

    public String TracklisttoString() {
        return "TrackEntity [Title=" +  ", trackLength=" + trackLength +  "]";
    }

} //End of Class