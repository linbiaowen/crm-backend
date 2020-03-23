package com.hthk.crm.service;

import com.hthk.crm.domain.ProductSimType;
import com.hthk.crm.repository.ProductSimTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProductSimType}.
 */
@Service
public class ProductSimTypeService {

    private final Logger log = LoggerFactory.getLogger(ProductSimTypeService.class);

    private final ProductSimTypeRepository productSimTypeRepository;

    public ProductSimTypeService(ProductSimTypeRepository productSimTypeRepository) {
        this.productSimTypeRepository = productSimTypeRepository;
    }

    /**
     * Save a productSimType.
     *
     * @param productSimType the entity to save.
     * @return the persisted entity.
     */
    public ProductSimType save(ProductSimType productSimType) {
        log.debug("Request to save ProductSimType : {}", productSimType);
        return productSimTypeRepository.save(productSimType);
    }

    /**
     * Get all the productSimTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ProductSimType> findAll(Pageable pageable) {
        log.debug("Request to get all ProductSimTypes");
        return productSimTypeRepository.findAll(pageable);
    }

    /**
     * Get one productSimType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ProductSimType> findOne(String id) {
        log.debug("Request to get ProductSimType : {}", id);
        return productSimTypeRepository.findById(id);
    }

    /**
     * Delete the productSimType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete ProductSimType : {}", id);
        productSimTypeRepository.deleteById(id);
    }
}
