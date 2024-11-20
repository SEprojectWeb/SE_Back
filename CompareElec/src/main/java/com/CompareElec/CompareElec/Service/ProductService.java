package com.CompareElec.CompareElec.Service;


import com.CompareElec.CompareElec.DTO.Product.ProductComparison;
import com.CompareElec.CompareElec.DTO.Product.ProductCreateRequest;
import com.CompareElec.CompareElec.DTO.Response.ProductInfo;
import com.CompareElec.CompareElec.domain.IMG.ProductThumbnail;
import com.CompareElec.CompareElec.domain.IMG.ProductThumbnailRepository;
import com.CompareElec.CompareElec.domain.Product;
import com.CompareElec.CompareElec.domain.ProductRepository;
import com.CompareElec.CompareElec.domain.Review;
import com.CompareElec.CompareElec.domain.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductThumbnailService productThumbnailService;
    @Autowired
    ProductThumbnailRepository productThumbnailRepository;
    @Autowired
    ReviewRepository reviewRepository;


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

    @Transactional
    public List<ProductInfo> showProducts(String producttype) {
        List<Product> products = productRepository.findAllByProductType(producttype);

        // Product를 ProductResponse로 변환
        return products.stream()
                .map(product -> {
                    // ProductThumbnailRepository에서 이미지 경로 조회
                    List<String> imagePaths = productThumbnailRepository.findByProduct_Productid(product.getProductid())
                            .stream()
                            .map(ProductThumbnail::getImg_path)
                            .collect(Collectors.toList());

                    // Product와 이미지 경로 리스트를 사용하여 ProductResponse 생성
                    return new ProductInfo(product, imagePaths);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ProductInfo> searchProducts(String keyword, String productType) {
        // 키워드와 제품 타입으로 필터링된 제품 목록 조회
        List<Product> products = productRepository.findProductsByKeyword(keyword, productType);

        // 각 제품에 이미지 경로 포함
        return products.stream()
                .map(product -> {
                    // ProductThumbnailRepository에서 이미지 경로 조회
                    List<String> imagePaths = productThumbnailRepository.findByProduct_Productid(product.getProductid())
                            .stream()
                            .map(ProductThumbnail::getImg_path)
                            .collect(Collectors.toList());

                    // ProductInfo 생성
                    return new ProductInfo(product, imagePaths);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ProductComparison> compareProducts(List<String> productNames) {
        List<Product> products = productRepository.findByNameIn(productNames);

        return products.stream()
                .map(product -> {
                    // 리뷰를 조회하기 위해 ReviewRepository를 사용
                    List<Review> reviews = reviewRepository.findByProduct_Productid(product.getProductid());

                    return new ProductComparison(product, reviews);
                })
                .collect(Collectors.toList());
    }
}
