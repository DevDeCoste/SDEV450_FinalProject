package com.sdev450_finalproject.Controller;

/**
 * @Course: SDEV-450-81 ~ Enterprise Java
 * @Author Name: Deven DeCoste, Madeline Merced & Trinh Nguyen
 * @Assignment Name: Final Project: Diet Spotify
 * @Subclass TrackController Description: Controller for Track Entity
 */

import com.opencsv.CSVReader;
import com.sdev450_finalproject.persistance.Album.AlbumEntity;
import com.sdev450_finalproject.persistance.Track.TrackEntity;
import com.sdev450_finalproject.persistance.Track.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("")
public class TrackController {

	@Autowired
	TrackRepository repository;

	static String FILE_PATH = "./src/main/resources/albumlist.csv";

	@GetMapping("/findTrackByAlbum/{findByAlbum}")
	public ArrayList<TrackEntity> findTrackByAlbumName(@PathVariable("findByAlbum") String searchTrack) throws IOException {

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
	public TrackEntity findAllbyTrackTitle(@PathVariable("trackName") String searchTrack) throws IOException {
		String[] nextRecord;
		ArrayList<TrackEntity> trackLists = new ArrayList<>();
		Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
		ArrayList<AlbumEntity> albumLists = new ArrayList<>();
		TrackEntity tempTrack = new TrackEntity();
boolean doesNotExist;
		CSVReader csvReader = new CSVReader(reader);
		String trackTitle = " ";

		while ((nextRecord = csvReader.readNext()) != null) {

			AlbumEntity albumEntity = new AlbumEntity();

			if (nextRecord[5].toLowerCase().contains(searchTrack.toLowerCase())) {
				// System.out.println("2++");
				trackTitle = nextRecord[5];
				tempTrack.setGenreType(nextRecord[4]);
				tempTrack.setTrackLength(nextRecord[6]);
				tempTrack.setYearPublished(nextRecord[1]);

				trackLists.add(tempTrack);
				albumEntity.addTrack(tempTrack);
				if(albumLists.contains(tempTrack)){
					albumEntity.setAlbumName(albumEntity.getAlbumName());
				};

			}
// else{
//				System.out.print(searchTrack + "Does not exist");
//				doesNotExist = true;
//			}

		}

		TrackEntity entity = new TrackEntity();
		List<TrackEntity> entities = repository.findAllByTrackTitle(trackTitle);
		if(entities.size() == 1){
			entity = entities.get(0);

		}
	 doesNotExist = true;

		if(doesNotExist){
			System.out.print(searchTrack + "reached does not exist section");
		}else{

		}

		entity.setTrackTitle(trackTitle);
		entity.setGenreType(tempTrack.getGenreType());
		entity.setTrackLength(tempTrack.getTrackLength());
		entity.setYearPublished(tempTrack.getYearPublished());

		if (repository.findByTrackTitleEquals(tempTrack.getTrackTitle()) == null) {
			repository.save(tempTrack);
		}
		repository.save(entity);

		csvReader.close();
		return entity;
	}

	/*
	 * TRINH: This is OLD CODE - LEAVING ALONE TO USE THE NEW MASTERCSV FILE
	 * 
	 * @GetMapping("/findTrack/{trackName}") public ArrayList<TrackEntity>
	 * findTrack(@PathVariable("trackName") String searchTrack) throws IOException,
	 * ParseException { String[] nextRecord; try (Reader reader =
	 * Files.newBufferedReader(Paths.get(FILE_PATH)); CSVReader csvReader = new
	 * CSVReader(reader);) { // Reading Records One by One in a String array
	 * 
	 * 
	 * 
	 * DateFormat sdf = new SimpleDateFormat("mm:ss"); while ((nextRecord =
	 * csvReader.readNext()) != null) { TrackEntity tempTrack = new TrackEntity();
	 * 
	 * if (nextRecord[nextRecord.length - 2].contains(searchTrack)) {
	 * 
	 * tempTrack.setArtist_name(nextRecord[2]);
	 * tempTrack.setArtist_url(nextRecord[3]);
	 * tempTrack.setTrack_duration(sdf.parse((nextRecord[8])));
	 * tempTrack.setTrack_image_location(nextRecord[10]);
	 * tempTrack.setTrack_interest(Long.parseLong(nextRecord[12]));
	 * tempTrack.setTrack_title(nextRecord[nextRecord.length - 2]);
	 * tempTrack.setTrack_url_location(nextRecord[nextRecord.length - 1]);
	 * 
	 * trackLists.add(tempTrack);
	 * 
	 * }
	 * 
	 * } csvReader.close();
	 * 
	 * // if there are no track found, add 1 item to track RANDOMLY // resource: //
	 * http://opencsv.sourceforge.net/apidocs/com/opencsv/CSVReaderBuilder.html if
	 * (trackLists.isEmpty()) { Reader reader1 =
	 * Files.newBufferedReader(Paths.get(FILE_PATH)); CSVReader csvReader1 = new
	 * CSVReader(reader1);
	 * 
	 * TrackEntity tempTrack = new TrackEntity();
	 * 
	 * int i = 0;
	 * 
	 * int randInt = new Random().nextInt(100);
	 * 
	 * while (i <= randInt) { nextRecord = csvReader1.readNext(); i = i + 2; }
	 * 
	 * // csvReader1.skip(25); // csvReader1.getSkipLines(); // //
	 * System.out.println(csvReader1.getSkipLines()); // nextRecord =
	 * csvReader1.peek(); // nextRecord = csvReader1.readNext();
	 * System.out.println(Arrays.toString(nextRecord));
	 * tempTrack.setArtist_name(nextRecord[2]);
	 * tempTrack.setArtist_url(nextRecord[3]);
	 * tempTrack.setTrack_duration(sdf.parse((nextRecord[8])));
	 * tempTrack.setTrack_image_location(nextRecord[10]);
	 * tempTrack.setTrack_interest(Long.parseLong(nextRecord[12]));
	 * tempTrack.setTrack_title(nextRecord[nextRecord.length - 2]);
	 * tempTrack.setTrack_url_location(nextRecord[nextRecord.length - 1]);
	 * 
	 * trackLists.add(tempTrack); csvReader1.close();
	 * 
	 * } return trackLists; }
	 * 
	 * } END OF CODE COMMENTED OUT HERE
	 */



@PostMapping("/save/{trackName}")
	boolean saveTrack(ArrayList trackList, @PathVariable("trackName") String trackName)
			throws IOException, ParseException {

		// trackList = findTrack(trackName);
		// System.out.println(trackLists.toString());
		return true;
	}

}
