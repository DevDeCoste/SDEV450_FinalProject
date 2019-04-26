package com.sdev450_finalproject.Controller;

import com.opencsv.CSVReader;
import com.sdev450_finalproject.persistance.Track.TrackEntity;
import com.sdev450_finalproject.persistance.Track.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

@RestController
@RequestMapping("")
public class TrackController {

	@Autowired
	TrackRepository repository;

	static String FILE_PATH = "./src/main/resources/albumlist.csv";


	@GetMapping("/findTrackByAlbum/{findByAlbum}")
	public ArrayList<TrackEntity> findTrackbyAlbumName(@PathVariable("findByAlbum") String searchTrack) throws IOException {
	
		
		String[] nextRecord;
		ArrayList<TrackEntity> trackLists = new ArrayList<>();
		Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));

		CSVReader csvReader = new CSVReader(reader);

		while ((nextRecord = csvReader.readNext()) != null) {
			System.out.println("1++");

			TrackEntity tempTrack = new TrackEntity();

			if (nextRecord[2].toLowerCase().contains(searchTrack.toLowerCase())) {

				tempTrack.setGenreType(nextRecord[4]);
				tempTrack.setTrackLength(nextRecord[6]);
				tempTrack.setTrackTitle(nextRecord[5]);
				tempTrack.setYearPublished(nextRecord[1]);

				trackLists.add(tempTrack);
			}

		}

		csvReader.close();
		return trackLists; 
		
		
		
	}
	
	@GetMapping("/saveTrack/{trackName}/{userId}")
	public boolean saveTrack(@PathVariable String trackName, @PathVariable long userId ) throws IOException {
		 
		repository.save(randomTrack());
		return true;
	}
	

	@GetMapping("/findTrack/random")
	public TrackEntity randomTrack() throws IOException {

		TrackEntity randTrack = new TrackEntity();
		Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));

		CSVReader csvReader = new CSVReader(reader);
		String[] nextRecord;
		int i = 0;
		int randInt = new Random().nextInt(80);
		while ((nextRecord = csvReader.readNext()) != null) {



			while (i <= randInt) {
				nextRecord = csvReader.readNext();
				i = i + 2;


				randTrack.setGenreType(nextRecord[4]);
				randTrack.setTrackLength(nextRecord[6]);
				randTrack.setTrackTitle(nextRecord[5]);
				randTrack.setYearPublished(nextRecord[1]);
			}


		}

		csvReader.close();
		return randTrack;
	}

	@PostMapping("/findTrack/{trackName}")
	public ArrayList<TrackEntity> findTrack(@PathVariable("trackName") String searchTrack) throws IOException {
		String[] nextRecord;
		ArrayList<TrackEntity> trackLists = new ArrayList<>();
		Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));

		CSVReader csvReader = new CSVReader(reader);

		while ((nextRecord = csvReader.readNext()) != null) {
			

			TrackEntity tempTrack = new TrackEntity();

			if (nextRecord[5].toLowerCase().contains(searchTrack.toLowerCase())) {
				tempTrack.setGenreType(nextRecord[4]);
				tempTrack.setTrackLength(nextRecord[6]);
				tempTrack.setTrackTitle(nextRecord[5]);
				tempTrack.setYearPublished(nextRecord[1]);

				trackLists.add(tempTrack);

				if(repository.findByTrackTitleContains(tempTrack.getTrackTitle()) == null) {
					repository.save(tempTrack);
				}
			}

		}

		csvReader.close();
		return trackLists;

	}


	@PostMapping("/save/{trackName}")
	boolean saveTrack(ArrayList trackList, @PathVariable("trackName") String trackName)
			throws IOException, ParseException {

		return true;
	}

}
