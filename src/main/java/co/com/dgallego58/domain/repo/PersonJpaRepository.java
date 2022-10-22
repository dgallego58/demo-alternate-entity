package co.com.dgallego58.domain.repo;

import co.com.dgallego58.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJpaRepository extends JpaRepository<Person, Long> {
}
