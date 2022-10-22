package co.com.dgallego58.services;

import co.com.dgallego58.api.commands.ActiveOwner;
import co.com.dgallego58.api.commands.AreaDTO;
import co.com.dgallego58.domain.entities.Area;
import co.com.dgallego58.domain.entities.City;
import co.com.dgallego58.domain.entities.Owner;
import co.com.dgallego58.domain.repo.AreaJpaRepository;
import co.com.dgallego58.domain.repo.CityJpaRepository;
import co.com.dgallego58.domain.repo.GroupTypeJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Repository
@Transactional(readOnly = true)
public class AreaService {

    private static final String TYPE_NAME = ActiveOwner.AREA.name();
    private final AreaJpaRepository areaJpaRepository;
    private final GroupTypeJpaRepository groupTypeJpaRepository;
    private final CityJpaRepository cityJpaRepository;

    public AreaService(final AreaJpaRepository areaJpaRepository, final GroupTypeJpaRepository groupTypeJpaRepository,
                       final CityJpaRepository cityJpaRepository) {
        this.areaJpaRepository = areaJpaRepository;
        this.groupTypeJpaRepository = groupTypeJpaRepository;
        this.cityJpaRepository = cityJpaRepository;
    }

    @Transactional
    public AreaDTO create(AreaDTO areaDTO) {
        var cities = cityJpaRepository.findAllByNameIn(areaDTO.cities());
        var groupType = groupTypeJpaRepository.findByName(TYPE_NAME)
                                              .orElseThrow(() -> new NoSuchElementException(
                                                      "No existe el Tipo de grupo: " + TYPE_NAME));
        var owner = new Owner();
        owner.setGroupType(groupType);
        var area = new Area();
        area.setCities(cities);
        area.setName(areaDTO.name());
        area.setOwner(owner);
        var savedArea = areaJpaRepository.save(area);
        var savedCities = savedArea.getCities()
                                   .stream()
                                   .map(City::getName)
                                   .toList();
        return new AreaDTO(savedArea.getId(), savedArea.getName(), savedCities);
    }
}
