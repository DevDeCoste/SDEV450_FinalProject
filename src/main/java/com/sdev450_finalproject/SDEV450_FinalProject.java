package com.sdev450_finalproject;

/*
 * @Course: SDEV 450 ~ Java Programming III
 * @Author Name: Dev DeCoste, Trinh Nguyen, Madeline Merced
 * @Assignment Name: sdev450_finalproject
 * @Date: Jan 18, 2019
 * @Description:
 */

//Imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import org.springframework.boot.SpringApplication;
import javafx.scene.Scene;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//Begin Class SDEV450_FinalProject
 
@SpringBootApplication
@EnableJpaRepositories
public class SDEV450_FinalProject {

	public static void main(String[] args) {
		SpringApplication.run(SDEV450_FinalProject.class, args);
	}
}

//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		BorderPane bPane = new BorderPane(); //create new border pane object
//		BorderPane bPane2 = new BorderPane(); //create new border pane object
//		bPane.setPadding(new Insets(10, 10, 10, 10));
//
//		Button btTest = new Button("Test Button");
//		btTest.setPadding(new Insets(5, 15, 5, 15));
//
//		btTest.setOnAction(e -> {
//
//				});
//
//		bPane.setTop(new CustPane("Top"));
//		bPane.setCenter(btTest);
//
//
//		Scene scene = new Scene(bPane, 450, 250);
//		primaryStage.setTitle("Diet Spotify");
//		primaryStage.setResizable(false);
//		primaryStage.setScene(scene);
//		primaryStage.show();
//
//
//	} //End Start Method
//	class CustPane extends StackPane {
//
//		public CustPane(String Text) {
//			getChildren().add(new Text("Welcome. Press the Button."));
//			setStyle(" -fx-font: 18px Tahoma;");
//			setPadding(new Insets(15.5, 15.5, 15.5, 35.5));
//		}
//	} // EndCustPane
//
//}

