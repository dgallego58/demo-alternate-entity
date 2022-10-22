package co.com.dgallego58.api.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@JsonDeserialize(builder = ActiveDTO.Builder.class)
public class ActiveDTO {

    private final Long id;
    private final String description;
    private final String activeType;
    private final String internalSerial;
    private final Integer activeWeight;
    private final Integer activeHeight;
    private final Integer activeLength;
    private final BigDecimal stockValue;
    private final Instant purchaseDate;
    private final String activeName;
    private final ActiveOwnerWrapper owner;

    public ActiveDTO(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.activeType = builder.activeType;
        this.internalSerial = builder.internalSerial;
        this.activeWeight = builder.activeWeight;
        this.activeHeight = builder.activeHeight;
        this.activeLength = builder.activeLength;
        this.stockValue = builder.stockValue;
        this.purchaseDate = builder.purchaseDate;
        this.activeName = builder.activeName;
        this.owner = builder.owner;
    }

    public ActiveOwnerWrapper getOwner() {
        return owner;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getActiveType() {
        return activeType;
    }

    public String getInternalSerial() {
        return internalSerial;
    }

    public Integer getActiveWeight() {
        return activeWeight;
    }

    public Integer getActiveHeight() {
        return activeHeight;
    }

    public Integer getActiveLength() {
        return activeLength;
    }

    public BigDecimal getStockValue() {
        return stockValue;
    }

    public Instant getPurchaseDate() {
        return purchaseDate;
    }

    public String getActiveName() {
        return activeName;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private ActiveOwnerWrapper owner;
        private Long id;
        private String description;
        private String activeType;
        private Instant purchaseDate;
        private Integer activeWeight;
        private Integer activeLength;
        private String internalSerial;
        private BigDecimal stockValue;
        private Integer activeHeight;
        private String activeName;

        public ActiveDTO build() {
            return new ActiveDTO(this);
        }

        public Builder setId(final Long id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(final String description) {
            this.description = description;
            return this;
        }

        public Builder setActiveType(final String activeType) {
            this.activeType = activeType;
            return this;
        }

        public Builder setPurchaseDate(final Instant purchaseDate) {
            this.purchaseDate = purchaseDate;
            return this;
        }

        public Builder setActiveWeight(final Integer activeWeight) {
            this.activeWeight = activeWeight;
            return this;
        }

        public Builder setActiveLength(final Integer activeLength) {
            this.activeLength = activeLength;
            return this;
        }

        public Builder setInternalSerial(final String internalSerial) {
            this.internalSerial = internalSerial;
            return this;
        }

        public Builder setStockValue(final BigDecimal stockValue) {
            this.stockValue = stockValue;
            return this;
        }

        public Builder setActiveHeight(final Integer activeHeight) {
            this.activeHeight = activeHeight;
            return this;
        }

        public Builder setActiveName(final String activeName) {
            this.activeName = activeName;
            return this;
        }

        public Builder setOwner(final ActiveOwnerWrapper owner) {
            this.owner = owner;
            return this;
        }
    }
}
