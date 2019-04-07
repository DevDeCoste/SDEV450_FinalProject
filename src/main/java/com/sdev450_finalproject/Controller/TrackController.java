package com.sdev450_finalproject.Controller;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVReader;
import com.sdev450_finalproject.persistance.TrackEntity;

@RestController
@RequestMapping("")
public class TrackController {

	// pseudocode:
	// get searchtext from url
	// iterate through csv to see if there's match
	// match = return track object
	// if no match, generate a random line and fetch that track object
	// return a track object

	// TEMP FILE HOLDER - need to refactor for final submission
	static String FILE_PATH = "C:\\Users\\0810\\eclipse-workspace\\Java-core-workspace\\enterprise_java_final_project\\SDEV450_FinalProject\\src\\main\\resources\\raw_tracks.csv";

	@GetMapping("/findTrack/{trackName}")
	public ArrayList<TrackEntity> findTrack(@PathVariable("trackName") String searchTrack)
			throws IOException, ParseException {

		try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
				CSVReader csvReader = new CSVReader(reader);) {
			// Reading Records One by One in a String array
			String[] nextRecord;
			ArrayList<TrackEntity> trackLists = new ArrayList<>();
		

			DateFormat sdf = new SimpleDateFormat("mm:ss");
			while ((nextRecord = csvReader.readNext()) != null) {
				TrackEntity tempTrack = new TrackEntity();

				if (nextRecord[nextRecord.length - 2].contains(searchTrack)) {

					tempTrack.setArtist_name(nextRecord[2]);
					tempTrack.setArtist_url(nextRecord[3]);
					tempTrack.setTrack_duration(sdf.parse((nextRecord[8])));
					tempTrack.setTrack_image_location(nextRecord[10]);
					tempTrack.setTrack_interest(Long.parseLong(nextRecord[12]));
					tempTrack.setTrack_title(nextRecord[nextRecord.length - 2]);
					tempTrack.setTrack_url_location(nextRecord[nextRecord.length - 1]);

					trackLists.add(tempTrack);
					

//            			
//            		}
//
//			
				}

			}

			return trackLists;
		}
	}
}
