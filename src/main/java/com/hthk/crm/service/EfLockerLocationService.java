package com.hthk.crm.service;

import com.hthk.crm.domain.EfLockerLocation;
import com.hthk.crm.repository.EfLockerLocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link EfLockerLocation}.
 */
@Service
public class EfLockerLocationService {

    private final Logger log = LoggerFactory.getLogger(EfLockerLocationService.class);

    private final EfLockerLocationRepository efLockerLocationRepository;

    public EfLockerLocationService(EfLockerLocationRepository efLockerLocationRepository) {
        this.efLockerLocationRepository = efLockerLocationRepository;
    }

    /**
     * Save a efLockerLocation.
     *
     * @param efLockerLocation the entity to save.
     * @return the persisted entity.
     */
    public EfLockerLocation save(EfLockerLocation efLockerLocation) {
        log.debug("Request to save EfLockerLocation : {}", efLockerLocation);
        return efLockerLocationRepository.save(efLockerLocation);
    }

    /**
     * Get all the efLockerLocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<EfLockerLocation> findAll(Pageable pageable) {
        log.debug("Request to get all EfLockerLocations");
        return efLockerLocationRepository.findAll(pageable);
    }


    /**
     *  Get all the efLockerLocations where SubsItemDelivery is {@code null}.
     *  @return the list of entities.
     */
    public List<EfLockerLocation> findAllWhereSubsItemDeliveryIsNull() {
        log.debug("Request to get all efLockerLocations where SubsItemDelivery is null");
        return StreamSupport
            .stream(efLockerLocationRepository.findAll().spliterator(), false)
            .filter(efLockerLocation -> efLockerLocation.getSubsItemDelivery() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one efLockerLocation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<EfLockerLocation> findOne(String id) {
        log.debug("Request to get EfLockerLocation : {}", id);
        return efLockerLocationRepository.findById(id);
    }

    /**
     * Delete the efLockerLocation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete EfLockerLocation : {}", id);
        efLockerLocationRepository.deleteById(id);
    }
}
