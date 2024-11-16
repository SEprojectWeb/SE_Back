package com.CompareElec.CompareElec.domain;

import com.CompareElec.CompareElec.domain.IMG.ProductThumbnail;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;

    private String name;
    private Integer price;
    private String brand;

    @Column(name = "energy_efficiency")
    private Integer energyEfficiency;

    private String size;

    @Column(name = "power_consume")
    private Float powerConsume;

    private Float weight;
    private String type;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "video_quality")
    private String videoQuality;

    private Float volume;

    @Column(name = "etc_info")
    private String etcInfo;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<ProductThumbnail> thumbnails = new ArrayList<>();

    public Product(String name,Integer price,String brand,Integer energy_efficiency,String size,Float power_consume,Float weight,String type,String product_type,String video_quality,Float volume,String etc_info) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.energyEfficiency = energy_efficiency;
        this.size = size;
        this.powerConsume = power_consume;
        this.weight = weight;
        this.type = type;
        this.productType = product_type;
        this.videoQuality = video_quality;
        this.volume = volume;
        this.etcInfo = etc_info;
    }
}
