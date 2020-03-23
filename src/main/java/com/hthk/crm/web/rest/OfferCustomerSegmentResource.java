package com.hthk.crm.web.rest;

import com.hthk.crm.domain.OfferCustomerSegment;
import com.hthk.crm.service.OfferCustomerSegmentService;
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
 * REST controller for managing {@link com.hthk.crm.domain.OfferCustomerSegment}.
 */
@RestController
@RequestMapping("/api")
public class OfferCustomerSegmentResource {

    private final Logger log = LoggerFactory.getLogger(OfferCustomerSegmentResource.class);

    private static final String ENTITY_NAME = "offerCustomerSegment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfferCustomerSegmentService offerCustomerSegmentService;

    public OfferCustomerSegmentResource(OfferCustomerSegmentService offerCustomerSegmentService) {
        this.offerCustomerSegmentService = offerCustomerSegmentService;
    }

    /**
     * {@code POST  /offer-customer-segments} : Create a new offerCustomerSegment.
     *
     * @param offerCustomerSegment the offerCustomerSegment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offerCustomerSegment, or with status {@code 400 (Bad Request)} if the offerCustomerSegment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offer-customer-segments")
    public ResponseEntity<OfferCustomerSegment> createOfferCustomerSegment(@RequestBody OfferCustomerSegment offerCustomerSegment) throws URISyntaxException {
        log.debug("REST request to save OfferCustomerSegment : {}", offerCustomerSegment);
        if (offerCustomerSegment.getId() != null) {
            throw new BadRequestAlertException("A new offerCustomerSegment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfferCustomerSegment result = offerCustomerSegmentService.save(offerCustomerSegment);
        return ResponseEntity.created(new URI("/api/offer-customer-segments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offer-customer-segments} : Updates an existing offerCustomerSegment.
     *
     * @param offerCustomerSegment the offerCustomerSegment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerCustomerSegment,
     * or with status {@code 400 (Bad Request)} if the offerCustomerSegment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offerCustomerSegment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offer-customer-segments")
    public ResponseEntity<OfferCustomerSegment> updateOfferCustomerSegment(@RequestBody OfferCustomerSegment offerCustomerSegment) throws URISyntaxException {
        log.debug("REST request to update OfferCustomerSegment : {}", offerCustomerSegment);
        if (offerCustomerSegment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OfferCustomerSegment result = offerCustomerSegmentService.save(offerCustomerSegment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offerCustomerSegment.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /offer-customer-segments} : get all the offerCustomerSegments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offerCustomerSegments in body.
     */
    @GetMapping("/offer-customer-segments")
    public ResponseEntity<List<OfferCustomerSegment>> getAllOfferCustomerSegments(Pageable pageable) {
        log.debug("REST request to get a page of OfferCustomerSegments");
        Page<OfferCustomerSegment> page = offerCustomerSegmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /offer-customer-segments/:id} : get the "id" offerCustomerSegment.
     *
     * @param id the id of the offerCustomerSegment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerCustomerSegment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offer-customer-segments/{id}")
    public ResponseEntity<OfferCustomerSegment> getOfferCustomerSegment(@PathVariable String id) {
        log.debug("REST request to get OfferCustomerSegment : {}", id);
        Optional<OfferCustomerSegment> offerCustomerSegment = offerCustomerSegmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(offerCustomerSegment);
    }

    /**
     * {@code DELETE  /offer-customer-segments/:id} : delete the "id" offerCustomerSegment.
     *
     * @param id the id of the offerCustomerSegment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offer-customer-segments/{id}")
    public ResponseEntity<Void> deleteOfferCustomerSegment(@PathVariable String id) {
        log.debug("REST request to delete OfferCustomerSegment : {}", id);
        offerCustomerSegmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
