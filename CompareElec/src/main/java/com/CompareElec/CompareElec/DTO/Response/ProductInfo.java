package com.CompareElec.CompareElec.DTO.Response;

import com.CompareElec.CompareElec.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductInfo {
    private long productid;
    private String name;
    private int price;
    private String brand;
    private int energyEfficiency;
    private String size;
    private float powerConsume;
    private float weight;
    private String type;
    private String videoQuality;
    private float volume;
    private String etcInfo;

    private List<String> imgPaths;

    public ProductInfo(Product product, List<String> images) {
        this.productid = product.getProductid();
        this.name = product.getName();
        this.price = product.getPrice();
        this.brand = product.getBrand();
        this.energyEfficiency = product.getEnergyEfficiency();
        this.size = product.getSize();
        this.powerConsume = product.getPowerConsume();
        this.weight = product.getWeight();
        this.type = product.getType();
        this.videoQuality = product.getVideoQuality();
        this.volume = product.getVolume();
        this.etcInfo = product.getEtcInfo();
        this.imgPaths = images;
    }
}
