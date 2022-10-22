package co.com.dgallego58.domain.entities;

import co.com.dgallego58.domain.entities.common.IdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "areas", schema = "asd_service")
public class Area implements IdEntity {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "area_name", length = 25)
    private String name;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "areas_cities", schema = "asd_service",
            joinColumns = @JoinColumn(name = "id_area"),
            inverseJoinColumns = @JoinColumn(name = "id_city")
    )
    private Set<City> cities = new HashSet<>();

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Owner owner;

    public Area() {
        //default
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Area active = (Area) o;

        return id != null && id.equals(active.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
