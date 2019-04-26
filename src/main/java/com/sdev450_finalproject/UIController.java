package com.sdev450_finalproject;

import com.sdev450_finalproject.persistance.Album.AlbumRepository;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class UIController {
	
	@Autowired
	AlbumRepository albumRepos;

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
			RestTemplate restTemplate = new RestTemplate();
			String data = restTemplate.getForEntity("http://localhost:8085/albums", String.class).getBody();
			this.text.setText(String.format("%-8s\t\n", data + "\n"));
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

		/*Artists - Lists all Artists in database*/
		this.allArtists.setOnAction(actionEvent -> {
			RestTemplate restTemplate = new RestTemplate();
			String data = restTemplate.getForEntity("http://localhost:8085/artists", String.class).getBody();
			this.text.setText(data);
		});

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
