package com.sdev450_finalproject;

import com.sdev450_finalproject.Controller.DialogController;
import com.sdev450_finalproject.Controller.TrackController;
import com.sdev450_finalproject.persistance.Album.AlbumEntity;
import com.sdev450_finalproject.persistance.Album.AlbumRepository;
import com.sdev450_finalproject.persistance.Artist.ArtistEntity;
import com.sdev450_finalproject.persistance.Artist.ArtistRepository;
import com.sdev450_finalproject.persistance.Track.TrackEntity;
import com.sdev450_finalproject.persistance.Track.TrackRepository;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



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

	@FXML
	public void initialize() {

		/*Albums - Lists all albums in database*/
		this.allAlbums.setOnAction(actionEvent -> {

			text.clear();
			List<AlbumEntity> allAlbums = albumRepos.findAll();
			if(allAlbums.isEmpty()){
				this.text.appendText("THERE ARE NO ALBUMS IN THE DATABASE.\n");
			} else {
			RestTemplate restTemplate = new RestTemplate();
			String data = restTemplate.getForEntity("http://localhost:8085/albums", String.class).getBody();
			this.text.setText(String.format("%-8s\t\n", data + "\n"));

		}});


		/*Albums - Searches by Album Name*/
		this.searchAlbum.setOnAction(actionEvent -> {
			text.clear();
			if(textField.getText().isEmpty()){
				this.text.setText("Please enter an album to insert into the database.\n");
			}else {
			RestTemplate restTemplate = new RestTemplate();
			String input = textField.getText();
			String data = restTemplate
					.postForEntity("http://localhost:8085/findTracksInAlbum/" + input, null, String.class).getBody();
			this.text.setText(data);
		}});




		/*Tracks - Searches by Album Name*/
		this.findTrack.setOnAction(actionEvent -> {
			text.clear();
			String input = textField.getText();
			RestTemplate restTemplate = new RestTemplate();
			ArrayList exist = trackRepos.findAllByTrackTitle(input);
			if (input.isEmpty()) {
				this.text.setText("Please enter a track to insert into the database.\n");
//			} else if (			exist.isEmpty()
//			) {
//				this.text.setText("Track is not in the CSV file");
			}else{
			String data = restTemplate
					.postForEntity("http://localhost:8085/findTrack/" + input, null, String.class).getBody();
			if(data == null){

			}
			this.text.setText(data);
		}});

		/* VIEW ALL CONTENTS IN DATABASE */
		//TRINH
		this.allArtists.setOnAction(actionEvent -> {
			text.clear();
			List<AlbumEntity> allAlbums = albumRepos.findAll();
			int counter = 1;

			if(allAlbums.isEmpty()){
				this.text.appendText("THERE ARE NO ALBUMS IN THE DATABASE.\n");

			} else {
				this.text.appendText("---HERE ARE THE ALBUMS IN THE DATABASE - LOCATED ON SERVER--- \n");
				for (AlbumEntity X : allAlbums) {
					text.appendText("Album No. " + counter + "\n");
					String xName = String.format("Album Name: %s\n", X.getAlbumName());
					String xGenre = String.format("Genre Type: %s\n\n", X.getGenre());
					text.appendText(xName);
					text.appendText(xGenre);
					counter++;

				}
				this.text.appendText("---END OF ALBUMS IN THE DATABASE - LOCATED ON SERVER--- \n\n\n\n");

				counter = 1;
			}
			List<TrackEntity> allTracks = trackRepos.findAll();

			if(allTracks.isEmpty()){
				this.text.appendText("THERE ARE NO TRACKS IN THE DATABASE.\n");

			}else {
				this.text.appendText("---HERE ARE THE TRACKS IN THE DATABASE - LOCATED ON SERVER--- \n");


				for (TrackEntity X : allTracks) {
					text.appendText("Track No. " + counter + "\n");
					String tName = String.format("Track Name: %s\n", X.getTrackTitle());
					String yearPub = String.format("Year Published: %s\n\n", X.getYearPublished());
					String tLength = String.format("Track Length: %s\n", X.getTrackLength());
					String tGenre = String.format("Track Genre: %s\n", X.getGenreType());

					text.appendText(tName + tLength + tGenre + yearPub);
					counter++;

				}

				counter = 1;

				this.text.appendText("---END OF TRACKS IN THE DATABASE - LOCATED ON SERVER--- \n\n\n\n");
			}

			List<ArtistEntity> allArtist = artistRepos.findAll();

			if(allArtist.isEmpty()){
				this.text.appendText("THERE ARE NO ARTISTS IN THE DATABASE.\n");

			}else {
				this.text.appendText("---HERE ARE THE ARTISTS WE HAVE IN THE DATABASE - LOCATED ON SERVER--- \n");
				for (ArtistEntity X : allArtist) {
					text.appendText("Artist No. " + counter + "\n");
					String tName = String.format("Arist Name: %s\n", X.getArtistName());

					text.appendText(tName);
					counter++;

				}

				this.text.appendText("---END OF ARTISTS IN THE DATABASE - LOCATED ON SERVER--- \n\n\n\n");

			}
 
	});

		/*Artists - Searches by Artist Name and Inserts into Database*/
		this.searchArtist.setOnAction(actionEvent -> {
		    if(textField.getText().isEmpty()){
				this.text.setText("Please enter an artist to insert into the database.");
            }else {
				RestTemplate restTemplate = new RestTemplate();
				String input = textField.getText();

				String data = restTemplate
						.postForEntity("http://localhost:8085/insertFromCSV/" + input, null, String.class).getBody();
				this.text.setText(data);
			}
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
			if(textField.getText().isEmpty()){
				this.text.setText("Please enter an artist to delete from the database.");
			}else {
			RestTemplate restTemplate = new RestTemplate();
			String input = textField.getText();
			String deleteSpecificURL = "http://localhost:8085/deleteByArtist/" + input;
			restTemplate.delete(deleteSpecificURL);
			this.text.setText(input + " has been deleted from the database.");
			}
		});

	}

}
