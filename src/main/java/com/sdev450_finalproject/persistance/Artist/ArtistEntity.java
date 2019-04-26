package com.sdev450_finalproject.persistance.Artist;


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


}
