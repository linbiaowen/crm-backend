package com.hthk.crm.service;

import com.hthk.crm.domain.Image;
import com.hthk.crm.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Image}.
 */
@Service
public class ImageService {

    private final Logger log = LoggerFactory.getLogger(ImageService.class);

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * Save a image.
     *
     * @param image the entity to save.
     * @return the persisted entity.
     */
    public Image save(Image image) {
        log.debug("Request to save Image : {}", image);
        return imageRepository.save(image);
    }

    /**
     * Get all the images.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<Image> findAll(Pageable pageable) {
        log.debug("Request to get all Images");
        return imageRepository.findAll(pageable);
    }

    /**
     * Get one image by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<Image> findOne(String id) {
        log.debug("Request to get Image : {}", id);
        return imageRepository.findById(id);
    }

    /**
     * Delete the image by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Image : {}", id);
        imageRepository.deleteById(id);
    }
}
