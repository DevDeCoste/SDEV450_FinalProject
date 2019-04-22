package com.sdev450_finalproject;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.sdev450_finalproject.persistance")
public class SpringEntry {
    public static void main(final String[] args) {
        Application.launch(SDEV450_FinalProject.class, args);
    }
}
