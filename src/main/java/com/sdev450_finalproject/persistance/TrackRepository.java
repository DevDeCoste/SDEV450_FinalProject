package com.sdev450_finalproject.persistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TrackRepository extends CrudRepository<TrackEntity, Long> {
    Page<TrackEntity> findAll(Pageable pageable);

   
    
}
