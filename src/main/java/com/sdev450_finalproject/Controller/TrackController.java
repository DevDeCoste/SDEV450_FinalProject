package com.sdev450_finalproject.Controller;

import com.opencsv.CSVReader;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("")
public class TrackController {
	static ArrayList<TrackEntity> trackLists = new ArrayList<>();
	@Autowired
	TrackRepository repository;





	// pseudocode:
	// get searchtext from url
	// iterate through csv to see if there's match
	// match = return track object
	// if no match, generate a random line and fetch that track object
	// return a track object

	// TEMP FILE HOLDER - need to refactor for final submission
	static String FILE_PATH ="./src/main/resources/albumlist.csv";// "C:\\Users\\0810\\eclipse-workspace\\Java-core-workspace\\enterprise_java_final_project\\SDEV450_FinalProject\\src\\main\\resources\\raw_tracks.csv";
	//C:\Users\0810\eclipse-workspace\Java-core-workspace\enterprise_java_final_project\SDEV450_FinalProject\src\main\resources
	//./src/main/resources/MasterCSV.csv
	//C:\\Users\\0810\\eclipse-workspace\\Java-core-workspace\\enterprise_java_final_project\\SDEV450_FinalProject\\src\\main\\resources\\MasterCSV.csv

	@GetMapping("/findTracksbyAlbum/{albumName}")
	public ResponseEntity findTracksbyAlbum(@PathVariable("albumName") String albumName) {
		ArrayList<TrackEntity> Entities = repository.findTrackEntitiesByAlbumTitleContains(albumName);
		if (Entities.isEmpty()) {
			//Returns 404 if not present
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		//Returns Track Entity if present
		return new ResponseEntity(Entities, HttpStatus.OK);
	}
	
	@GetMapping("/findTrack/{trackName}")
	public ArrayList<TrackEntity> findTrack(@PathVariable("trackName") String searchTrack) throws IOException{
		String[] nextRecord;
		Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
		
		CSVReader csvReader = new CSVReader(reader);
		
		System.out.println("the name i wanted to search is:");
		System.out.println(searchTrack);
		int i = 0;
		
//		List<String[]> allData = csvReader.readAll();
//		for (String[] X : allData) {
//			System.out.println(X);
//		}
//		
		
		while ((nextRecord = csvReader.readNext()) != null) {
			
			System.out.println(Arrays.toString(nextRecord));
			i++;
			System.out.println("line " + i);
//			TrackEntity tempTrack = new TrackEntity();
//
//			if (nextRecord[3].contains(searchTrack)) {
//				System.out.println("ITS THERE !!!");
//			}
//			else {
//				System.out.println("ITS NOT THERE OR NOT LOADED");
//			}
		}
		
		
		return trackLists;
		
	}
	/*
	 * TRINH: This is OLD CODE - LEAVING ALONE TO USE THE NEW MASTERCSV FILE
	 * 
	@GetMapping("/findTrack/{trackName}")
	public ArrayList<TrackEntity> findTrack(@PathVariable("trackName") String searchTrack)
			throws IOException, ParseException {
		String[] nextRecord;
		try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
				CSVReader csvReader = new CSVReader(reader);) {
			// Reading Records One by One in a String array



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

				}

			}
			csvReader.close();

			// if there are no track found, add 1 item to track RANDOMLY
			// resource:
			// http://opencsv.sourceforge.net/apidocs/com/opencsv/CSVReaderBuilder.html
			if (trackLists.isEmpty()) {
				Reader reader1 = Files.newBufferedReader(Paths.get(FILE_PATH));
				CSVReader csvReader1 = new CSVReader(reader1);

				TrackEntity tempTrack = new TrackEntity();

				int i = 0;

				int randInt = new Random().nextInt(100);

				while (i <= randInt) {
					nextRecord = csvReader1.readNext();
					i = i + 2;
				}

//				  csvReader1.skip(25); 
//				  csvReader1.getSkipLines();
//				  
//				  System.out.println(csvReader1.getSkipLines()); 
//				  nextRecord =				  csvReader1.peek(); 
//				  nextRecord = csvReader1.readNext();
				System.out.println(Arrays.toString(nextRecord));
				tempTrack.setArtist_name(nextRecord[2]);
				tempTrack.setArtist_url(nextRecord[3]);
				tempTrack.setTrack_duration(sdf.parse((nextRecord[8])));
				tempTrack.setTrack_image_location(nextRecord[10]);
				tempTrack.setTrack_interest(Long.parseLong(nextRecord[12]));
				tempTrack.setTrack_title(nextRecord[nextRecord.length - 2]);
				tempTrack.setTrack_url_location(nextRecord[nextRecord.length - 1]);

				trackLists.add(tempTrack);
				csvReader1.close();

			}
			return trackLists;
		}
	
	}
		END OF CODE COMMENTED OUT HERE*/

	@PostMapping("/save/{trackName}")
	boolean saveTrack(ArrayList trackList, @PathVariable("trackName") String trackName) throws IOException, ParseException {
		
		//trackList = findTrack(trackName);
		System.out.println(trackLists.toString());
		return true;
	}

}
