package com.sdev450_finalproject.persistance.Artist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {
    Page<ArtistEntity> findAll(Pageable pageable);
}