package com.CompareElec.CompareElec.DTO.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductCreateRequest {
    private String name;
    private int price;
    private String brand;
    private int energy_efficiency;
    private String size;
    private float power_consume;
    private float weight;
    private String type;
    private String product_type;
    private String video_quality;
    private float volume;
    private String etc_info;
}
