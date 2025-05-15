package org.dm.trainingcourses.model;

public class TrainingNotFoundException extends RuntimeException {
    public TrainingNotFoundException(Long id) {
        super("Training not found: " + id);
    }
}
