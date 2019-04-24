package com.sdev450_finalproject;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sdev450_finalproject.persistance.TrackEntity;



@Component
public class UIController {

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
	protected void findHandler(ActionEvent event) {

		System.out.println("this got hit");
		RestTemplate restTemplate = new RestTemplate();

		// System.out.println(UIController.textField.getText());
		// System.out.println(UIController.textField.getText()+ "11");

		String fooResourceUrl = "http://localhost:8085/findTrack/"+textField.getText();
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		
		System.out.println(response.toString());
		System.out.println(response.getBody());

		// String data = restTemplate.getForEntity("http://localhost:8085/findTrack/"+
		// UIController.textField.getText(), String.class ).getBody();
		this.text.setText(response.getBody());
		TrackEntity trackget = restTemplate.getForObject(fooResourceUrl, TrackEntity.class);
		System.out.println(trackget.toString());
		//this.text.setText(restTemplate.getForObject(fooResourceUrl, TrackEntity.class));
		System.out.println(textField.getText());

	}

	@FXML
	public void initialize() {

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
					.postForEntity("http://localhost:8085/findArtistByAlbum/" + input, null, String.class).getBody();
			this.text.setText(data);
		});

	}

}
