package org.dm.trainingcourses.configuration;

import org.dm.trainingcourses.model.Training;
import org.dm.trainingcourses.model.TrainingFactory;
import org.dm.trainingcourses.repository.TrainingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DatabaseConfiguration {

    private final Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Autowired
    private TrainingRepository trainingRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> initTrainings();
    }

    private void initTrainings() {
        logger.info("Initializing database with training data...");
        saveTraining(new Training("Java Basics", "Learn the basics of Java programming.",new BigDecimal("199.99"), "John Doe"));
        saveTraining(new Training("Spring Boot", "Build web applications with Spring Boot.", new BigDecimal("299.99"), "Jane Smith"));
        saveTraining(TrainingFactory.createTraining());
        logger.info("Database initialization complete.");
    }

    private void saveTraining(Training training) {
        trainingRepository.save(training);
        logger.debug("Training saved: {}", training);
    }
}
