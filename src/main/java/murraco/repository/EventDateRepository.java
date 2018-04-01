package murraco.repository;

import murraco.model.Event;
import murraco.model.EventDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDateRepository extends JpaRepository<EventDate, Integer> {
}
