package org.dm.trainingcourses.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Booking {

    private @Id
    @GeneratedValue Long id;

    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "date_id")
    @JsonBackReference
    private Date date;

    Booking() {
    }

    public Booking(Date date, String name, String email) {
        this.date = date;
        this.name = name;
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Booking{id=%d, name='%s', email='%s', date=%s}", id, name, email, date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;

        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) &&
                Objects.equals(name, booking.name) &&
                Objects.equals(email, booking.email) &&
                Objects.equals(date, booking.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, date);
    }
}

