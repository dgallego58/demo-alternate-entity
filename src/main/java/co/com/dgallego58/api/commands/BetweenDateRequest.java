package co.com.dgallego58.api.commands;

import java.time.Instant;

public record BetweenDateRequest(Instant from, Instant to) {
}
