package org.dm.trainingcourses.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Date {

    private @Id
    @GeneratedValue Long id;

    private Timestamp startDate;
    private Timestamp endDate;

    @ManyToOne
    @JoinColumn(name = "training_id")
    @JsonBackReference
    private Training training;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "date"
    )
    @JsonManagedReference
    private final Set<Booking> bookings = new HashSet<>();

    Date() {
    }

    public Date(Training training, Timestamp startDate, Timestamp endDate) {
        this.training = training;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings.clear();
        this.bookings.addAll(bookings);
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("Date{id=%d, startDate=%s, endDate=%s, training=%s}", id, startDate, endDate, training);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date date)) return false;

        return Objects.equals(id, date.id) &&
               Objects.equals(startDate, date.startDate) &&
               Objects.equals(endDate, date.endDate) &&
               Objects.equals(training, date.training);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, training);
    }
}

