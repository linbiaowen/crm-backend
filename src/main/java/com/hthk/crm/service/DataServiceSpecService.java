package com.hthk.crm.service;

import com.hthk.crm.domain.DataServiceSpec;
import com.hthk.crm.repository.DataServiceSpecRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DataServiceSpec}.
 */
@Service
public class DataServiceSpecService {

    private final Logger log = LoggerFactory.getLogger(DataServiceSpecService.class);

    private final DataServiceSpecRepository dataServiceSpecRepository;

    public DataServiceSpecService(DataServiceSpecRepository dataServiceSpecRepository) {
        this.dataServiceSpecRepository = dataServiceSpecRepository;
    }

    /**
     * Save a dataServiceSpec.
     *
     * @param dataServiceSpec the entity to save.
     * @return the persisted entity.
     */
    public DataServiceSpec save(DataServiceSpec dataServiceSpec) {
        log.debug("Request to save DataServiceSpec : {}", dataServiceSpec);
        return dataServiceSpecRepository.save(dataServiceSpec);
    }

    /**
     * Get all the dataServiceSpecs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<DataServiceSpec> findAll(Pageable pageable) {
        log.debug("Request to get all DataServiceSpecs");
        return dataServiceSpecRepository.findAll(pageable);
    }

    /**
     * Get one dataServiceSpec by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<DataServiceSpec> findOne(String id) {
        log.debug("Request to get DataServiceSpec : {}", id);
        return dataServiceSpecRepository.findById(id);
    }

    /**
     * Delete the dataServiceSpec by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete DataServiceSpec : {}", id);
        dataServiceSpecRepository.deleteById(id);
    }
}
