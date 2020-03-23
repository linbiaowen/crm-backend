package com.hthk.crm.web.rest;

import com.hthk.crm.domain.EfLockerLocation;
import com.hthk.crm.service.EfLockerLocationService;
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
 * REST controller for managing {@link com.hthk.crm.domain.EfLockerLocation}.
 */
@RestController
@RequestMapping("/api")
public class EfLockerLocationResource {

    private final Logger log = LoggerFactory.getLogger(EfLockerLocationResource.class);

    private static final String ENTITY_NAME = "efLockerLocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EfLockerLocationService efLockerLocationService;

    public EfLockerLocationResource(EfLockerLocationService efLockerLocationService) {
        this.efLockerLocationService = efLockerLocationService;
    }

    /**
     * {@code POST  /ef-locker-locations} : Create a new efLockerLocation.
     *
     * @param efLockerLocation the efLockerLocation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new efLockerLocation, or with status {@code 400 (Bad Request)} if the efLockerLocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ef-locker-locations")
    public ResponseEntity<EfLockerLocation> createEfLockerLocation(@Valid @RequestBody EfLockerLocation efLockerLocation) throws URISyntaxException {
        log.debug("REST request to save EfLockerLocation : {}", efLockerLocation);
        if (efLockerLocation.getId() != null) {
            throw new BadRequestAlertException("A new efLockerLocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EfLockerLocation result = efLockerLocationService.save(efLockerLocation);
        return ResponseEntity.created(new URI("/api/ef-locker-locations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ef-locker-locations} : Updates an existing efLockerLocation.
     *
     * @param efLockerLocation the efLockerLocation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated efLockerLocation,
     * or with status {@code 400 (Bad Request)} if the efLockerLocation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the efLockerLocation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ef-locker-locations")
    public ResponseEntity<EfLockerLocation> updateEfLockerLocation(@Valid @RequestBody EfLockerLocation efLockerLocation) throws URISyntaxException {
        log.debug("REST request to update EfLockerLocation : {}", efLockerLocation);
        if (efLockerLocation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EfLockerLocation result = efLockerLocationService.save(efLockerLocation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, efLockerLocation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ef-locker-locations} : get all the efLockerLocations.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of efLockerLocations in body.
     */
    @GetMapping("/ef-locker-locations")
    public ResponseEntity<List<EfLockerLocation>> getAllEfLockerLocations(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("subsitemdelivery-is-null".equals(filter)) {
            log.debug("REST request to get all EfLockerLocations where subsItemDelivery is null");
            return new ResponseEntity<>(efLockerLocationService.findAllWhereSubsItemDeliveryIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of EfLockerLocations");
        Page<EfLockerLocation> page = efLockerLocationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ef-locker-locations/:id} : get the "id" efLockerLocation.
     *
     * @param id the id of the efLockerLocation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the efLockerLocation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ef-locker-locations/{id}")
    public ResponseEntity<EfLockerLocation> getEfLockerLocation(@PathVariable String id) {
        log.debug("REST request to get EfLockerLocation : {}", id);
        Optional<EfLockerLocation> efLockerLocation = efLockerLocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(efLockerLocation);
    }

    /**
     * {@code DELETE  /ef-locker-locations/:id} : delete the "id" efLockerLocation.
     *
     * @param id the id of the efLockerLocation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ef-locker-locations/{id}")
    public ResponseEntity<Void> deleteEfLockerLocation(@PathVariable String id) {
        log.debug("REST request to delete EfLockerLocation : {}", id);
        efLockerLocationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
