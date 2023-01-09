package sg.edu.nus.iss.app.workshop11;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Annotation to enable Spring Boot application
@SpringBootApplication
public class Workshop11Application {
	// Logger instance to log messages
	private static final Logger logger = LoggerFactory.getLogger(Workshop11Application.class);
	// Variable to store port number
	private static String portNumber = null;
	// Default port number
	private static final String DEFAULT_PORT="8080";

	// Main method, entry point of the application
	public static void main(String[] args) {
		// Iterate over command-line arguments
		for (String argVal : args) {
			// Log the argument value
			logger.debug("argVal : " + argVal);
			// Check if the argument is a port number
			if (argVal.contains("--port=")) {
				// Extract the port number from the argument and assign it to portNumber
				portNumber = argVal.substring(argVal.length() - 4, argVal.length());
				// Log the port number
				logger.debug("portNumber : " + portNumber);
			}
		}

		// If portNumber is still null, try to get it from an environment variable
		if (portNumber == null) {
			portNumber = System.getenv("APP_PORT");
			logger.debug("Sys ENV portNumber : " + portNumber);
		}
		
		// If portNumber is still null, use the default port number
		if (portNumber == null) {
			portNumber = DEFAULT_PORT;
		}

		// Create a new SpringApplication object
		SpringApplication app = new SpringApplication(Workshop11Application.class);
		// Set the default properties to use portNumber as the server port
		app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		// Run the Spring Boot application
		app.run(args);
	}

}
