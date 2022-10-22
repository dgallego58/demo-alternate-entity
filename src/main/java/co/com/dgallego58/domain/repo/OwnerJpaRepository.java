package co.com.dgallego58.domain.repo;

import co.com.dgallego58.domain.entities.Owner;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerJpaRepository extends JpaRepository<Owner, Long> {

    @EntityGraph(attributePaths = {"groupType"})
    Optional<Owner> findByGroupTypeName(String name);
}
