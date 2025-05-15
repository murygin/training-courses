package org.dm.trainingcourses.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Training {

    private @Id
    @GeneratedValue Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private String speaker;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "training"
    )
    @JsonManagedReference
    private final Set<Date> dates = new HashSet<>();

    Training() {
    }

    public Training(String name, String description, BigDecimal price, String speaker) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.speaker = speaker;
    }

    public Set<Date> getDates() {
        return dates;
    }

    public void setDates(Set<Date> dates) {
        this.dates.clear();
        this.dates.addAll(dates);
    }

    public void addDate(Date date) {
        this.dates.add(date);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    @Override
    public String toString() {
        return String.format("Training{id=%d, name='%s', description='%s', price=%s, speaker='%s'}",
                id, name, description, price, speaker);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Training training)) return false;

        return Objects.equals(getId(), training.getId()) &&
                Objects.equals(getName(), training.getName()) &&
                Objects.equals(getDescription(), training.getDescription()) &&
                Objects.equals(getPrice(), training.getPrice()) &&
                Objects.equals(getSpeaker(), training.getSpeaker());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPrice(), getSpeaker());
    }
}
