package com.hthk.crm.web.rest;

import com.hthk.crm.domain.ProductSimType;
import com.hthk.crm.service.ProductSimTypeService;
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

/**
 * REST controller for managing {@link com.hthk.crm.domain.ProductSimType}.
 */
@RestController
@RequestMapping("/api")
public class ProductSimTypeResource {

    private final Logger log = LoggerFactory.getLogger(ProductSimTypeResource.class);

    private static final String ENTITY_NAME = "productSimType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductSimTypeService productSimTypeService;

    public ProductSimTypeResource(ProductSimTypeService productSimTypeService) {
        this.productSimTypeService = productSimTypeService;
    }

    /**
     * {@code POST  /product-sim-types} : Create a new productSimType.
     *
     * @param productSimType the productSimType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productSimType, or with status {@code 400 (Bad Request)} if the productSimType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-sim-types")
    public ResponseEntity<ProductSimType> createProductSimType(@Valid @RequestBody ProductSimType productSimType) throws URISyntaxException {
        log.debug("REST request to save ProductSimType : {}", productSimType);
        if (productSimType.getId() != null) {
            throw new BadRequestAlertException("A new productSimType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductSimType result = productSimTypeService.save(productSimType);
        return ResponseEntity.created(new URI("/api/product-sim-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-sim-types} : Updates an existing productSimType.
     *
     * @param productSimType the productSimType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productSimType,
     * or with status {@code 400 (Bad Request)} if the productSimType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productSimType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-sim-types")
    public ResponseEntity<ProductSimType> updateProductSimType(@Valid @RequestBody ProductSimType productSimType) throws URISyntaxException {
        log.debug("REST request to update ProductSimType : {}", productSimType);
        if (productSimType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductSimType result = productSimTypeService.save(productSimType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productSimType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-sim-types} : get all the productSimTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productSimTypes in body.
     */
    @GetMapping("/product-sim-types")
    public ResponseEntity<List<ProductSimType>> getAllProductSimTypes(Pageable pageable) {
        log.debug("REST request to get a page of ProductSimTypes");
        Page<ProductSimType> page = productSimTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-sim-types/:id} : get the "id" productSimType.
     *
     * @param id the id of the productSimType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productSimType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-sim-types/{id}")
    public ResponseEntity<ProductSimType> getProductSimType(@PathVariable String id) {
        log.debug("REST request to get ProductSimType : {}", id);
        Optional<ProductSimType> productSimType = productSimTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productSimType);
    }

    /**
     * {@code DELETE  /product-sim-types/:id} : delete the "id" productSimType.
     *
     * @param id the id of the productSimType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-sim-types/{id}")
    public ResponseEntity<Void> deleteProductSimType(@PathVariable String id) {
        log.debug("REST request to delete ProductSimType : {}", id);
        productSimTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
