package com.sdev450_finalproject.Controller;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

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
	public void findTrack(@PathVariable("trackName") String searchTrack) throws IOException {

		try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
				CSVReader csvReader = new CSVReader(reader);) {
			// Reading Records One by One in a String array
			String[] nextRecord;
		
			while ((nextRecord = csvReader.readNext()) != null) {

            	for (int i = 0; i < nextRecord.length; i++) {
            		if (nextRecord[i].contains(searchTrack)) {
            			System.out.println("found AWOL search track. printing line");
            			System.out.println(csvReader.getLinesRead());
            			System.out.println(Arrays.toString(nextRecord));
            			
            		}

			
			}

		}
	}
}
}
