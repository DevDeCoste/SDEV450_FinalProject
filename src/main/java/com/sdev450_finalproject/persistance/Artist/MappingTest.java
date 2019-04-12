package com.sdev450_finalproject.persistance.Artist;

/** 
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.sdev450_finalproject.persistance.Artist
 * @Date: Apr 11, 2019
 * @Subclass MappingTest Description: 
 */
//Imports

////Begin Subclass MappingTest
//@Entity
//@Table(name = "MappingTest")
//public class MappingTest implements Serializable {
//
//    @javax.persistence.Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//
//    private String id;
//    private String AlbumName;
//    private String artist_name;
//    private String[] Tracklist;
//    private String genre;
// 
//    @OneToOne(fetch = FetchType.LAZY) 
//    @JoinColumn(name = "artist_id") 
//    private ArtistEntity artist;
// 
//    // getters & setters
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getAlbumName() {
//        return AlbumName;
//    }
//
//    public void setAlbumName(String AlbumName) {
//        this.AlbumName = AlbumName;
//    }
//
//    public String getArtist() {
//        return artist_name;
//    }
//
//    public void setArtist(String Artist) {
//        this.artist_name = Artist;
//    }
//
//    public String[] getTracklist() {
//        return Tracklist;
//    }
//
//    public void setTracklist(String[] Tracklist) {
//        this.Tracklist = Tracklist;
//    }
//
//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
//
//    public void setArtist(ArtistEntity artist) {
//        this.artist = artist;
//    }
//
//} //End Subclass MappingTest