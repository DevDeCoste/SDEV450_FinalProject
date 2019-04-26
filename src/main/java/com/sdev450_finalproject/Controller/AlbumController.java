package com.sdev450_finalproject.Controller;

import com.opencsv.CSVReader;
import com.sdev450_finalproject.persistance.Album.AlbumEntity;
import com.sdev450_finalproject.persistance.Album.AlbumRepository;
import com.sdev450_finalproject.persistance.Artist.ArtistEntity;
import com.sdev450_finalproject.persistance.Artist.ArtistRepository;
import com.sdev450_finalproject.persistance.Track.TrackEntity;
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
public class AlbumController {

    static String FILE_PATH = "./src/main/resources/albumlist.csv";

    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    ArtistRepository artistRepository;

    @GetMapping(path = "/albums")
    public List<String> getEntities() {
        ArrayList<String> albumNames = new ArrayList<>();
        List<AlbumEntity> albums = albumRepository.findAll();
        for (AlbumEntity album : albums) {
            albumNames.add(album.getAlbumName());
        }
        return albumNames;
    }

    @PostMapping(path = "/albums")
    public boolean createAlbum(@RequestBody AlbumEntity albumEntity) {
        albumRepository.save(albumEntity);
        return true;
    }


    @PostMapping("/findTracksInAlbum/{findByAlbumName}")
    public ArtistEntity findTrackbyAlbumName(@PathVariable("findByAlbumName") String searchTrack)
            throws IOException {

        String[] nextRecord;
        String albumTitle = "";
        String artistName = "";
        ArrayList<TrackEntity> trackLists = new ArrayList<>();
        ArrayList<AlbumEntity> albumLists = new ArrayList<>();

        AlbumEntity tempAlbum = new AlbumEntity();
        Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));

        CSVReader csvReader = new CSVReader(reader);

        while ((nextRecord = csvReader.readNext()) != null) {
            TrackEntity tempTrack = new TrackEntity();

            if (nextRecord[2].toLowerCase().contains(searchTrack.toLowerCase())) {

                albumTitle = nextRecord[2];
                artistName = nextRecord[3];
                tempTrack.setGenreType(nextRecord[4]);
                tempTrack.setTrackLength(nextRecord[6]);
                tempTrack.setTrackTitle(nextRecord[5]);
                tempTrack.setYearPublished(nextRecord[1]);

                trackLists.add(tempTrack);
            }else{
                tempTrack.setTrackTitle("null");
            }

        }
        ArtistEntity entity = new ArtistEntity();
        List<ArtistEntity> entities = artistRepository.findAllByArtistName(artistName);
        if (entities.size() == 1) {
            entity = entities.get(0);
        }

        tempAlbum.setAlbumName(albumTitle);
        entity.setArtistName(artistName);
        tempAlbum.setGenre(trackLists.get(1).getGenreType());


        for (TrackEntity X : trackLists) {
            tempAlbum.addTrack(X);
        }


        albumLists.add(tempAlbum);
        entity.addAlbum(tempAlbum);

        if(albumRepository.findByAlbumNameEquals(tempAlbum.getAlbumName()) == null) {
            artistRepository.save(entity);
        }

        csvReader.close();
        return entity;

    }



    @GetMapping("/findRandomAlbum")
    public ArrayList<AlbumEntity> findRandomAlbum() throws IOException {

        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
             CSVReader csvReader = new CSVReader(reader)) {
            String[] nextRecord;
            ArrayList<AlbumEntity> albumLists = new ArrayList<>();
            while ((nextRecord = csvReader.readNext()) != null) {
                AlbumEntity tempAlbum = new AlbumEntity();

                if (albumLists.isEmpty()) {
                    Reader reader1 = Files.newBufferedReader(Paths.get(FILE_PATH));
                    CSVReader csvReader1 = new CSVReader(reader1);

                    int i = 0;

                    int randInt = new Random().nextInt(100);

                    while (i <= randInt) {
                        nextRecord = csvReader1.readNext();
                        i = i + 2;
                    }

                    System.out.println(Arrays.toString(nextRecord));
                    tempAlbum.setAlbumName(nextRecord[2]);
                    tempAlbum.setGenre(nextRecord[4]);

                    albumLists.add(tempAlbum);
                    albumRepository.save(tempAlbum);
                    csvReader1.close();

                }
            }

            return albumLists;
        }
    } // End FindRandomAlbum method

}
