package com.hthk.crm.service;

import com.hthk.crm.domain.OfferCustomerSegment;
import com.hthk.crm.repository.OfferCustomerSegmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OfferCustomerSegment}.
 */
@Service
public class OfferCustomerSegmentService {

    private final Logger log = LoggerFactory.getLogger(OfferCustomerSegmentService.class);

    private final OfferCustomerSegmentRepository offerCustomerSegmentRepository;

    public OfferCustomerSegmentService(OfferCustomerSegmentRepository offerCustomerSegmentRepository) {
        this.offerCustomerSegmentRepository = offerCustomerSegmentRepository;
    }

    /**
     * Save a offerCustomerSegment.
     *
     * @param offerCustomerSegment the entity to save.
     * @return the persisted entity.
     */
    public OfferCustomerSegment save(OfferCustomerSegment offerCustomerSegment) {
        log.debug("Request to save OfferCustomerSegment : {}", offerCustomerSegment);
        return offerCustomerSegmentRepository.save(offerCustomerSegment);
    }

    /**
     * Get all the offerCustomerSegments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<OfferCustomerSegment> findAll(Pageable pageable) {
        log.debug("Request to get all OfferCustomerSegments");
        return offerCustomerSegmentRepository.findAll(pageable);
    }

    /**
     * Get one offerCustomerSegment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OfferCustomerSegment> findOne(String id) {
        log.debug("Request to get OfferCustomerSegment : {}", id);
        return offerCustomerSegmentRepository.findById(id);
    }

    /**
     * Delete the offerCustomerSegment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete OfferCustomerSegment : {}", id);
        offerCustomerSegmentRepository.deleteById(id);
    }
}
