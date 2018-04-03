package mainAPI.repository;


import mainAPI.model.EventReservation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cbadea on 4/3/2018.
 */
public interface EventReservationRepository extends JpaRepository<EventReservation,Integer> {
}
