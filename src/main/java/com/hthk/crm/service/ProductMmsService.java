package com.hthk.crm.service;

import com.hthk.crm.domain.ProductMms;
import com.hthk.crm.repository.ProductMmsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link ProductMms}.
 */
@Service
public class ProductMmsService {

    private final Logger log = LoggerFactory.getLogger(ProductMmsService.class);

    private final ProductMmsRepository productMmsRepository;

    public ProductMmsService(ProductMmsRepository productMmsRepository) {
        this.productMmsRepository = productMmsRepository;
    }

    /**
     * Save a productMms.
     *
     * @param productMms the entity to save.
     * @return the persisted entity.
     */
    public ProductMms save(ProductMms productMms) {
        log.debug("Request to save ProductMms : {}", productMms);
        return productMmsRepository.save(productMms);
    }

    /**
     * Get all the productMms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ProductMms> findAll(Pageable pageable) {
        log.debug("Request to get all ProductMms");
        return productMmsRepository.findAll(pageable);
    }


    /**
     *  Get all the productMms where Product is {@code null}.
     *  @return the list of entities.
     */
    public List<ProductMms> findAllWhereProductIsNull() {
        log.debug("Request to get all productMms where Product is null");
        return StreamSupport
            .stream(productMmsRepository.findAll().spliterator(), false)
            .filter(productMms -> productMms.getProduct() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one productMms by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ProductMms> findOne(String id) {
        log.debug("Request to get ProductMms : {}", id);
        return productMmsRepository.findById(id);
    }

    /**
     * Delete the productMms by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete ProductMms : {}", id);
        productMmsRepository.deleteById(id);
    }
}
