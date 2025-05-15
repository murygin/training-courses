package org.dm.trainingcourses.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public final class TrainingFactory {

    private TrainingFactory() {
        // Prevent instantiation
    }

    public static Training createTraining() {
        Training training = new Training("Microservices", "Develop microservices with Spring Cloud.", new BigDecimal("399.99"), "Alice Johnson");
        Date date1 = new Date(training, Timestamp.valueOf("2025-09-01 09:00:00"), Timestamp.valueOf("2025-09-01 17:00:00"));
        date1.addBooking(new Booking(date1, "Bernhard Buchung", "bb@email.com"));
        training.addDate(date1);
        training.addDate(new Date(training, Timestamp.valueOf("2025-10-07 09:00:00"), Timestamp.valueOf("2025-10-07 17:00:00")));
        training.addDate(new Date(training, Timestamp.valueOf("2025-10-14 09:00:00"), Timestamp.valueOf("2025-10-14 17:00:00")));
        return training;
    }
}
