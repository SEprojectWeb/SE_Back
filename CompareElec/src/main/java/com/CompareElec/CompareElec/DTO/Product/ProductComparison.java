package com.CompareElec.CompareElec.DTO.Product;

import com.CompareElec.CompareElec.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

@AllArgsConstructor
public class ProductComparison {
    private Long productid;
    private String name;
    private Specs specs;
    private List<ReviewInfo> reviews;

    public ProductComparison(Product product, List<Review> reviews) {
        this.productid = product.getProductid();
        this.name = product.getName();
        this.specs = new Specs(
                product.getBrand(),
                product.getEnergyEfficiency(),
                product.getPowerConsume(),
                product.getVideoQuality(),
                product.getVolume()
        );
        this.reviews = reviews.stream()
                .map(review -> new ReviewInfo(
                        review.getReviewid(),
                        review.getContents(),
                        review.getRating()
                ))
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Specs {
        private String brand;
        private Integer energyEfficiency;
        private Float powerConsume;
        private String videoQuality;
        private Float volume;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ReviewInfo {
        private Long reviewid;
        private String content;
        private Float rating;
    }
}
