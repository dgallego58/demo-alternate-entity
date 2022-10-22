package co.com.dgallego58.services;

import co.com.dgallego58.api.commands.ActiveOwner;
import co.com.dgallego58.api.commands.PersonDTO;
import co.com.dgallego58.domain.entities.Owner;
import co.com.dgallego58.domain.entities.Person;
import co.com.dgallego58.domain.repo.GroupTypeJpaRepository;
import co.com.dgallego58.domain.repo.PersonJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Repository
@Transactional(readOnly = true)
public class PersonService {

    private static final String TYPE_NAME = ActiveOwner.PERSON.name();
    private final PersonJpaRepository personJpaRepository;
    private final GroupTypeJpaRepository groupTypeJpaRepository;

    public PersonService(final PersonJpaRepository personJpaRepository,
                         final GroupTypeJpaRepository groupTypeJpaRepository) {
        this.personJpaRepository = personJpaRepository;
        this.groupTypeJpaRepository = groupTypeJpaRepository;
    }

    @Transactional
    public PersonDTO create(PersonDTO dto) {
        var groupType = groupTypeJpaRepository.findByName(TYPE_NAME)
                                              .orElseThrow(() -> new NoSuchElementException(
                                                      "No existe el Tipo de grupo: " + TYPE_NAME));
        var owner = new Owner();
        owner.setGroupType(groupType);
        var person = new Person();
        person.setName(dto.name());
        person.setOwner(owner);
        var storedPerson = personJpaRepository.save(person);
        return new PersonDTO(storedPerson.getId(), storedPerson.getName());
    }
}
