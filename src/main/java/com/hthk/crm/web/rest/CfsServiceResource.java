package com.hthk.crm.web.rest;

import com.hthk.crm.domain.CfsService;
import com.hthk.crm.service.CfsServiceService;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.hthk.crm.domain.CfsService}.
 */
@RestController
@RequestMapping("/api")
public class CfsServiceResource {

    private final Logger log = LoggerFactory.getLogger(CfsServiceResource.class);

    private static final String ENTITY_NAME = "cfsService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CfsServiceService cfsServiceService;

    public CfsServiceResource(CfsServiceService cfsServiceService) {
        this.cfsServiceService = cfsServiceService;
    }

    /**
     * {@code POST  /cfs-services} : Create a new cfsService.
     *
     * @param cfsService the cfsService to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cfsService, or with status {@code 400 (Bad Request)} if the cfsService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cfs-services")
    public ResponseEntity<CfsService> createCfsService(@Valid @RequestBody CfsService cfsService) throws URISyntaxException {
        log.debug("REST request to save CfsService : {}", cfsService);
        if (cfsService.getId() != null) {
            throw new BadRequestAlertException("A new cfsService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CfsService result = cfsServiceService.save(cfsService);
        return ResponseEntity.created(new URI("/api/cfs-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cfs-services} : Updates an existing cfsService.
     *
     * @param cfsService the cfsService to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cfsService,
     * or with status {@code 400 (Bad Request)} if the cfsService is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cfsService couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cfs-services")
    public ResponseEntity<CfsService> updateCfsService(@Valid @RequestBody CfsService cfsService) throws URISyntaxException {
        log.debug("REST request to update CfsService : {}", cfsService);
        if (cfsService.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CfsService result = cfsServiceService.save(cfsService);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cfsService.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cfs-services} : get all the cfsServices.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cfsServices in body.
     */
    @GetMapping("/cfs-services")
    public ResponseEntity<List<CfsService>> getAllCfsServices(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("product-is-null".equals(filter)) {
            log.debug("REST request to get all CfsServices where product is null");
            return new ResponseEntity<>(cfsServiceService.findAllWhereProductIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of CfsServices");
        Page<CfsService> page = cfsServiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cfs-services/:id} : get the "id" cfsService.
     *
     * @param id the id of the cfsService to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cfsService, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cfs-services/{id}")
    public ResponseEntity<CfsService> getCfsService(@PathVariable String id) {
        log.debug("REST request to get CfsService : {}", id);
        Optional<CfsService> cfsService = cfsServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cfsService);
    }

    /**
     * {@code DELETE  /cfs-services/:id} : delete the "id" cfsService.
     *
     * @param id the id of the cfsService to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cfs-services/{id}")
    public ResponseEntity<Void> deleteCfsService(@PathVariable String id) {
        log.debug("REST request to delete CfsService : {}", id);
        cfsServiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
