package com.sdev450_finalproject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.opencsv.CSVReader;
import com.sdev450_finalproject.persistance.Album.AlbumEntity;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


@RestController
@RequestMapping("")
public class AlbumController {

    static String FILE_PATH = "CSVFiles/albumlist.csv";

    @GetMapping("/findAlbum/{albumName}")
    public ArrayList<AlbumEntity> findAlbum(@PathVariable("albumName") String searchAlbum)
            throws IOException {

        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
             CSVReader csvReader = new CSVReader(reader)) {


            String[] nextRecord;
            ArrayList<AlbumEntity> albumLists = new ArrayList<>();



            while ((nextRecord = csvReader.readNext()) != null) {
                AlbumEntity tempAlbum = new AlbumEntity();

                if (nextRecord[nextRecord.length - 2].contains(searchAlbum)) {

                    tempAlbum.setAlbumName(nextRecord[2]);
                    tempAlbum.setArtist(nextRecord[3]);
                    tempAlbum.setGenre(nextRecord[4]);


                    albumLists.add(tempAlbum);

                }

            }

            return albumLists;
        }
    }


//    @GetMapping("/loadAlbum")
//    public String LoadAlbum() {
//        try {
//
//
//        } catch(FileNotFoundException ex) {
//
//        }
//    }

}
