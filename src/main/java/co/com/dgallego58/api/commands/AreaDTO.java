package co.com.dgallego58.api.commands;

import java.util.ArrayList;
import java.util.List;

public record AreaDTO(
        Long id,
        String name,
        List<String> cities
) {
    public AreaDTO(final Long id, final String name, final List<String> cities) {
        this.id = id;
        this.name = name;
        this.cities = cities == null ? new ArrayList<>() : cities;
    }
}
