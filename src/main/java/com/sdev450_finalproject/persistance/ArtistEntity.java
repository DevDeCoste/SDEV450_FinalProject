package com.sdev450_finalproject.persistance;


//import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;


@Entity
@Table(name = "Artist")
public class ArtistEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String artistName;
    String albumTitle;
    String trackTitle;
    String genreType;
    String trackLength;
    String yearPublished;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public String getGenreType() {
        return genreType;
    }

    public void setGenreType(String genreType) {
        this.genreType = genreType;
    }

    public String getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(String trackLength) {
        this.trackLength = trackLength;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return "ArtistEntity [id=" + id + ", artistName=" + artistName + ", albumTitle=" + albumTitle + ", trackTitle="
                + trackTitle + ", genreType=" + genreType + ", trackLength=" + trackLength + ", yearPublished="
                + yearPublished + "]";
    }
}
