package com.sdev450_finalproject.persistance.Artist;

import java.sql.Time;
import java.time.Duration;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Artist")

public class ArtistEntity {

    @Id
    private long id;

    String artist_name;
    String track_name;
    String popularity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getTrack_name() {
        return track_name;
    }

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "ArtistEntity [id= " + id + "artist_name= " + artist_name
                + ", track_name= " + track_name + "popularity= " + popularity+ "]";
    }

}
