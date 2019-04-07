package com.sdev450_finalproject.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.opencsv.CSVReader;
import com.sdev450_finalproject.persistance.Album.AlbumEntity;


@RestController
@RequestMapping("")
public class AlbumController {

    static String FILE_PATH = "CSVFiles/albumlist.csv";


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
