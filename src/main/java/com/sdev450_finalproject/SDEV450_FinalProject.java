package com.sdev450_finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


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
//Begin Class SDEV450_FinalProject
 
@SpringBootApplication
@EnableJpaRepositories
public class SDEV450_FinalProject {

	public static void main(String[] args) {
		SpringApplication.run(SDEV450_FinalProject.class, args);
	}

}

