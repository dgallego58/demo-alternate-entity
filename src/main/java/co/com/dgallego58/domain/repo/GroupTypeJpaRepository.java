package co.com.dgallego58.domain.repo;

import co.com.dgallego58.domain.entities.GroupType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupTypeJpaRepository extends JpaRepository<GroupType, Long> {

    Optional<GroupType> findByName(String name);
}
