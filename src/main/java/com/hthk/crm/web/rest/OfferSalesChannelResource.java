package com.hthk.crm.web.rest;

import com.hthk.crm.domain.OfferSalesChannel;
import com.hthk.crm.service.OfferSalesChannelService;
import com.hthk.crm.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.hthk.crm.domain.OfferSalesChannel}.
 */
@RestController
@RequestMapping("/api")
public class OfferSalesChannelResource {

    private final Logger log = LoggerFactory.getLogger(OfferSalesChannelResource.class);

    private static final String ENTITY_NAME = "offerSalesChannel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfferSalesChannelService offerSalesChannelService;

    public OfferSalesChannelResource(OfferSalesChannelService offerSalesChannelService) {
        this.offerSalesChannelService = offerSalesChannelService;
    }

    /**
     * {@code POST  /offer-sales-channels} : Create a new offerSalesChannel.
     *
     * @param offerSalesChannel the offerSalesChannel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offerSalesChannel, or with status {@code 400 (Bad Request)} if the offerSalesChannel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offer-sales-channels")
    public ResponseEntity<OfferSalesChannel> createOfferSalesChannel(@RequestBody OfferSalesChannel offerSalesChannel) throws URISyntaxException {
        log.debug("REST request to save OfferSalesChannel : {}", offerSalesChannel);
        if (offerSalesChannel.getId() != null) {
            throw new BadRequestAlertException("A new offerSalesChannel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfferSalesChannel result = offerSalesChannelService.save(offerSalesChannel);
        return ResponseEntity.created(new URI("/api/offer-sales-channels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offer-sales-channels} : Updates an existing offerSalesChannel.
     *
     * @param offerSalesChannel the offerSalesChannel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerSalesChannel,
     * or with status {@code 400 (Bad Request)} if the offerSalesChannel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offerSalesChannel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offer-sales-channels")
    public ResponseEntity<OfferSalesChannel> updateOfferSalesChannel(@RequestBody OfferSalesChannel offerSalesChannel) throws URISyntaxException {
        log.debug("REST request to update OfferSalesChannel : {}", offerSalesChannel);
        if (offerSalesChannel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OfferSalesChannel result = offerSalesChannelService.save(offerSalesChannel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offerSalesChannel.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /offer-sales-channels} : get all the offerSalesChannels.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offerSalesChannels in body.
     */
    @GetMapping("/offer-sales-channels")
    public ResponseEntity<List<OfferSalesChannel>> getAllOfferSalesChannels(Pageable pageable) {
        log.debug("REST request to get a page of OfferSalesChannels");
        Page<OfferSalesChannel> page = offerSalesChannelService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /offer-sales-channels/:id} : get the "id" offerSalesChannel.
     *
     * @param id the id of the offerSalesChannel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerSalesChannel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offer-sales-channels/{id}")
    public ResponseEntity<OfferSalesChannel> getOfferSalesChannel(@PathVariable String id) {
        log.debug("REST request to get OfferSalesChannel : {}", id);
        Optional<OfferSalesChannel> offerSalesChannel = offerSalesChannelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(offerSalesChannel);
    }

    /**
     * {@code DELETE  /offer-sales-channels/:id} : delete the "id" offerSalesChannel.
     *
     * @param id the id of the offerSalesChannel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offer-sales-channels/{id}")
    public ResponseEntity<Void> deleteOfferSalesChannel(@PathVariable String id) {
        log.debug("REST request to delete OfferSalesChannel : {}", id);
        offerSalesChannelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
