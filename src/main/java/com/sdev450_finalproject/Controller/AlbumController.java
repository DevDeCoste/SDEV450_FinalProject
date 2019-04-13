package com.sdev450_finalproject.Controller;

import com.opencsv.CSVReader;
import com.sdev450_finalproject.persistance.Album.AlbumEntity;
import com.sdev450_finalproject.persistance.Album.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    static String FILE_PATH = "./src/main/resources/MasterCSV.csv";

    public ArrayList<AlbumEntity> albumLists = new ArrayList<>();

    public AlbumEntity tempAlbum = new AlbumEntity();



    @Autowired
    AlbumRepository repository;

    // Just me doing a test push from work
    //And then another test comment for the test push 2
    @GetMapping(path = "/albums")
    public List<AlbumEntity> getEntities() {
        return repository.findAll();
    }

    @PostMapping(path = "/albums")
    public boolean createAlbum(@RequestBody AlbumEntity albumEntity) {
        repository.save(albumEntity);
        return true;
    }

    @GetMapping("/findAlbumByArtist/{artistName}")
    public ResponseEntity findAlbumByArtist(@PathVariable("artistName") String artistName) {

        ArrayList<AlbumEntity> Entities = repository.findAllByArtist(artistName);
        if (Entities.isEmpty()) {
            //Returns 404 if not present
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        //Returns Album Entity if present
        return new ResponseEntity(Entities, HttpStatus.OK);
    }

    @GetMapping("/findAlbum/{albumName}")
    public ArrayList<AlbumEntity> findAlbum(@PathVariable("albumName") String searchAlbum)
            throws IOException {

        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
             CSVReader csvReader = new CSVReader(reader)) {
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {


                if (nextRecord[3].equalsIgnoreCase(searchAlbum)) {

                    tempAlbum.setAlbumName(nextRecord[2]);
                    tempAlbum.setArtist(nextRecord[3]);
                    tempAlbum.setGenre(nextRecord[4]);

                    albumLists.add(tempAlbum);

                    if (repository.findByAlbumNameEquals(tempAlbum.getAlbumName()) == null) {
                        repository.save(tempAlbum);
                    }

                }

            }

            csvReader.close();

            return albumLists;
        }

    } //End FindAlbum Method

    @GetMapping("/findRandomAlbum")
    public ArrayList<AlbumEntity> findRandomAlbum()
            throws IOException {

        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
             CSVReader csvReader = new CSVReader(reader)) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {


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
                    tempAlbum.setArtist(nextRecord[3]);
                    tempAlbum.setGenre(nextRecord[4]);

                    albumLists.add(tempAlbum);
                    repository.save(tempAlbum);
                    csvReader1.close();

                }
            }

            return albumLists;
        }
    } //End FindRandomAlbum method

}

