package com.sdev450_finalproject.persistance.Artist;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ARTISTS")
public class Artist implements Serializable{

    //Variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id; //this is currently a string do we want it to be long?
    
    private String artist_name;
//    private String genre;
//    private String href;
//    private String type;
//    private String uri;
    private int popularity;
    //URLObj external_urls;
    //FollowersObj followers;
    //ImgObj[] images;

    
    public Artist(){
        
    }
    
    public Artist(Long id, String name, int popularity){
        this.id = id;
        this.artist_name = name;
        this.popularity = popularity;
    }
    
    /*id getter & setter*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id= id;
    }

    /*artist_name getter & setter*/
    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

//    /*genre getter & setter*/
//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
//
//    /*href getter & setter*/
//    public String getHref() {
//        return href;
//    }
//
//    public void setHref(String href) {
//        this.href = href;
//    }
//
//    /*type getter & setter*/
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    /*uri getter & setter*/
//    public String getUri() {
//        return uri;
//    }
//
//    public void setUri(String uri) {
//        this.uri = uri;
//    }

    /*popularity getter & setter*/
    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
    @Override
    public String toString(){
        return "Artist{" + "id =" + id + "name = " + artist_name + "popularity = " + popularity + '}';
}
}
