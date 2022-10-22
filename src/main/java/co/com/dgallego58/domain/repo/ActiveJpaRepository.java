package co.com.dgallego58.domain.repo;

import co.com.dgallego58.domain.entities.Active;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ActiveJpaRepository extends JpaRepository<Active, Long> {

    @EntityGraph(attributePaths = "owner.groupType")
    Optional<Active> findByInternalSerial(String internalSerial);


    List<Active> findAllByActiveType(String activeType);

    List<Active> findAllByPurchaseDateBetween(Instant startDate, Instant endDate);

    List<Active> findAllByInternalSerial(String internalSerial);
}
