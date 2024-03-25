package de.gpb.eventgo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {

    @Query("FROM Event WHERE eventDate > NOW()")
    List<Event> findFutureEvents();
}