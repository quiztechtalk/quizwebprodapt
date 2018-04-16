package main.java.com.prodapt.quiz;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws SecurityException, IOException {
    	
    	InputStream inputStream=Application.class.getResourceAsStream("log4j.properties");
    	LogManager.getLogManager().readConfiguration(inputStream);
    	System.getProperties().put("server.port", 88);
        SpringApplication.run(Application.class, args);
    }
}