package com.CompareElec.CompareElec.Controller;


import com.CompareElec.CompareElec.DTO.Review.ReviewCreateRequest;
import com.CompareElec.CompareElec.Service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/review")
@Tag(name = "리뷰 관련 API")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @PostMapping(value ="/add")
    @Operation(summary = "리뷰 등록 API",description = "accessToken 필요",security = {@SecurityRequirement(name="accessToken")})
    public void addReview(Authentication authentication, @RequestBody ReviewCreateRequest request){
        String userid = ((User) authentication.getPrincipal()).getUsername();
        reviewService.addReview(userid,request);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "리뷰 삭제 API",description = "accessToken 필요",security = {@SecurityRequirement(name="accessToken")})
    public void deleteReview(Authentication authentication, @RequestParam Long reviewid){
        String userid = ((User) authentication.getPrincipal()).getUsername();
        reviewService.deleteReview(userid,reviewid);
    }

}
