package co.com.dgallego58.api;

import co.com.dgallego58.api.commands.PersonDTO;
import co.com.dgallego58.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(path = "/_new")
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO personDTO) {
        var data = personService.create(personDTO);
        return ResponseEntity.ok(data);
    }
}
