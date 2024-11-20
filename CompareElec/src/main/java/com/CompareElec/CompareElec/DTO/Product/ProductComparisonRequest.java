package com.CompareElec.CompareElec.DTO.Product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductComparisonRequest {
    private List<String> productNames;
}

