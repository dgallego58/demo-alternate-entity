package co.com.dgallego58.api;

import co.com.dgallego58.api.commands.ActiveDTO;
import co.com.dgallego58.api.commands.BetweenDateRequest;
import co.com.dgallego58.services.ActiveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/actives")
public class ActiveController {

    private final ActiveService activeService;

    public ActiveController(final ActiveService activeService) {
        this.activeService = activeService;
    }

    @PostMapping(path = "/_new")
    public ResponseEntity<ActiveDTO> create(@RequestBody ActiveDTO activeDTO) {
        var resp = activeService.create(activeDTO);
        return ResponseEntity.ok(resp);
    }

    @PutMapping(path = "/_update")
    public ResponseEntity<ActiveDTO> update(@RequestBody ActiveDTO activeDTO) {
        var result = activeService.update(activeDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/by-type")
    public ResponseEntity<List<ActiveDTO>> allByType(@RequestParam(value = "type") String type) {
        var activeDTOS = activeService.activesByType(type);
        return ResponseEntity.ok(activeDTOS);
    }

    @PostMapping(path = "/by-date")
    public ResponseEntity<List<ActiveDTO>> allByType(@RequestBody BetweenDateRequest betweenDateRequest) {

        var activeDTOS = activeService.activesByDateBetween(betweenDateRequest.from(), betweenDateRequest.to());
        return ResponseEntity.ok(activeDTOS);
    }
}
