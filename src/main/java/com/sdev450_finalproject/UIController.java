package com.sdev450_finalproject;

import com.sdev450_finalproject.persistance.Album.AlbumEntity;
import com.sdev450_finalproject.persistance.Album.AlbumRepository;
import com.sdev450_finalproject.persistance.Artist.ArtistEntity;
import com.sdev450_finalproject.persistance.Artist.ArtistRepository;
import com.sdev450_finalproject.persistance.Track.TrackEntity;
import com.sdev450_finalproject.persistance.Track.TrackRepository;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class UIController {
	
	@Autowired
	AlbumRepository albumRepos;
	
	@Autowired
	TrackRepository trackRepos;
	
	@Autowired
	ArtistRepository artistRepos;

	private final HostServices hostServices;

	UIController(HostServices hostServices) {
		this.hostServices = hostServices;
	}

	@FXML
	public Label label;

	@FXML
	public Button allAlbums;

	@FXML
	public Button findTrack;

	@FXML
	public HBox buttonBox;

	@FXML
	public TextArea text;

	@FXML
	public Button searchAlbum;

	@FXML
	public TextField textField;

	@FXML
	public Button allArtists;

	@FXML
	public Button searchArtist;

	@FXML
	public Button delete;

	@FXML
	public Button deleteSpecific;

//	@FXML
//	protected void findHandler(ActionEvent event) {
//
//		System.out.println("this got hit");
//		RestTemplate restTemplate = new RestTemplate();
//
//		// System.out.println(UIController.textField.getText());
//		// System.out.println(UIController.textField.getText()+ "11");
//text.clear();
//		String fooResourceUrl = "http://localhost:8085/findTrack/"+textField.getText();
//		ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl,null, String.class);
//
//		System.out.println(response.toString());
//		System.out.println(response.getBody());
//
//		// String data = restTemplate.getForEntity("http://localhost:8085/findTrack/"+
//		// UIController.textField.getText(), String.class ).getBody();
//		this.text.setText(response.getBody());
//		TrackEntity trackget = restTemplate.getForObject(fooResourceUrl, TrackEntity.class);
//		System.out.println(trackget.toString());
//		//this.text.setText(restTemplate.getForObject(fooResourceUrl, TrackEntity.class));
//		System.out.println(textField.getText());
//
//	}

	@FXML
	public void initialize() {
		
//		/* TRINH:
//		 * initialize album and tracks to test mapping
//		 */
//
//
//
//		ArrayList<TrackEntity> tracksInAlbum =  new ArrayList<>();
//
//		TrackEntity trackToSave = new TrackEntity();
//		TrackEntity trackToSave1 = new TrackEntity();
//		AlbumEntity albumToSave = new AlbumEntity ();
//
//		albumToSave.setAlbumName("BSB ALBUM");
//
//		trackToSave.setTrackTitle("I want it that way");
//		trackToSave.setGenreType("90s Music");
//		trackToSave.setAlbumTitle("BackStreet Boys Album 1");
//		trackToSave.setArtistName("Back Street Boys");
//		trackToSave.setAlbumName(albumToSave);
//
//		trackToSave1.setTrackTitle("I want it that way");
//		trackToSave1.setGenreType("90s Music");
//		trackToSave1.setAlbumTitle("BackStreet Boys Album 1");
//		trackToSave1.setArtistName("Back Street Boys");
//		trackToSave1.setAlbumName(albumToSave);
//
//		tracksInAlbum.add(trackToSave);
//		tracksInAlbum.add(trackToSave1);
//
//		albumToSave.setTracks(tracksInAlbum);
//		ArtistEntity newArtist = new ArtistEntity();

		
		
//		albumRepos.save(albumToSave);
		
		
		/*
		 * end initalize album can be deleted 
		 */

		/*Albums - Lists all albums in database*/
		
		
		
		this.allAlbums.setOnAction(actionEvent -> {
			text.clear();
			RestTemplate restTemplate = new RestTemplate();
			String data = restTemplate.getForEntity("http://localhost:8085/albums", String.class).getBody();
			this.text.setText(String.format("%-8s\t\n", data + "\n"));

			//this.text.appendText("\n");
		});



		/*Albums - Searches by Album Name*/
		this.searchAlbum.setOnAction(actionEvent -> {
			text.clear();
			RestTemplate restTemplate = new RestTemplate();
			String input = textField.getText();
			String data = restTemplate
					.postForEntity("http://localhost:8085/findTracksInAlbum/" + input, null, String.class).getBody();
			this.text.setText(data);
		});

		/*Tracks - Searches by Album Name*/
		this.findTrack.setOnAction(actionEvent -> {
			text.clear();
			RestTemplate restTemplate = new RestTemplate();
			String input = textField.getText();
			String data = restTemplate
					.postForEntity("http://localhost:8085/findTrack/" + input, null, String.class).getBody();
			this.text.setText(data);
		});

		/* VIEW ALL CONTENTS IN DATABASE */
		//TRINH
		this.allArtists.setOnAction(actionEvent -> {
			text.clear();
			this.text.appendText("---HERE ARE THE ALBUMS IN THE DATABASE - LOCATED ON SERVER--- \n");
			
			List<AlbumEntity> allAlbums = albumRepos.findAll();
			//this.text.appendText(albumRepos.findAll().toString());
			int counter = 1;
			for (AlbumEntity X: allAlbums) {
				text.appendText("Album No. " + counter +"\n");
				String xName = String.format("Album Name: %s\n", X.getAlbumName());
				String xGenre = String.format("Genre Type: %s\n\n", X.getGenre());
				text.appendText(xName);
				text.appendText(xGenre);
				counter++;
				
			
			}
			this.text.appendText("---END OF ALBUMS IN THE DATABASE - LOCATED ON SERVER--- \n\n\n\n");
			
			counter =1;
			
			this.text.appendText("---HERE ARE THE TRACKS IN THE DATABASE - LOCATED ON SERVER--- \n");
			
			List<TrackEntity> allTracks = trackRepos.findAll();
			
			for (TrackEntity X : allTracks) {
				text.appendText("Track No. " + counter +"\n");
				String tName = String.format("Track Name: %s\n", X.getTrackTitle());
				String yearPub = String.format("Year Published: %s\n\n", X.getYearPublished());
				String tLength = String.format("Track Length: %s\n", X.getTrackLength());
				String tGenre = String.format("Track Genre: %s\n", X.getGenreType());
				
				text.appendText(tName  + tLength + tGenre+ yearPub);
				counter++;
				
			}
			
			counter =1;
			
			this.text.appendText("---END OF TRACKS IN THE DATABASE - LOCATED ON SERVER--- \n\n\n\n");
			
			this.text.appendText("---HERE ARE THE ARTISTS WE HAVE IN THE DATABASE - LOCATED ON SERVER--- \n");
			List<ArtistEntity> allArtist = artistRepos.findAll();
			for (ArtistEntity X : allArtist) {
				text.appendText("Artist No. " + counter +"\n");
				String tName = String.format("Arist Name: %s\n", X.getArtistName());
	 
				
				text.appendText(tName);
				counter++;
				
			}
			
			this.text.appendText("---END OF ARTISTS IN THE DATABASE - LOCATED ON SERVER--- \n\n\n\n");
			
			
 
	});
		
		/* VIEW DATABASE CONTENTS EDITED - this is original 11:40 PM* TRINH comment/
		/*Artists - Lists all Artists in database*/
//		this.allArtists.setOnAction(actionEvent -> {
//			RestTemplate restTemplate = new RestTemplate();
//			String data = restTemplate.getForEntity("http://localhost:8085/artists", String.class).getBody();
//			this.text.setText(data);
//		});
		

		/*Artists - Searches by Artist Name*/
		this.searchArtist.setOnAction(actionEvent -> {
			RestTemplate restTemplate = new RestTemplate();
			String input = textField.getText();
			String data = restTemplate
					.postForEntity("http://localhost:8085/insertFromCSV/" + input, null, String.class).getBody();
			this.text.setText(data);
		});

		/*Entire Database Delete*/
		this.delete.setOnAction(actionEvent -> {
			RestTemplate restTemplate = new RestTemplate();
			String deleteAllUrl = "http://localhost:8085/delete";
			restTemplate.delete(deleteAllUrl);
			this.text.setText("Database has been deleted");
		});

		/*Artists - Searches by Artist Name*/
		this.deleteSpecific.setOnAction(actionEvent -> {
			RestTemplate restTemplate = new RestTemplate();
			String input = textField.getText();
			String deleteSpecificURL = "http://localhost:8085/deleteByArtist/" + input;
			restTemplate.delete(deleteSpecificURL);
			this.text.setText(input + " has been deleted from the database.");
		});

	}

}
