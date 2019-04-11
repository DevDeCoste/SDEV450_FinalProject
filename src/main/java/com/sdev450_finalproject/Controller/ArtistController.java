package com.sdev450_finalproject.Controller;

/**
 * @Course: SDEV 450 - Enterprise Java
 * @Author Name: Madeline Merced
 * @Assignment Name: com.sdev450_finalproject.Controller
 * @Date: Apr 6, 2019
 * @Subclass ArtistController Description:
 */
//Imports
import com.opencsv.CSVReader;
import com.sdev450_finalproject.persistance.Artist.ArtistEntity;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Begin Subclass ArtistController
@RestController
@RequestMapping("")
public class ArtistController {

    static String FILE_PATH = "./src/main/resources/albumlist.csv";

    @GetMapping("/findArtist/{artistName}")
    public ArrayList<ArtistEntity> findArtist(@PathVariable("artistName") String searchArtist)
            throws IOException, ParseException {

        //Opens reader to read CSV file and set table values
        try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);) {

            String[] nextRecord;
            ArrayList<ArtistEntity> artists = new ArrayList<>();

            while ((nextRecord = csvReader.readNext()) != null) {
                ArtistEntity tempArtist = new ArtistEntity();

                if (nextRecord[nextRecord.length - 2].contains(searchArtist)) {

                    tempArtist.setId(nextRecord[0]);
                    tempArtist.setArtistName(nextRecord[3]);
                    tempArtist.setAlbumName(nextRecord[2]);
//                    tempArtist.setTracks(nextRecord);
                    tempArtist.setGenre(nextRecord[4]);

                    artists.add(tempArtist);

                }

            }

            csvReader.close();

            if (artists.isEmpty()) {
                Reader reader1 = Files.newBufferedReader(Paths.get(FILE_PATH));
                CSVReader csvReader1 = new CSVReader(reader1);

                ArtistEntity tempArtist = new ArtistEntity();

                int i = 0;

                int randInt = new Random().nextInt(100);

                while (i <= randInt) {
                    nextRecord = csvReader1.readNext();
                    i = i + 2;
                }

//				
                System.out.println(Arrays.toString(nextRecord));
                tempArtist.setId(nextRecord[0]);
                tempArtist.setArtistName(nextRecord[3]);
                tempArtist.setAlbumName(nextRecord[2]);
//                tempArtist.setTracks(nextRecord);
                tempArtist.setGenre(nextRecord[4]);

                artists.add(tempArtist);
                csvReader1.close();

            }
            return artists;
        }
    }

} //End Subclass ArtistController

