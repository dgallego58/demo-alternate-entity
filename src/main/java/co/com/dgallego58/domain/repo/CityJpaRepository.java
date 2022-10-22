package co.com.dgallego58.domain.repo;

import co.com.dgallego58.domain.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CityJpaRepository extends JpaRepository<City, Long> {

    Set<City> findAllByNameIn(List<String> names);
}
