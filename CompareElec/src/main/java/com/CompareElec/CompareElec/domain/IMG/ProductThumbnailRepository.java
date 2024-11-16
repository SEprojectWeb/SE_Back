package com.CompareElec.CompareElec.domain.IMG;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductThumbnailRepository extends JpaRepository<ProductThumbnail, Long> {
    List<ProductThumbnail> findByProduct_Productid(Long productId);
}
