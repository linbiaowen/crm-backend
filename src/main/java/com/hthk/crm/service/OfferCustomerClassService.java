package com.hthk.crm.service;

import com.hthk.crm.domain.OfferCustomerClass;
import com.hthk.crm.repository.OfferCustomerClassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OfferCustomerClass}.
 */
@Service
public class OfferCustomerClassService {

    private final Logger log = LoggerFactory.getLogger(OfferCustomerClassService.class);

    private final OfferCustomerClassRepository offerCustomerClassRepository;

    public OfferCustomerClassService(OfferCustomerClassRepository offerCustomerClassRepository) {
        this.offerCustomerClassRepository = offerCustomerClassRepository;
    }

    /**
     * Save a offerCustomerClass.
     *
     * @param offerCustomerClass the entity to save.
     * @return the persisted entity.
     */
    public OfferCustomerClass save(OfferCustomerClass offerCustomerClass) {
        log.debug("Request to save OfferCustomerClass : {}", offerCustomerClass);
        return offerCustomerClassRepository.save(offerCustomerClass);
    }

    /**
     * Get all the offerCustomerClasses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<OfferCustomerClass> findAll(Pageable pageable) {
        log.debug("Request to get all OfferCustomerClasses");
        return offerCustomerClassRepository.findAll(pageable);
    }

    /**
     * Get one offerCustomerClass by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OfferCustomerClass> findOne(String id) {
        log.debug("Request to get OfferCustomerClass : {}", id);
        return offerCustomerClassRepository.findById(id);
    }

    /**
     * Delete the offerCustomerClass by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete OfferCustomerClass : {}", id);
        offerCustomerClassRepository.deleteById(id);
    }
}
