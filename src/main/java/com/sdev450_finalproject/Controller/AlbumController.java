package com.sdev450_finalproject.Controller;

import com.opencsv.CSVReader;
import com.sdev450_finalproject.persistance.Album.AlbumEntity;
import com.sdev450_finalproject.persistance.TrackEntity;
import com.sdev450_finalproject.persistance.Album.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("")
public class AlbumController {

	static String FILE_PATH = "./src/main/resources/albumlist.csv";

	@Autowired
	AlbumRepository repository;

	@GetMapping(path = "/albums")
	public List<AlbumEntity> getEntities() {
		return repository.findAll();
	}

	@PostMapping(path = "/albums")
	public boolean createAlbum(@RequestBody AlbumEntity albumEntity) {
		repository.save(albumEntity);
		return true;
	}

	@GetMapping("/findAlbumByArtist/{artistName}")
	public ResponseEntity findAlbumByArtist(@PathVariable("artistName") String artistName) {

		ArrayList<AlbumEntity> Entities = repository.findAllByArtist(artistName);
		if (Entities.isEmpty()) {
			// Returns 404 if not present
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		// Returns Album Entity if present
		return new ResponseEntity(Entities, HttpStatus.OK);
	}

	@GetMapping("/findTracksInAlbum/{findByAlbumName}")
	public ArrayList<AlbumEntity> findTrackbyAlbumName(@PathVariable("findByAlbumName") String searchTrack)
			throws IOException {

		String[] nextRecord;
		ArrayList<TrackEntity> trackLists = new ArrayList<>();
		ArrayList<AlbumEntity> albumLists = new ArrayList<>();
		AlbumEntity tempAlbum = new AlbumEntity();
		Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));

		CSVReader csvReader = new CSVReader(reader);

		while ((nextRecord = csvReader.readNext()) != null) {
	 		TrackEntity tempTrack = new TrackEntity();

			if (nextRecord[2].toLowerCase().contains(searchTrack.toLowerCase())) {

				tempTrack.setAlbumTitle(nextRecord[2]);
				tempTrack.setArtistName(nextRecord[3]);
				tempTrack.setGenreType(nextRecord[4]);
				tempTrack.setTrackLength(nextRecord[6]);
				tempTrack.setTrackTitle(nextRecord[5]);
				tempTrack.setYearPublished(nextRecord[1]);

				trackLists.add(tempTrack);
			}

		}


 

		tempAlbum.setAlbumName(trackLists.get(1).getAlbumTitle());
		tempAlbum.setArtist(trackLists.get(1).getArtistName());
		tempAlbum.setGenre(trackLists.get(1).getGenreType());

		String track[] = new String[trackLists.size()];
		int j = 0;
		for (TrackEntity X : trackLists) {
			track[j] = X.getTrackTitle();
			j++;
		}

		tempAlbum.setAlbumTracks(track);
		albumLists.add(tempAlbum);

                            if (repository.findByAlbumNameEquals(tempAlbum.getAlbumName()) == null) {
                        repository.save(tempAlbum);
                    }
		csvReader.close();
		return albumLists;

	}

	@GetMapping("/findAlbum/{albumName}")
	public ArrayList<AlbumEntity> findAlbum(@PathVariable("albumName") String searchAlbum) throws IOException {

		try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
				CSVReader csvReader = new CSVReader(reader)) {
			String[] nextRecord;
			// String[] track;

			ArrayList<TrackEntity> TrackList = new ArrayList<>();
			ArrayList<AlbumEntity> albumLists = new ArrayList<>();

			while ((nextRecord = csvReader.readNext()) != null) {
				AlbumEntity tempAlbum = new AlbumEntity();

				if (nextRecord[2].equalsIgnoreCase(searchAlbum)) {

					TrackEntity tempTrack = new TrackEntity();

					// nextRecord[5].toLowerCase().contains(searchTrack.toLowerCase())
					// StringUtils.containsIgnoreCase(searchTrack, nextRecord[5])
//			CharSequence charAt5 = nextRecord[5];
//			CharSequence searchChar = searchTrack;

					if (nextRecord[2].toLowerCase().contains(searchAlbum.toLowerCase())
							&& (csvReader.readNext()) != null) {
						// System.out.println("2++");
						for (int i = 0; i < nextRecord.length; i++) {
							csvReader.readNext();
							tempTrack.setAlbumTitle(nextRecord[2]);
							tempTrack.setArtistName(nextRecord[3]);
							tempTrack.setGenreType(nextRecord[4]);
							tempTrack.setTrackLength(nextRecord[6]);
							tempTrack.setTrackTitle(nextRecord[5]);
							tempTrack.setYearPublished(nextRecord[1]);

							TrackList.add(tempTrack);
							csvReader.readNext();
						}

						// String[] tracks = TrackList.get(i);
					}

//                    while((nextRecord[2].equalsIgnoreCase(searchAlbum)) && (csvReader.readNext()) != null) {
//
//                        tracks.add(nextRecord[5].toString());
//                    }

					tempAlbum.setAlbumName(nextRecord[2]);
					tempAlbum.setArtist(nextRecord[3]);
					tempAlbum.setGenre(nextRecord[4]);
					System.out.println(TrackList.toString());

					System.out.println("print object 2");
					System.out.println(TrackList.get(1).getTrackTitle()); // TaxMan
					System.out.println(TrackList.get(2).getTrackTitle()); // TaxMan
					System.out.println(TrackList.get(3).getTrackTitle()); // TaxMan

					for (TrackEntity A : TrackList) {
						System.out.println(A.toString());
					}
					String track[] = new String[TrackList.size()];
					int j = 0;
					for (TrackEntity X : TrackList) {
						track[j] = X.getTrackTitle();
						j++;
					}

					tempAlbum.setAlbumTracks(track);
					albumLists.add(tempAlbum);

//                    if (repository.findByAlbumNameEquals(tempAlbum.getAlbumName()) == null) {
//                        repository.save(tempAlbum);
//                    }
					csvReader.close();
				}

			}

			return albumLists;
		}

	} // End FindAlbum Method

	@GetMapping("/findRandomAlbum")
	public ArrayList<AlbumEntity> findRandomAlbum() throws IOException {

		try (Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
				CSVReader csvReader = new CSVReader(reader)) {
			String[] nextRecord;
			ArrayList<AlbumEntity> albumLists = new ArrayList<>();
			while ((nextRecord = csvReader.readNext()) != null) {
				AlbumEntity tempAlbum = new AlbumEntity();

				if (albumLists.isEmpty()) {
					Reader reader1 = Files.newBufferedReader(Paths.get(FILE_PATH));
					CSVReader csvReader1 = new CSVReader(reader1);

					int i = 0;

					int randInt = new Random().nextInt(100);

					while (i <= randInt) {
						nextRecord = csvReader1.readNext();
						i = i + 2;
					}

					System.out.println(Arrays.toString(nextRecord));
					tempAlbum.setAlbumName(nextRecord[2]);
					tempAlbum.setArtist(nextRecord[3]);
					tempAlbum.setGenre(nextRecord[4]);

					albumLists.add(tempAlbum);
					repository.save(tempAlbum);
					csvReader1.close();

				}
			}

			return albumLists;
		}
	} // End FindRandomAlbum method

}
