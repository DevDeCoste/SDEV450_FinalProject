package com.sdev450_finalproject;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

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
    public void initialize() {
        this.button.setOnAction( actionEvent ->
                this.label.setText(this.hostServices.getDocumentBase()));
    }
}
