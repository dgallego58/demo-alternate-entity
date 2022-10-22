package co.com.dgallego58.api.error;

import co.com.dgallego58.api.commands.ErrorResponseDTO;
import co.com.dgallego58.services.ActiveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionalHandler {

    @ExceptionHandler(value = {ActiveService.AlreadyAssignedException.class,
            ActiveService.OwnerNotFoundException.class,
            ActiveService.ActiveCouldNotBeUpdatedException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleActiveExceptions(RuntimeException ex) {
        var response = new ErrorResponseDTO(ex.getMessage(), Instant.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponseDTO> handleGeneralExceptions(Exception ex) {
        var response = new ErrorResponseDTO(ex.getMessage(), Instant.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(value = {ActiveService.EmptyResultException.class})
    public ResponseEntity<ErrorResponseDTO> handleEmptyResults(ActiveService.EmptyResultException ex) {
        var response = new ErrorResponseDTO(ex.getMessage(), Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
