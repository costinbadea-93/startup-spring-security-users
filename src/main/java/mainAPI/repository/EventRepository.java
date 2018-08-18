package mainAPI.repository;

import mainAPI.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by cbadea on 4/2/2018.
 */
public interface EventRepository extends PagingAndSortingRepository<Event, Integer> {

}
