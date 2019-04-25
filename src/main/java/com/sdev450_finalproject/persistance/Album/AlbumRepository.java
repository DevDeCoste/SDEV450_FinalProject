package com.sdev450_finalproject.persistance.Album;

import com.sdev450_finalproject.persistance.ArtistEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
public interface AlbumRepository extends JpaRepository<AlbumEntity, String> {

    Page<AlbumEntity> findAll(Pageable pageable);

    AlbumEntity findByAlbumNameEquals(String AlbumName);

    ArrayList<ArtistEntity> findAllByArtist(String ArtistName);
    
     


}