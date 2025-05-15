package org.dm.trainingcourses.model;

public class DateNotFoundException extends RuntimeException {
    public DateNotFoundException(Long id) {
        super("Date not found: " + id);
    }
}
