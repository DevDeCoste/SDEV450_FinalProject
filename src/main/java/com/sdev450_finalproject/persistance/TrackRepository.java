package com.sdev450_finalproject.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TrackRepository extends CrudRepository<TrackEntity, String> {
    Page<TrackEntity> findAll(Pageable pageable);

//    ArrayList<TrackEntity> findTrackEntitiesByAlbumTitleContains(String AlbumName);

}
