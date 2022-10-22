package co.com.dgallego58.api.commands;

import java.time.Instant;

public record ErrorResponseDTO(String cause, Instant instant) {
}
