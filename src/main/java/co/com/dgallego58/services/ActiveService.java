package co.com.dgallego58.services;

import co.com.dgallego58.api.commands.ActiveDTO;
import co.com.dgallego58.domain.entities.Active;
import co.com.dgallego58.domain.entities.Owner;
import co.com.dgallego58.domain.repo.ActiveJpaRepository;
import co.com.dgallego58.domain.repo.GroupTypeJpaRepository;
import co.com.dgallego58.domain.repo.OwnerJpaRepository;
import co.com.dgallego58.services.mapper.EntityMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class ActiveService {

    private final ActiveJpaRepository activeJpaRepository;
    private final GroupTypeJpaRepository groupTypeJpaRepository;
    private final OwnerJpaRepository ownerJpaRepository;


    public ActiveService(final ActiveJpaRepository activeJpaRepository,
                         final GroupTypeJpaRepository groupTypeJpaRepository,
                         final OwnerJpaRepository ownerJpaRepository) {
        this.activeJpaRepository = activeJpaRepository;
        this.groupTypeJpaRepository = groupTypeJpaRepository;
        this.ownerJpaRepository = ownerJpaRepository;
    }

    @Transactional
    public ActiveDTO create(ActiveDTO activeDTO) {
        var gType = groupTypeJpaRepository.findByName(activeDTO.getOwner().ownerType().name())
                                          .orElseThrow(() -> new NoSuchElementException("No existe el activo"));
        Optional<Active> byInternalSerial = activeJpaRepository.findByInternalSerial(activeDTO.getInternalSerial());

        if (byInternalSerial.isPresent()) {
            Active active = byInternalSerial.get();
            if (!active.getOwner().getGroupType().equals(gType)) {
                throw new AlreadyAssignedException(
                        "Ya está asociado al tipo: " + active.getOwner().getGroupType().getName());
            }
        }
        var owner = new Owner();
        owner.setGroupType(gType);
        Active active = EntityMapper.newActive(activeDTO, owner);
        var savedActive = activeJpaRepository.save(active);
        return EntityMapper.activeDTO(savedActive);
    }

    @Transactional
    public ActiveDTO update(ActiveDTO activeDTO) {
        Optional<Active> byInternalSerial = activeJpaRepository.findByInternalSerial(activeDTO.getInternalSerial());
        return byInternalSerial.map(active -> {
                                   var newOwner = ownerJpaRepository.findById(activeDTO.getOwner().ownerId())
                                                                    .orElseThrow(
                                                                            () -> new OwnerNotFoundException("No existe el dueño del activo"));
                                   var em = EntityMapper.fromActive(activeDTO, active, newOwner);
                                   var updatedActive = em.updateActive();
                                   return activeJpaRepository.save(updatedActive);
                               })
                               .map(EntityMapper::activeDTO)
                               .orElseThrow(
                                       () -> new ActiveCouldNotBeUpdatedException("No se pudo actualizar el activo"));
    }

    public List<ActiveDTO> activesByType(String activeType) {
        var result = activeJpaRepository.findAllByActiveType(activeType)
                                        .stream()
                                        .map(EntityMapper::activeDTO)
                                        .toList();
        return validateEmptyList(result);
    }

    public <T> List<T> validateEmptyList(List<T> list) {
        if (list.isEmpty()) {
            throw new EmptyResultException("No hay resultados en la busqueda");
        }
        return list;
    }


    public List<ActiveDTO> activesByDateBetween(Instant from, Instant to) {
        var result = activeJpaRepository.findAllByPurchaseDateBetween(from, to)
                                        .stream()
                                        .map(EntityMapper::activeDTO)
                                        .toList();
        return validateEmptyList(result);
    }

    public List<ActiveDTO> activesByInternalSerial(String internalSerial) {
        var activeDTOS = activeJpaRepository.findAllByInternalSerial(internalSerial)
                                            .stream()
                                            .map(EntityMapper::activeDTO)
                                            .toList();
        return validateEmptyList(activeDTOS);
    }

    public static class EmptyResultException extends RuntimeException {
        public EmptyResultException(final String message) {
            super(message);
        }
    }


    public static class AlreadyAssignedException extends RuntimeException {
        public AlreadyAssignedException(final String message) {
            super(message);
        }
    }

    public static class OwnerNotFoundException extends RuntimeException {
        public OwnerNotFoundException(final String message) {
            super(message);
        }
    }

    public static class ActiveCouldNotBeUpdatedException extends RuntimeException {
        public ActiveCouldNotBeUpdatedException(final String message) {
            super(message);
        }
    }
}
