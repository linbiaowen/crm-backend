package com.hthk.crm.service;

import com.hthk.crm.domain.CfsService;
import com.hthk.crm.domain.DataServiceSpec;
import com.hthk.crm.domain.VoiceServiceSpec;
import com.hthk.crm.repository.CfsServiceRepository;
import com.hthk.crm.repository.DataServiceSpecRepository;
import com.hthk.crm.repository.VoiceServiceSpecRepository;

import org.apache.commons.lang3.StringUtils;
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
 * Service Implementation for managing {@link CfsService}.
 */
@Service
public class CfsServiceService {

    private final Logger log = LoggerFactory.getLogger(CfsServiceService.class);

    private final CfsServiceRepository cfsServiceRepository;
    private final DataServiceSpecRepository dataServiceSpecRepository;
    private final VoiceServiceSpecRepository voiceServiceSpecRepository;

    public CfsServiceService(CfsServiceRepository cfsServiceRepository, DataServiceSpecRepository dataServiceSpecRepository, VoiceServiceSpecRepository voiceServiceSpecRepository) {
        this.cfsServiceRepository = cfsServiceRepository;
        this.dataServiceSpecRepository = dataServiceSpecRepository;
        this.voiceServiceSpecRepository = voiceServiceSpecRepository;
    }

    /**
     * Save a cfsService.
     *
     * @param cfsService the entity to save.
     * @return the persisted entity.
     */
    public CfsService save(CfsService cfsService) {
        log.debug("Request to save CfsService : {}", cfsService);

        if (StringUtils.isNotBlank(cfsService.getTempDataSpecIds())){
            DataServiceSpec dataServiceSpec = dataServiceSpecRepository.findByDataSpecId(cfsService.getTempDataSpecIds());
             cfsService.setDataServiceSpec(dataServiceSpec);
        }

        if (StringUtils.isNotBlank(cfsService.getTempVoiceSpecIds())){
            VoiceServiceSpec voiceServiceSpec = voiceServiceSpecRepository.findByVoiceSpecId(cfsService.getTempVoiceSpecIds());
             cfsService.setVoiceServiceSpec(voiceServiceSpec);
        }

        return cfsServiceRepository.save(cfsService);
    }

    /**
     * Get all the cfsServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<CfsService> findAll(Pageable pageable) {
        log.debug("Request to get all CfsServices");
        return cfsServiceRepository.findAll(pageable);
    }


    /**
     *  Get all the cfsServices where Product is {@code null}.
     *  @return the list of entities.
     */
    public List<CfsService> findAllWhereProductIsNull() {
        log.debug("Request to get all cfsServices where Product is null");
        return StreamSupport
            .stream(cfsServiceRepository.findAll().spliterator(), false)
            .filter(cfsService -> cfsService.getProduct() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one cfsService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<CfsService> findOne(String id) {
        log.debug("Request to get CfsService : {}", id);
        return cfsServiceRepository.findById(id);
    }

    /**
     * Delete the cfsService by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete CfsService : {}", id);
        cfsServiceRepository.deleteById(id);
    }
}
