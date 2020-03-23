package com.hthk.crm.web.rest;

import com.hthk.crm.domain.ProductMms;
import com.hthk.crm.service.ProductMmsService;
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
 * REST controller for managing {@link com.hthk.crm.domain.ProductMms}.
 */
@RestController
@RequestMapping("/api")
public class ProductMmsResource {

    private final Logger log = LoggerFactory.getLogger(ProductMmsResource.class);

    private static final String ENTITY_NAME = "productMms";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductMmsService productMmsService;

    public ProductMmsResource(ProductMmsService productMmsService) {
        this.productMmsService = productMmsService;
    }

    /**
     * {@code POST  /product-mms} : Create a new productMms.
     *
     * @param productMms the productMms to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productMms, or with status {@code 400 (Bad Request)} if the productMms has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-mms")
    public ResponseEntity<ProductMms> createProductMms(@Valid @RequestBody ProductMms productMms) throws URISyntaxException {
        log.debug("REST request to save ProductMms : {}", productMms);
        if (productMms.getId() != null) {
            throw new BadRequestAlertException("A new productMms cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductMms result = productMmsService.save(productMms);
        return ResponseEntity.created(new URI("/api/product-mms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /product-mms} : Updates an existing productMms.
     *
     * @param productMms the productMms to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productMms,
     * or with status {@code 400 (Bad Request)} if the productMms is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productMms couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-mms")
    public ResponseEntity<ProductMms> updateProductMms(@Valid @RequestBody ProductMms productMms) throws URISyntaxException {
        log.debug("REST request to update ProductMms : {}", productMms);
        if (productMms.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductMms result = productMmsService.save(productMms);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, productMms.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /product-mms} : get all the productMms.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productMms in body.
     */
    @GetMapping("/product-mms")
    public ResponseEntity<List<ProductMms>> getAllProductMms(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("product-is-null".equals(filter)) {
            log.debug("REST request to get all ProductMmss where product is null");
            return new ResponseEntity<>(productMmsService.findAllWhereProductIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ProductMms");
        Page<ProductMms> page = productMmsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /product-mms/:id} : get the "id" productMms.
     *
     * @param id the id of the productMms to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productMms, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-mms/{id}")
    public ResponseEntity<ProductMms> getProductMms(@PathVariable String id) {
        log.debug("REST request to get ProductMms : {}", id);
        Optional<ProductMms> productMms = productMmsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productMms);
    }

    /**
     * {@code DELETE  /product-mms/:id} : delete the "id" productMms.
     *
     * @param id the id of the productMms to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-mms/{id}")
    public ResponseEntity<Void> deleteProductMms(@PathVariable String id) {
        log.debug("REST request to delete ProductMms : {}", id);
        productMmsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
