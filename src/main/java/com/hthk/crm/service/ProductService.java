package com.hthk.crm.service;

import com.hthk.crm.domain.CfsService;
import com.hthk.crm.domain.Image;
import com.hthk.crm.domain.Product;
import com.hthk.crm.domain.ProductData;
import com.hthk.crm.domain.ProductMms;
import com.hthk.crm.domain.ProductSms;
import com.hthk.crm.domain.ProductVoice;
import com.hthk.crm.domain.ResourceSpecification;
import com.hthk.crm.repository.CfsServiceRepository;
import com.hthk.crm.repository.ImageRepository;
import com.hthk.crm.repository.ProductDataRepository;
import com.hthk.crm.repository.ProductMmsRepository;
import com.hthk.crm.repository.ProductRepository;
import com.hthk.crm.repository.ProductSmsRepository;
import com.hthk.crm.repository.ProductVoiceRepository;
import com.hthk.crm.repository.ResourceSpecificationRepository;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Product}.
 */
@Service
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final ProductVoiceRepository productVoiceRepository;
    private final ProductDataRepository productDataRepository;
    private final ProductSmsRepository productSmsRepository;
    private final ProductMmsRepository productMmsRepository;
    private final ImageRepository imageRepository;
    private final ResourceSpecificationRepository resourceSpecificationRepository;
    private final CfsServiceRepository cfsServiceRepository;

    public ProductService(ProductRepository productRepository, ProductVoiceRepository productVoiceRepository, ProductDataRepository productDataRepository, ProductSmsRepository productSmsRepository, ProductMmsRepository productMmsRepository, ImageRepository imageRepository, ResourceSpecificationRepository resourceSpecificationRepository, CfsServiceRepository cfsServiceRepository) {
        this.productRepository = productRepository;
        this.productVoiceRepository = productVoiceRepository;
        this.productDataRepository = productDataRepository;
        this.productSmsRepository = productSmsRepository;
        this.productMmsRepository = productMmsRepository;
        this.imageRepository = imageRepository;
        this.resourceSpecificationRepository = resourceSpecificationRepository;
        this.cfsServiceRepository = cfsServiceRepository;
    }

    /**
     * Save a product.
     *
     * @param product the entity to save.
     * @return the persisted entity.
     */
    public Product save(Product product) {
        log.debug("Request to save Product : {}", product);


    //     //@DBRef
    // @Field("productVoice")
    // private ProductVoice productVoice;

    // //@DBRef
    // @Field("productData")
    // private ProductData productData;

    // //@DBRef
    // @Field("productSms")
    // private ProductSms productSms;

    // //@DBRef
    // @Field("productMms")
    // private ProductMms productMms;

    // //@DBRef
    // @Field("cfsService")
    // private CfsService cfsService;

    // //@DBRef
    // @Field("deliveryOptions")
    // private Set<String> deliveryOptions = new HashSet<>();

    // //@DBRef
    // @Field("resourceSpecifications")
    // private Set<ResourceSpecification> resourceSpecifications = new HashSet<>();

    // //@DBRef
    // @Field("images")
    // private Set<Image> images = new HashSet<>();

        if (StringUtils.isNotBlank(product.getTempDeliveryOptions())){
            log.debug("product.getTempDeliveryOptions()=" + product.getTempDeliveryOptions());
            String[] deliveryOptions = product.getTempDeliveryOptions().split(",");
            for (String deliveryOption : deliveryOptions){
                log.debug("deliveryOption=" + deliveryOption);
                product.addDeliveryOptions(deliveryOption);
            }
        }

        if (StringUtils.isNotBlank(product.getTempVoiceIds())){
            ProductVoice productVoice = productVoiceRepository.findByVoiceId(product.getTempVoiceIds());
             product.setProductVoice(productVoice);
        }

        if (StringUtils.isNotBlank(product.getTempDataIds())){
            ProductData productData = productDataRepository.findByDataId(product.getTempDataIds());
             product.setProductData(productData);
        }

        if (StringUtils.isNotBlank(product.getTempSmsIds())){
            ProductSms productSms = productSmsRepository.findBySmsId(product.getTempSmsIds());
             product.setProductSms(productSms);
        }

        if (StringUtils.isNotBlank(product.getTempMmsIds())){
            ProductMms productMms = productMmsRepository.findByMmsId(product.getTempMmsIds());
             product.setProductMms(productMms);
        }

        if (StringUtils.isNotBlank(product.getTempServiceId())){
            CfsService cfsService = cfsServiceRepository.findbyServiceId(product.getTempServiceId());
             product.setCfsService(cfsService);
        }

        if (StringUtils.isNotBlank(product.getTempImageIds())){
            String[] imageIds = product.getTempImageIds().split(",");
            for (String imageId : imageIds){
                log.debug("imageId = " + imageId);
                Image image = imageRepository.findByImageId(Long.parseLong(imageId));
                log.debug("image = " + image == null ? "can not find image." : image.getId());
                product.addImages(image);
            }
        }

        if (StringUtils.isNotBlank(product.getTempResourceSpecIds())){
            String[] resourceSpecIds = product.getTempResourceSpecIds().split(",");
            for (String resourceSpecId : resourceSpecIds){
                ResourceSpecification resourceSpecification = resourceSpecificationRepository.findByResoureSpecId(resourceSpecId);
                product.addResourceSpecifications(resourceSpecification);
            }
        }

        return productRepository.save(product);
    }

    /**
     * Get all the products.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<Product> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productRepository.findAll(pageable);
    }

    /**
     * Get one product by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<Product> findOne(String id) {
        log.debug("Request to get Product : {}", id);
        return productRepository.findById(id);
    }

    /**
     * Delete the product by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.deleteById(id);
    }
}
