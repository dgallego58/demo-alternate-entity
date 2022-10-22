package co.com.dgallego58.services.mapper;

import co.com.dgallego58.api.commands.ActiveDTO;
import co.com.dgallego58.api.commands.ActiveOwner;
import co.com.dgallego58.api.commands.ActiveOwnerWrapper;
import co.com.dgallego58.domain.entities.Active;
import co.com.dgallego58.domain.entities.GroupType;
import co.com.dgallego58.domain.entities.Owner;

import java.time.Instant;

public class EntityMapper {

    private final ActiveDTO activeDTO;
    private final Active entityToBeUpdated;
    private final Owner ownerRelationShip;

    public EntityMapper(ActiveDTO activeDTO,
                        Active entityToBeUpdated,
                        Owner ownerRelationShip) {
        this.activeDTO = activeDTO;
        this.entityToBeUpdated = entityToBeUpdated;
        this.ownerRelationShip = ownerRelationShip;
    }

    public static EntityMapper fromActive(ActiveDTO activeDTO,
                                          Active entityToBeUpdated,
                                          Owner ownerRelationShip){
        return new EntityMapper(activeDTO, entityToBeUpdated, ownerRelationShip);

    }

    public static ActiveDTO activeDTO(Active savedActive) {
        var ownerType = ActiveOwner.valueOf(savedActive.getOwner().getGroupType().getName());
        var activeOwner = new ActiveOwnerWrapper(savedActive.getId(), ownerType);
        return ActiveDTO.builder()
                        .setId(savedActive.getId())
                        .setActiveHeight(savedActive.getActiveHeight())
                        .setActiveLength(savedActive.getActiveLength())
                        .setActiveWeight(savedActive.getActiveWeight())
                        .setActiveName(savedActive.getActiveName())
                        .setDescription(savedActive.getDescription())
                        .setOwner(activeOwner)
                        .setStockValue(savedActive.getStockValue())
                        .setInternalSerial(savedActive.getInternalSerial())
                        .setActiveType(savedActive.getActiveType())
                        .setPurchaseDate(savedActive.getPurchaseDate())
                        .build();
    }

    public static Owner fromGroupType(GroupType groupType) {
        var owner = new Owner();
        owner.setGroupType(groupType);
        return owner;
    }

    public static Active newActive(ActiveDTO activeDTO, Owner owner) {
        var active = new Active();
        active.setId(activeDTO.getId());
        active.setActiveHeight(activeDTO.getActiveHeight());
        active.setActiveLength(activeDTO.getActiveLength());
        active.setActiveWeight(activeDTO.getActiveWeight());
        active.setActiveName(activeDTO.getActiveName());
        active.setDescription(activeDTO.getDescription());
        active.setOwner(owner);
        active.setStockValue(activeDTO.getStockValue());
        active.setInternalSerial(activeDTO.getInternalSerial());
        active.setActiveType(activeDTO.getActiveType());
        active.setPurchaseDate(Instant.now());
        return active;
    }

    public Active updateActive() {
        this.entityToBeUpdated.setId(activeDTO.getId());
        this.entityToBeUpdated.setActiveHeight(activeDTO.getActiveHeight());
        this.entityToBeUpdated.setActiveLength(activeDTO.getActiveLength());
        this.entityToBeUpdated.setActiveWeight(activeDTO.getActiveWeight());
        this.entityToBeUpdated.setActiveName(activeDTO.getActiveName());
        this.entityToBeUpdated.setDescription(activeDTO.getDescription());
        this.entityToBeUpdated.setOwner(ownerRelationShip);
        this.entityToBeUpdated.setStockValue(activeDTO.getStockValue());
        this.entityToBeUpdated.setInternalSerial(activeDTO.getInternalSerial());
        this.entityToBeUpdated.setActiveType(activeDTO.getActiveType());
        return entityToBeUpdated;
    }
}
