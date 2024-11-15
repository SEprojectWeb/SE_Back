package com.CompareElec.CompareElec.Service;

import com.CompareElec.CompareElec.DTO.Review.ReviewCreateRequest;
import com.CompareElec.CompareElec.domain.*;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;


    @Transactional
    public void addReview(String userid, ReviewCreateRequest request){
        User user = userRepository.findByUserid(userid)
                .orElseThrow(IllegalArgumentException::new);

        Product product = productRepository.findByProductid(request.getProductid())
                .orElseThrow(IllegalArgumentException::new);

        Review review = new Review(request.getRating(),request.getContents(),user,product);

        reviewRepository.save(review);
    }


    @Transactional
    public void deleteReview(String userid,Long reviewid){
        Review review = reviewRepository.findByReviewid(reviewid)
                .orElseThrow(IllegalArgumentException::new);

        if(!review.getUser().getUserid().equals(userid)){
            throw new RuntimeException("사용자가 등록한 리뷰가 아님");
        }

        reviewRepository.delete(review);

    }

}
