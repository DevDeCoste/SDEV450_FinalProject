package com.sdev450_finalproject.Controller;

/** 
 * @Course: SDEV 250 ~ Java Programming I
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
 
    static String FILE_PATH = "C:\\Users\\Doug\\Desktop\\School\\CurrentClasses\\Enterprise Java\\SDEV450_FinalProject-master\\src\\main\\resources\\SpotifyAudioFeaturesNov2018.csv";

    @GetMapping("/artist/{artistName}")
    public ArrayList<ArtistEntity> findArtist(@PathVariable("artistName") String searchArtist)
			throws IOException, ParseException {
		String[] nextRecord;
		try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
				CSVReader csvReader = new CSVReader(reader);) {
			// Reading Records One by One in a String array

			ArrayList<ArtistEntity> artists = new ArrayList<>();

			while ((nextRecord = csvReader.readNext()) != null) {
				ArtistEntity tempArtist = new ArtistEntity();

				if (nextRecord[nextRecord.length - 2].contains(searchArtist)) {

					tempArtist.setArtist_name(nextRecord[0]);
                                        tempArtist.setTrack_name(nextRecord[1]);
                                        tempArtist.setPopularity(nextRecord[2]);

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
				tempArtist.setArtist_name(nextRecord[0]);
                                tempArtist.setTrack_name(nextRecord[1]);
                                tempArtist.setPopularity(nextRecord[2]);

				artists.add(tempArtist);
				csvReader1.close();

			}
			return artists;
		}
	}
    
} //End Subclass ArtistController

