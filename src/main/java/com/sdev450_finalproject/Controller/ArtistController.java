package com.sdev450_finalproject.Controller;


import com.opencsv.CSVReader;
import com.sdev450_finalproject.persistance.Album.AlbumEntity;
import com.sdev450_finalproject.persistance.ArtistEntity;
import com.sdev450_finalproject.persistance.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("")
public class ArtistController {

    @Autowired
    ArtistRepository artistRepository;

    static String FILE_PATH = "./src/main/resources/albumlist.csv";

    @GetMapping(path = "/artists")
    public List<ArtistEntity> getEntities() {
        return artistRepository.findAll();
    }

    @PostMapping(path = "/artists")
    public boolean createArtist(@RequestBody ArtistEntity artistEntity) {
        artistRepository.save(artistEntity);
        return true;
    }

    @GetMapping("/findArtistByAlbum/{findByAlbum}")
    public ArrayList<ArtistEntity> findArtistByAlbumName(@PathVariable("findByAlbum") String searchArtist) throws IOException {

        String[] nextRecord;
        ArrayList<ArtistEntity> artistLists = new ArrayList<>();
        Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));

        CSVReader csvReader = new CSVReader(reader);

        while ((nextRecord = csvReader.readNext()) != null) {

            ArtistEntity tempArtist = new ArtistEntity();

            if (nextRecord[2].toLowerCase().contains(searchArtist.toLowerCase())) {
                tempArtist.setId(Long.parseLong(nextRecord[0]));
                tempArtist.setAlbumTitle(nextRecord[2]);
                tempArtist.setArtistName(nextRecord[3]);
//                tempArtist.setGenreType(nextRecord[4]);
//                tempArtist.setTrackLength(nextRecord[6]);
//                tempArtist.setTrackTitle(nextRecord[5]);
//                tempArtist.setYearPublished(nextRecord[1]);

                artistLists.add(tempArtist);
            }


        }

        csvReader.close();
        return artistLists;
    }

    @GetMapping("/foo")
    public void foo() {
        ArtistEntity entity = new ArtistEntity();
        entity.setArtistName("test");
        AlbumEntity album = new AlbumEntity();
        album.setAlbumName("Test Album");
        AlbumEntity album2 = new AlbumEntity();
        album2.setAlbumName("Test Album 2");
        entity.setAlbums(Arrays.asList(album, album2));
        artistRepository.save(entity);

        ArtistEntity foo = artistRepository.findAllByArtistName("test").get(0);
        boolean t = false;
    }


    @GetMapping("/findRandomArtist")
    public ArrayList<ArtistEntity> findRandomArtist() throws IOException {

        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
             CSVReader csvReader = new CSVReader(reader)) {

            String[] nextRecord;
            ArrayList<ArtistEntity> artistLists = new ArrayList<>();
            while ((nextRecord = csvReader.readNext()) != null) {
                ArtistEntity tempArtist = new ArtistEntity();

                if (artistLists.isEmpty()) {
                    Reader reader1 = Files.newBufferedReader(Paths.get(FILE_PATH));
                    CSVReader csvReader1 = new CSVReader(reader1);

                    int i = 0;

                    int randInt = new Random().nextInt(100);

                    while (i <= randInt) {
                        nextRecord = csvReader1.readNext();
                        i = i + 2;
                    }
                    tempArtist.setId(Long.parseLong(nextRecord[0]));
//                    tempArtist.setAlbumTitle(nextRecord[2]);
                    tempArtist.setArtistName(nextRecord[3]);
//                    tempArtist.setTrackTitle(nextRecord[5]);
                    artistLists.add(tempArtist);
                    artistRepository.save(tempArtist);
                    csvReader1.close();
                }

            }

            return artistLists;
        }
    }

}
