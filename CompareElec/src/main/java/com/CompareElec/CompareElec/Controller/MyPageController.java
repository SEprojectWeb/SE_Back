package com.CompareElec.CompareElec.Controller;

import com.CompareElec.CompareElec.DTO.Response.UserInfo;
import com.CompareElec.CompareElec.DTO.User.UserInfoRequest;
import com.CompareElec.CompareElec.domain.Review;
import com.CompareElec.CompareElec.Service.UserService;
import com.CompareElec.CompareElec.Service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mypage")
@Tag(name = "마이페이지 관련 API")
public class MyPageController {

    UserService userService;
    ReviewService reviewService;

    public MyPageController(UserService userService, ReviewService reviewService) {
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @GetMapping("/user-info")
    @Operation(summary = "사용자 정보 조회", security = {@SecurityRequirement(name = "accessToken")})
    public ResponseEntity<UserInfo> getUserInfo(Authentication authentication) {
        String userid = ((com.CompareElec.CompareElec.domain.User) authentication.getPrincipal()).getUsername();
        UserInfo userInfo = userService.getUserInfo(userid);
        return ResponseEntity.ok(userInfo);
    }

    @PutMapping("/user-info")
    @Operation(summary = "사용자 정보 수정", security = {@SecurityRequirement(name = "accessToken")})
    public ResponseEntity<UserInfo> updateUserInfo(Authentication authentication,
                                                   @RequestBody UserInfoRequest userInfoRequest) {
        String userid = ((com.CompareElec.CompareElec.domain.User) authentication.getPrincipal()).getUsername();
        UserInfo updatedUserInfo = userService.updateUserInfo(userid, userInfoRequest);
        return ResponseEntity.ok(updatedUserInfo);
    }

    @GetMapping("/reviews")
    @Operation(summary = "사용자가 작성한 리뷰 목록 조회", security = {@SecurityRequirement(name = "accessToken")})
    public ResponseEntity<List<Review>> getUserReviews(Authentication authentication) {
        String userid = ((com.CompareElec.CompareElec.domain.User) authentication.getPrincipal()).getUsername();
        List<Review> reviews = reviewService.getUserReviews(userid);
        return ResponseEntity.ok(reviews);
    }
}
