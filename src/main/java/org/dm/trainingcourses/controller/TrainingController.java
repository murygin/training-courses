package org.dm.trainingcourses.controller;

import org.dm.trainingcourses.model.Booking;
import org.dm.trainingcourses.model.Date;
import org.dm.trainingcourses.model.Training;
import org.dm.trainingcourses.model.TrainingNotFoundException;
import org.dm.trainingcourses.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainingController {

    public static final String PATH_TRAININGS = "/trainings";
    public static final String PATH_DATES = "/dates";
    public static final String PATH_BOOKINGS = "/bookings";

    @Autowired
    private TrainingService trainingService;

    @GetMapping(PATH_TRAININGS)
    public List<Training> getAllTrainings() {
        return trainingService.getAll();
    }

    @GetMapping(PATH_TRAININGS + "/{id}")
    public Training getById(@PathVariable Long id) {
        return trainingService.getById(id).orElseThrow(() -> new TrainingNotFoundException(id));
    }

    @PostMapping(PATH_TRAININGS)
    @ResponseStatus(HttpStatus.CREATED)
    public Training createTraining(@RequestBody Training training) {
        return trainingService.createTraining(training);
    }

    @PostMapping(PATH_TRAININGS + "/{trainingId}" + PATH_DATES+ "/{dateId}" + PATH_BOOKINGS)
    @ResponseStatus(HttpStatus.CREATED)
    public Date createBooking(@PathVariable Long trainingId, @PathVariable Long dateId, @RequestBody Booking booking) {
        return trainingService.createBooking(trainingId, dateId, booking);
    }
}
