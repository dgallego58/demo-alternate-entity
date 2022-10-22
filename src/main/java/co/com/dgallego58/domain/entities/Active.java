package co.com.dgallego58.domain.entities;

import co.com.dgallego58.domain.entities.common.IdEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "actives", schema = "asd_service")
public class Active implements IdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "id_owner", nullable = false)
    private Owner owner;

    @Size(max = 50)
    @NotNull
    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @Size(max = 20)
    @NotNull
    @Column(name = "active_type", nullable = false, length = 20)
    private String activeType;

    @Size(max = 25)
    @NotNull
    @Column(name = "internal_serial", nullable = false, length = 25)
    @NaturalId
    private String internalSerial;

    @NotNull
    @Column(name = "active_weight", nullable = false)
    private Integer activeWeight;

    @NotNull
    @Column(name = "active_height", nullable = false)
    private Integer activeHeight;

    @NotNull
    @Column(name = "active_length", nullable = false)
    private Integer activeLength;

    @NotNull
    @Column(name = "stock_value", nullable = false, precision = 25, scale = 2)
    private BigDecimal stockValue;

    @NotNull
    @Column(name = "purchase_date", nullable = false)
    private Instant purchaseDate;

    @Size(max = 5)
    @NotNull
    @Column(name = "active_name", nullable = false, length = 25)
    private String activeName;


    public Active() {
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

        final Active active = (Active) o;

        return id != null && id.equals(active.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
