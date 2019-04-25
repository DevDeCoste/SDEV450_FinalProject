package com.sdev450_finalproject.persistance.Artist;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
public interface ArtistRepository extends JpaRepository<ArtistEntity, String> {

    Page<ArtistEntity> findAll(Pageable pageable);

    ArtistEntity findByArtistNameEquals(String ArtistName);

    ArrayList<ArtistEntity> findAllByArtistName(String ArtistName);

    @Override
    void deleteAll();

    @Modifying
    @Transactional
    @Query(value="delete from ArtistEntity where artistName = ?1")
    void deleteByArtistName(String ArtistName);

    @Modifying
    @Transactional
    @Query(value="delete from AlbumEntity where albumName = ?1")
    void deleteByAlbumName(String AlbumName);
}




