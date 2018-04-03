package mainAPI.repository;

import mainAPI.model.EventLocation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cbadea on 4/2/2018.
 */
public interface EventLocationRepository extends JpaRepository<EventLocation, Integer>{
    EventLocation findById(int id);
}
