package com.sdev450_finalproject.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
public interface TrackRepository extends CrudRepository<TrackEntity, Long> {
    Page<TrackEntity> findAll(Pageable pageable);

    ArrayList<TrackEntity> findTrackEntitiesByAlbumTitleContains(String AlbumName);
    
}
