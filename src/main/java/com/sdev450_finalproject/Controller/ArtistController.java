package com.sdev450_finalproject.Controller;


import com.opencsv.CSVReader;
import com.sdev450_finalproject.persistance.Album.AlbumEntity;
import com.sdev450_finalproject.persistance.Album.AlbumRepository;
import com.sdev450_finalproject.persistance.ArtistEntity;
import com.sdev450_finalproject.persistance.ArtistRepository;
import com.sdev450_finalproject.persistance.TrackEntity;
import com.sdev450_finalproject.persistance.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("")
public class ArtistController {

    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    TrackRepository trackRepository;

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

    @DeleteMapping("/delete")
    public void deleteAll() {
        artistRepository.deleteAll();
        albumRepository.deleteAll();
        trackRepository.deleteAll();
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
                tempArtist.setAlbumName(nextRecord[2]);
                tempArtist.setArtistName(nextRecord[3]);
                artistLists.add(tempArtist);
            }

        }

        csvReader.close();
        return artistLists;
    }

    @GetMapping("/findAllByArtist/{artistName}")
    public ResponseEntity findAllByArtist(@PathVariable("artistName") String artistName) {

        ArrayList<ArtistEntity> Entities = artistRepository.findAllByArtistName(artistName);
        if (Entities.isEmpty()) {
            // Returns 404 if not present
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        // Returns Album Entity if present
        return new ResponseEntity(Entities, HttpStatus.OK);

    }



    @PostMapping("/findAllByArtist/{artistName}")
    public  ArrayList<ArtistEntity> findAllbyArtist(@PathVariable("artistName") String searchArtist)
            throws IOException{

        String[] nextRecord;
        ArrayList<TrackEntity> trackLists = new ArrayList<>();
        ArrayList<ArtistEntity> artistLists = new ArrayList<>();

        ArtistEntity tempArtist = new ArtistEntity();
        Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));

        CSVReader csvReader = new CSVReader(reader);

        while ((nextRecord = csvReader.readNext()) != null) {
            TrackEntity tempTrack = new TrackEntity();

            if (nextRecord[2].toLowerCase().contains(searchArtist.toLowerCase())) {

                tempTrack.setAlbumTitle(nextRecord[2]);
                tempTrack.setArtistName(nextRecord[3]);
                tempTrack.setGenreType(nextRecord[4]);
                tempTrack.setTrackLength(nextRecord[6]);
                tempTrack.setTrackTitle(nextRecord[5]);
                tempTrack.setYearPublished(nextRecord[1]);

                trackLists.add(tempTrack);
            }

        }
        ArtistEntity entity = new ArtistEntity();
        List<ArtistEntity> entities = artistRepository.findAllByArtistName(trackLists.get(1).getArtistName());
        if (entities.size() == 1) {
            entity = entities.get(0);
        }

        tempArtist.setAlbumName(trackLists.get(1).getAlbumTitle());
        entity.setArtistName(trackLists.get(1).getArtistName());

        String track[] = new String[trackLists.size()];
        int j = 0;
        for (TrackEntity X : trackLists) {
            track[j] = X.getTrackTitle();
            j++;
        }

        tempArtist.setArtistAlbums(track);
        artistLists.add(tempArtist);
        artistRepository.save(entity);

//        if (albumRepository.findByAlbumNameEquals(tempAlbum.getAlbumName()) == null) {
//            albumRepository.save(tempAlbum);
//        }
        csvReader.close();
        return artistLists;
    }
//
//
//    @GetMapping("/foo")
//    public void foo() {
//        ArtistEntity entity = new ArtistEntity();
//        entity.setArtistName("test");
//        AlbumEntity album = new AlbumEntity();
//        album.setAlbumName("Test Album");
//        AlbumEntity album2 = new AlbumEntity();
//        album2.setAlbumName("Test Album 2");
//        entity.setAlbums(Arrays.asList(album, album2));
//        artistRepository.save(entity);
//
//        ArtistEntity foo = artistRepository.findAllByArtistName("test").get(0);
//        boolean t = false;
//    }


    @GetMapping("/findRandomArtist")
    public ArrayList<ArtistEntity> findRandomArtist() throws IOException {

        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
             CSVReader csvReader = new CSVReader(reader)) {

            String[] nextRecord;
            ArrayList<ArtistEntity> artistLists = new ArrayList<>();
            ArrayList<AlbumEntity> albums = new ArrayList<>();

            while ((nextRecord = csvReader.readNext()) != null) {
                ArtistEntity tempArtist = new ArtistEntity();

                AlbumEntity tempAlbum = new AlbumEntity();
                albums.toArray(tempAlbum.getAlbumTracks());
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
                    tempArtist.setArtistName(nextRecord[3]);
//                    tempArtist.setAlbumName(albums.addAll());
                    artistLists.add(tempArtist);
                    artistRepository.save(tempArtist);
                    csvReader1.close();
                }

            }

            return artistLists;
        }
    }

}
