package co.com.dgallego58.api;

import co.com.dgallego58.api.commands.AreaDTO;
import co.com.dgallego58.services.AreaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/area")
public class AreaController {

    private final AreaService areaService;

    public AreaController(final AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping(path = "/_new")
    public ResponseEntity<AreaDTO> create(@RequestBody AreaDTO areaDTO) {
        return ResponseEntity.ok(areaService.create(areaDTO));
    }
}
