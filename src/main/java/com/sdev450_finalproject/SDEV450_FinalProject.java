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
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//Begin Class SDEV450_FinalProject
 

@EnableJpaRepositories
public class SDEV450_FinalProject extends Application {

	private ConfigurableApplicationContext context;
	private Parent rootNode;


		@Override
		public void init() throws Exception {
			ApplicationContextInitializer<GenericApplicationContext> initializer =
					ac -> {
			ac.registerBean(Application.class, () -> SDEV450_FinalProject.this);
			ac.registerBean(Parameters.class, this::getParameters);
			ac.registerBean(HostServices.class, this::getHostServices);
		};
			SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder();
			springApplicationBuilder.sources(SpringEntry.class);
			springApplicationBuilder.initializers(initializer);
			this.context = springApplicationBuilder
					.run(getParameters().getRaw().toArray(new String[0]));
		}



	@Override
	public void start(Stage primaryStage) throws Exception {
		this.context.publishEvent(new StageReadyEvent( primaryStage));
		
		
	}

	class StageReadyEvent extends ApplicationEvent {

		public Stage getStage(){
			return Stage.class.cast( getSource());
		}
		public StageReadyEvent(Stage source) {
			super(source);
		}
	}

	@Override
	public void stop() throws Exception {
		this.context.close();
		Platform.exit();
	}
}

