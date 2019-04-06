package com.sdev450_finalproject.persistance.Artist;



/** 
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.sdev450_finalproject.persistance
 * @Date: Apr 6, 2019
 * @Subclass ArtistService Description: 
 */
//Imports
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdev450_finalproject.persistance.Artist.ArtistRepository;
import com.sdev450_finalproject.persistance.Artist.Artist;

@Service
//Begin Subclass ArtistService
public class ArtistService implements IArtistService {

    @Autowired
    private ArtistRepository repository;

    @Override
    public List<Artist> findAll() {

        List<Artist> artists = (List<Artist>) repository.findAll();
        
        return artists;
    }


    @Override
    public Artist findById(Long id) {
        Artist artist = repository.findById(id).get();
        return artist;
    }
}

 //End Subclass ArtistService