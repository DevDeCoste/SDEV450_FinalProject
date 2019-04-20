package com.sdev450_finalproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

//import javax.annotation.Resource;

@Component
public class StageListener implements ApplicationListener<SDEV450_FinalProject.StageReadyEvent> {

    StageListener(@Value("${spring.application.ui.title}") String appTitle,
                  @Value("classpath:./main.fxml") Resource resource, ApplicationContext ac) {
        this.appTitle = appTitle;
        this.fxml = resource;
        this.ac = ac;
    }

    private final String appTitle;
    private final Resource fxml;
    private final ApplicationContext ac;

    @Override
    public void onApplicationEvent(SDEV450_FinalProject.StageReadyEvent stageReadyEvent) {
        try{
        Stage stage = stageReadyEvent.getStage();
//original from DEV
//            URL url = this.fxml.getURL();
//            FXMLLoader fxmlLoader = new FXMLLoader(url);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("./main.fxml"));
        
            fxmlLoader.setControllerFactory(ac::getBean);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 500, 500);
            stage.setScene(scene);
            stage.setTitle(this.appTitle);
            stage.show();
    } catch(IOException ex) {
        throw new RuntimeException(ex);
    }



    }
}
