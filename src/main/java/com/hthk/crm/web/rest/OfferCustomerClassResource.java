package com.hthk.crm.web.rest;

import com.hthk.crm.domain.OfferCustomerClass;
import com.hthk.crm.service.OfferCustomerClassService;
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
 * REST controller for managing {@link com.hthk.crm.domain.OfferCustomerClass}.
 */
@RestController
@RequestMapping("/api")
public class OfferCustomerClassResource {

    private final Logger log = LoggerFactory.getLogger(OfferCustomerClassResource.class);

    private static final String ENTITY_NAME = "offerCustomerClass";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfferCustomerClassService offerCustomerClassService;

    public OfferCustomerClassResource(OfferCustomerClassService offerCustomerClassService) {
        this.offerCustomerClassService = offerCustomerClassService;
    }

    /**
     * {@code POST  /offer-customer-classes} : Create a new offerCustomerClass.
     *
     * @param offerCustomerClass the offerCustomerClass to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offerCustomerClass, or with status {@code 400 (Bad Request)} if the offerCustomerClass has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offer-customer-classes")
    public ResponseEntity<OfferCustomerClass> createOfferCustomerClass(@RequestBody OfferCustomerClass offerCustomerClass) throws URISyntaxException {
        log.debug("REST request to save OfferCustomerClass : {}", offerCustomerClass);
        if (offerCustomerClass.getId() != null) {
            throw new BadRequestAlertException("A new offerCustomerClass cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfferCustomerClass result = offerCustomerClassService.save(offerCustomerClass);
        return ResponseEntity.created(new URI("/api/offer-customer-classes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offer-customer-classes} : Updates an existing offerCustomerClass.
     *
     * @param offerCustomerClass the offerCustomerClass to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerCustomerClass,
     * or with status {@code 400 (Bad Request)} if the offerCustomerClass is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offerCustomerClass couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offer-customer-classes")
    public ResponseEntity<OfferCustomerClass> updateOfferCustomerClass(@RequestBody OfferCustomerClass offerCustomerClass) throws URISyntaxException {
        log.debug("REST request to update OfferCustomerClass : {}", offerCustomerClass);
        if (offerCustomerClass.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OfferCustomerClass result = offerCustomerClassService.save(offerCustomerClass);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offerCustomerClass.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /offer-customer-classes} : get all the offerCustomerClasses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offerCustomerClasses in body.
     */
    @GetMapping("/offer-customer-classes")
    public ResponseEntity<List<OfferCustomerClass>> getAllOfferCustomerClasses(Pageable pageable) {
        log.debug("REST request to get a page of OfferCustomerClasses");
        Page<OfferCustomerClass> page = offerCustomerClassService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /offer-customer-classes/:id} : get the "id" offerCustomerClass.
     *
     * @param id the id of the offerCustomerClass to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerCustomerClass, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offer-customer-classes/{id}")
    public ResponseEntity<OfferCustomerClass> getOfferCustomerClass(@PathVariable String id) {
        log.debug("REST request to get OfferCustomerClass : {}", id);
        Optional<OfferCustomerClass> offerCustomerClass = offerCustomerClassService.findOne(id);
        return ResponseUtil.wrapOrNotFound(offerCustomerClass);
    }

    /**
     * {@code DELETE  /offer-customer-classes/:id} : delete the "id" offerCustomerClass.
     *
     * @param id the id of the offerCustomerClass to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offer-customer-classes/{id}")
    public ResponseEntity<Void> deleteOfferCustomerClass(@PathVariable String id) {
        log.debug("REST request to delete OfferCustomerClass : {}", id);
        offerCustomerClassService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
