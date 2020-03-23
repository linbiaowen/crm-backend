package com.hthk.crm.service;

import com.hthk.crm.domain.OfferSalesChannel;
import com.hthk.crm.repository.OfferSalesChannelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OfferSalesChannel}.
 */
@Service
public class OfferSalesChannelService {

    private final Logger log = LoggerFactory.getLogger(OfferSalesChannelService.class);

    private final OfferSalesChannelRepository offerSalesChannelRepository;

    public OfferSalesChannelService(OfferSalesChannelRepository offerSalesChannelRepository) {
        this.offerSalesChannelRepository = offerSalesChannelRepository;
    }

    /**
     * Save a offerSalesChannel.
     *
     * @param offerSalesChannel the entity to save.
     * @return the persisted entity.
     */
    public OfferSalesChannel save(OfferSalesChannel offerSalesChannel) {
        log.debug("Request to save OfferSalesChannel : {}", offerSalesChannel);
        return offerSalesChannelRepository.save(offerSalesChannel);
    }

    /**
     * Get all the offerSalesChannels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<OfferSalesChannel> findAll(Pageable pageable) {
        log.debug("Request to get all OfferSalesChannels");
        return offerSalesChannelRepository.findAll(pageable);
    }

    /**
     * Get one offerSalesChannel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<OfferSalesChannel> findOne(String id) {
        log.debug("Request to get OfferSalesChannel : {}", id);
        return offerSalesChannelRepository.findById(id);
    }

    /**
     * Delete the offerSalesChannel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete OfferSalesChannel : {}", id);
        offerSalesChannelRepository.deleteById(id);
    }
}
