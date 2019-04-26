package com.sdev450_finalproject.persistance.Track;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sdev450_finalproject.persistance.Album.AlbumEntity;

import java.util.ArrayList;

@Transactional
public interface TrackRepository extends JpaRepository<TrackEntity, String> {
    Page<TrackEntity> findAll(Pageable pageable);

    ArrayList<TrackEntity> findAllByTrackTitle(String TrackTitle);

    ArrayList<TrackEntity> findByTrackTitleEquals(String TrackTitle);

    //Page<AlbumEntity> findAll(Pageable pageable);

}
