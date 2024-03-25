package de.gpb.eventgo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long eventId;
    @Column
    private String name;
    @Column
    private String createdBy;
    @Column
    private LocalDate createdAt = LocalDate.now();
    @Column
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate eventDate;
    @Column
    @Size(max = 255)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    Set<Participant> participant;



    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long id) {
        this.eventId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(Set<Participant> participant) {
        this.participant = participant;
    }
}

