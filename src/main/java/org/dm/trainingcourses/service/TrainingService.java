package org.dm.trainingcourses.service;

import org.dm.trainingcourses.model.Booking;
import org.dm.trainingcourses.model.Date;
import org.dm.trainingcourses.model.DateNotFoundException;
import org.dm.trainingcourses.model.Training;
import org.dm.trainingcourses.repository.DateRepository;
import org.dm.trainingcourses.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private DateRepository dateRepository;

    public List<Training> getAll() {
        return trainingRepository.findAll();
    }

    public Optional<Training> getById(Long id) {
        return trainingRepository.findById(id);
    }

    public Training createTraining(Training training) {
        training.getDates().forEach(date -> date.setTraining(training));
        return trainingRepository.save(training);
    }

    public Date createBooking(Long trainingId, Long dateId, Booking booking) {
        Date date = dateRepository.findById(dateId).orElseThrow(() -> new DateNotFoundException(dateId));
        booking.setDate(date);
        date.addBooking(booking);
        date = dateRepository.save(date);
        return date;
    }
}
