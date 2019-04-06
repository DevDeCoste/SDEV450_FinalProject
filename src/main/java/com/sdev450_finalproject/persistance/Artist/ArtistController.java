package com.sdev450_finalproject.persistance.Artist;

/** 
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Madeline Merced
 * @Assignment Name: com.sdev450_finalproject.Controller
 * @Date: Apr 6, 2019
 * @Subclass ArtistController Description: 
 */
//Imports

import com.sdev450_finalproject.WriteCsvToResponse;
import com.sdev450_finalproject.persistance.Artist.Artist;
import com.sdev450_finalproject.persistance.Artist.IArtistService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Begin Subclass ArtistController
@RestController
@RequestMapping("")
public class ArtistController {
  
   @Autowired
    IArtistService ArtistService;

    @RequestMapping(value = "/artist", produces = "text/csv")
    public void findArtists(HttpServletResponse response) throws IOException {

        List<Artist> artists = (List<Artist>) ArtistService.findAll();

        WriteCsvToResponse.writeArtists(response.getWriter(), artists);
    }

    @RequestMapping(value = "/artist/{artistId}", produces = "text/csv")
    public void findCity(@PathVariable Long artistId, HttpServletResponse response) throws IOException {

        Artist artist = ArtistService.findById(artistId);
        WriteCsvToResponse.writeCity(response.getWriter(), artist);
    }
} //End Subclass ArtistController

