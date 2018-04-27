package mainAPI.repository;

import mainAPI.model.RegistrationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent, Integer> {
    RegistrationEvent findById(int id);
}

