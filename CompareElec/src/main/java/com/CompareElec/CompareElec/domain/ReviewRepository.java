package com.CompareElec.CompareElec.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByReviewid(Long reviewid);

    List<Review> findAllByUser_Userid(String userid);

    List<Review> findByProduct_Productid(Long productid);
}
