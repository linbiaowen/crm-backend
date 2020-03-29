package com.hthk.crm.service;

import com.hthk.crm.domain.Image;
import com.hthk.crm.domain.Offer;
import com.hthk.crm.domain.OfferAdvancePayment;
import com.hthk.crm.domain.OfferCustomerSegment;
import com.hthk.crm.domain.OfferDiscount;
import com.hthk.crm.domain.OfferPromotion;
import com.hthk.crm.domain.Product;
import com.hthk.crm.repository.ImageRepository;
import com.hthk.crm.repository.OfferAdvancePaymentRepository;
import com.hthk.crm.repository.OfferDiscountRepository;
import com.hthk.crm.repository.OfferPromotionRepository;
import com.hthk.crm.repository.OfferRepository;
import com.hthk.crm.repository.ProductRepository;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Offer}.
 */
@Service
public class OfferService {

    private final Logger log = LoggerFactory.getLogger(OfferService.class);

    private final OfferRepository offerRepository;
    private final OfferAdvancePaymentRepository offerAdvancePaymentRepository;
    private final ProductRepository productRepository;
    private final OfferPromotionRepository offerPromotionRepository;
    private final OfferDiscountRepository offerDiscountRepository;
    private final ImageRepository imageRepository;

    public OfferService(OfferRepository offerRepository, OfferAdvancePaymentRepository offerAdvancePaymentRepository, ProductRepository productRepository, OfferPromotionRepository offerPromotionRepository, OfferDiscountRepository offerDiscountRepository, ImageRepository imageRepository) {
        this.offerRepository = offerRepository;
        this.offerAdvancePaymentRepository = offerAdvancePaymentRepository;
        this.productRepository = productRepository;
        this.offerPromotionRepository = offerPromotionRepository;
        this.offerDiscountRepository = offerDiscountRepository;
        this.imageRepository = imageRepository;
    }

    /**
     * Save a offer.
     *
     * @param offer the entity to save.
     * @return the persisted entity.
     */
    public Offer save(Offer offer) {
        log.debug("Request to save Offer : {}", offer);

        
        /**
         * testing code to setup offer advancePayment
         */
        if (StringUtils.isNotBlank(offer.getTempAdvancePaymentIds())){
            String[] advancePaymentIds = offer.getTempAdvancePaymentIds().split(",");
            for (String advancePaymentId : advancePaymentIds){
                OfferAdvancePayment offerAdvancePayment = offerAdvancePaymentRepository.findByOfferAdvancePaymentId(Long.parseLong(advancePaymentId));
                offer.addOfferAdvancePayment(offerAdvancePayment);
            }
        }

        /**
         * testing code to setup offer products.
         */
        if (StringUtils.isNotBlank(offer.getTempProductIds())){
            log.debug("offer.getProductIds()=" + offer.getTempProductIds());
            String[] productIds = offer.getTempProductIds().split(",");
            for (String productId : productIds){
                log.debug("productId=" + productId);
                Product product = productRepository.findByProdutId(productId);
                log.debug("product=" + (product == null ? "not found" : product.getProductId()));
                if (product != null){
                    offer.addProducts(product);
                }
            }
        }

        if (StringUtils.isNotBlank(offer.getTempCustomerSegments())){
            log.debug("offer.getTempCustomerSegments()=" + offer.getTempCustomerSegments());
            String[] customerSegments = offer.getTempCustomerSegments().split(",");
            for (String customerSegment : customerSegments){
                log.debug("customerSegment=" + customerSegment);
                offer.addCustomerSegments(customerSegment);
            }
        }

        if (StringUtils.isNotBlank(offer.getTempCustomerClasses())){
            log.debug("offer.getTempCustomerClasses()=" + offer.getTempCustomerClasses());
            String[] customerClasses = offer.getTempCustomerClasses().split(",");
            for (String customerClass : customerClasses){
                log.debug("customerClass=" + customerClass);
                offer.addCustomerClasses(customerClass);
            }
        }

        if (StringUtils.isNotBlank(offer.getTempSalesChannels())){
            log.debug("offer.getTempSalesChannels()=" + offer.getTempSalesChannels());
            String[] salesChannels = offer.getTempSalesChannels().split(",");
            for (String salesChannel : salesChannels){
                log.debug("salesChannel=" + salesChannel);
                offer.addSalesChannels(salesChannel);
            }
        }

        if (StringUtils.isNotBlank(offer.getTempPromoCodes())){
            String[] promoCodes = offer.getTempPromoCodes().split(",");
            for (String promoCode : promoCodes){
                OfferPromotion offerPromotion = offerPromotionRepository.findByPromoCode(promoCode);
                offer.addOfferPromotions(offerPromotion);
            }
        }

        if (StringUtils.isNotBlank(offer.getTempDiscountCodes())){
            String[] discountCodes = offer.getTempDiscountCodes().split(",");
            for (String discountCode : discountCodes){
                OfferDiscount offerDiscount = offerDiscountRepository.findByDiscountCode(discountCode);
                offer.addOfferDiscounts(offerDiscount);
            }
        }

        if (StringUtils.isNotBlank(offer.getTempImageIds())){
            String[] imageIds = offer.getTempImageIds().split(",");
            for (String imageId : imageIds){
                Image image = imageRepository.findByImageId(Long.parseLong(imageId));
                offer.addImages(image);
            }
        }


        return offerRepository.save(offer);
    }

    /**
     * Get all the offers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<Offer> findAll(Pageable pageable) {
        log.debug("Request to get all Offers");
        return offerRepository.findAll(pageable);
    }

    /**
     * Get all the offers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Offer> findAllWithEagerRelationships(Pageable pageable) {
        return offerRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one offer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<Offer> findOne(String id) {
        log.debug("Request to get Offer : {}", id);
        return offerRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the offer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Offer : {}", id);
        offerRepository.deleteById(id);
    }
}
