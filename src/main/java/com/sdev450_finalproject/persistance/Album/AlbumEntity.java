package com.sdev450_finalproject.persistance.Album;

import org.hibernate.annotations.GenericGenerator;

import com.sdev450_finalproject.persistance.TrackEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;



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
    @Column(length = 4096)
    private String[] albumTracks;

    private String trackLength;
    
    //TRINH TRIES FOR MAPPING
    
    @OneToMany(mappedBy="albumName",  cascade= CascadeType.ALL) //mappedBy="albumentity",
    private List<TrackEntity> tracks;
    
    private long album_id;






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


    public List<TrackEntity> getTracks() {
		return tracks;
	}

	public void setTracks(List<TrackEntity> tracks) {
		this.tracks = tracks;
	}

	@Override
    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("AlbumEntity").append(System.getProperty("line.separator"));
//        sb.append("id = " + id).append(System.getProperty("line.separator"));
//        sb.append("Album Name =" + albumName);
//        return sb.toString();

        return "AlbumEntity [id=" + id + System.lineSeparator() +", Album Name=" + albumName + ", artist=" + artist
                + ", Genre=" + Genre + "Tracks: " + albumTracks + "]";
    }

    public long getAlbum_id() {
		return album_id;
	}

	public void setAlbum_id(long album_id) {
		this.album_id = album_id;
	}

	public String TracklisttoString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("TrackEntity").append(System.getProperty("line.separator"));
//        sb.append("Title = " + id).append(System.getProperty("line.separator"));
//        sb.append("TrackLength =" + trackLength);
//        return sb.toString();
        return "TrackEntity [Title=" +  ", trackLength=" + trackLength +  "]";
    }

} //End of Class