package de.gpb.eventgo;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long participantId;
    @Column
    private String name;

    @ManyToMany(mappedBy = "participant")
    Set<Event> events;

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long id) {
        this.participantId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
