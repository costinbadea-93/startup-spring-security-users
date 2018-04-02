package mainAPI.repository;

import mainAPI.model.Event;
import mainAPI.model.EventDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDateRepository extends JpaRepository<EventDate, Integer> {
    EventDate findById(int id);
}

