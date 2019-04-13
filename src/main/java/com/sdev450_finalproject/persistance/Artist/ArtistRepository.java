/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdev450_finalproject.persistance.Artist;
import java.util.ArrayList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Hard
 */
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {
    Page<ArtistEntity> findAll(Pageable pageable);
   ArrayList<ArtistEntity>findByArtistEquals(String artist);
}
