package com.sdev450_finalproject.Controller;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.GetMapping;
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
	public TrackEntity findTrack() {

		try (Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_PATH));
				CSVReader csvReader = new CSVReader(reader);) {
			// Reading Records One by One in a String array
			String[] nextRecord;
			String searchTrack = "AWOL";

			while ((nextRecord = csvReader.readNext()) != null) {

				for (String e : nextRecord) {
					System.out.format("%s ", e);
				}

//
//            	for (String track : nextRecord) {
//            		if (track == searchTrack) {
//            			System.out.println( "found it");
//            		}
//            	}
//            	

//            	System.out.println("line added");
//            	System.out.println( csvReader.toString());

				TrackEntity track = null;
				return track;
			}

		}
	}
}
