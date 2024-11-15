package com.CompareElec.CompareElec.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;

    private String name;
    private Integer price;
    private String brand;
    private Integer energy_efficiency;
    private String size;
    private Float power_consume;
    private Float weight;
    private String type;
    private String product_type;
    private String video_quality;
    private Float volume;
    private String etc_info;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Review> reviews = new ArrayList<>();


    public Product(String name,Integer price,String brand,Integer energy_efficiency,String size,Float power_consume,Float weight,String type,String product_type,String video_quality,Float volume,String etc_info) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.energy_efficiency = energy_efficiency;
        this.size = size;
        this.power_consume = power_consume;
        this.weight = weight;
        this.type = type;
        this.product_type = product_type;
        this.video_quality = video_quality;
        this.volume = volume;
        this.etc_info = etc_info;
    }
}
