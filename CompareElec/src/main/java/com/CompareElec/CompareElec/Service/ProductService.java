package com.CompareElec.CompareElec.Service;


import com.CompareElec.CompareElec.DTO.Product.ProductCreateRequest;
import com.CompareElec.CompareElec.domain.Product;
import com.CompareElec.CompareElec.domain.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductThumbnailService productThumbnailService;


    @Transactional
    public Long addProduct(ProductCreateRequest request, List<MultipartFile> images) {
        Product product = new Product(request.getName(),
                request.getPrice(),
                request.getBrand(),
                request.getEnergy_efficiency(),
                request.getSize(),
                request.getPower_consume(),
                request.getWeight(),
                request.getType(),
                request.getProduct_type(),
                request.getVideo_quality(),
                request.getVolume(),
                request.getEtc_info());

        productRepository.save(product);
        productThumbnailService.uploadThumbnail(product,images);
        return product.getProductid();
    }
}
