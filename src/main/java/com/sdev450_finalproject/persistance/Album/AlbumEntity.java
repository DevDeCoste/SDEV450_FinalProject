package com.sdev450_finalproject.persistance.Album;

import com.sdev450_finalproject.persistance.Track.TrackEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "Album")
public class AlbumEntity implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")


    private String id;
    private String albumName;
    private String Genre;


    @OneToMany(targetEntity = TrackEntity.class, cascade = CascadeType.ALL)
    private List<TrackEntity> tracks;

    public void addTrack(TrackEntity track) {
        if(tracks == null) {
            this.tracks = new ArrayList<>();
        }
        this.tracks.add(track);
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
        return "AlbumEntity [id=" + id + System.lineSeparator() +", Album Name=" + albumName + ", Genre=" + Genre +  "]";
    }




} //End of Class