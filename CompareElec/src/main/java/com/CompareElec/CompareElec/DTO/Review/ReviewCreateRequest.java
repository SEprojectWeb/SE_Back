package com.CompareElec.CompareElec.DTO.Review;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ReviewCreateRequest {
    private long productid;
    private float rating;
    private String contents;
}
