package com.sdev450_finalproject.persistance;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Track")
public class TrackEntity {
    //Variables
    @Id
    private long id; //this is currently a string do we want it to be long?
    private String track_name;
    private String[] available_markets;
    private String href;
    private String type;
    private String uri;
    private int disc_number;
    private int duration_ms;
    private int track_number;
    private boolean explicit;
    //URLObj external_urls;
    //Artist [] artists;
    //Album album;

    /*id getter & setter*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id= id;
    }

    /*track_name getter & setter*/
    public String getTrack_name() {
        return track_name;
    }

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }

    /*available_market getter & setter*/
    public String[] getAvailable_markets() {
        return available_markets;
    }

    public void setAvailable_markets(String[] available_markets) {
        this.available_markets = available_markets;
    }

    /*disc_number getter & setter*/
    public int getDisc_number() {
        return disc_number;
    }

    public void setDisc_number(int disc_number) {
        this.disc_number = disc_number;
    }

    /*duration_ms getter & setter*/
    public int getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    /*track_number getter & setter*/
    public int getTrack_number() {
        return track_number;
    }

    public void setTrack_number(int track_number) {
        this.track_number = track_number;
    }

    /*explicit getter & setter*/
    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    /*href getter & setter*/
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    /*type getter & setter*/
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*uri getter & setter*/
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}