package com.sdev450_finalproject;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UIController {

    private final HostServices hostServices;

    UIController(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @FXML
    public Label label;

    @FXML
    public Button button;

    @FXML
    public HBox buttonBox;

    @FXML
    public TextArea text;

    @FXML
    public Button searchAlbum;

    @FXML
    public TextField textField;

    @FXML
    public void initialize() {
        this.button.setOnAction( actionEvent -> {
            RestTemplate restTemplate = new RestTemplate();
            String data = restTemplate.getForEntity("http://localhost:8085/albums", String.class ).getBody();
            this.text.setText(data);
            }
        );
        this.searchAlbum.setOnAction( actionEvent -> {
                    RestTemplate restTemplate = new RestTemplate();
                    String input = textField.getText();
                    String data = restTemplate.postForEntity("http://localhost:8085/findTracksInAlbum/"+ input, null, String.class ).getBody();
                    this.text.setText(data);
                }
        );
}

}
