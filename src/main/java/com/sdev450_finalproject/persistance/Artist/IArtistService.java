/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdev450_finalproject.persistance.Artist;

import java.util.List;

/**
 *
 * @author Hard
 */
public interface IArtistService {

    public List<Artist> findAll();

    public Artist findById(Long id);
}
