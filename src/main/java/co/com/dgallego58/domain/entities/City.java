package co.com.dgallego58.domain.entities;

import co.com.dgallego58.domain.entities.common.IdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "cities", schema = "asd_service")
public class City implements IdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "city_name")
    private String name;

    public City() {
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

        final City city = (City) o;


        return id != null && id.equals(city.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
