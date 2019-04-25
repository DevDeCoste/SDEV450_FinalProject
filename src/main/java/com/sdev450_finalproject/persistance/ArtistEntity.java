package com.sdev450_finalproject.persistance;


//import com.opencsv.bean.CsvBindByName;

import com.sdev450_finalproject.persistance.Album.AlbumEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Artist")
public class ArtistEntity{

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    String artistName;
    String albumName;
    String[] artistAlbums;
    String[] artistSongs;

    public String[] getArtistSongs() {
        return artistSongs;
    }

    public void setArtistSongs(String[] artistSongs) {
        this.artistSongs = artistSongs;
    }



    public List<AlbumEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumEntity> albums) {
        this.albums = albums;
    }



    @OneToMany(targetEntity = AlbumEntity.class, cascade = CascadeType.ALL)
    private List<AlbumEntity> albums;

    public void addAlbum(AlbumEntity album) {
        if(albums == null) {
            this.albums = new ArrayList<>();
        }
        this.albums.add(album);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String[] getArtistAlbums() {
        return artistAlbums;
    }

    public void setArtistAlbums(String[] artistAlbums) {
        this.artistAlbums = artistAlbums;
    }



//    public String getTrackTitle() {
//        return trackTitle;
//    }
//
//    public void setTrackTitle(String trackTitle) {
//        this.trackTitle = trackTitle;
//    }
//
//    public String getGenreType() {
//        return genreType;
//    }
//
//    public void setGenreType(String genreType) {
//        this.genreType = genreType;
//    }
//
//    public String getTrackLength() {
//        return trackLength;
//    }
//
//    public void setTrackLength(String trackLength) {
//        this.trackLength = trackLength;
//    }
//
//    public String getYearPublished() {
//        return yearPublished;
//    }
//
//    public void setYearPublished(String yearPublished) {
//        this.yearPublished = yearPublished;
//    }

//    @Override
//    public String toString() {
//        return "ArtistEntity [id=" + id + ", artistName=" + artistName + "]";
//    }
}
