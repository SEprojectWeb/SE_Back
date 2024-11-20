package com.CompareElec.CompareElec.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductid(Long productid);
    List<Product> findAllByProductType(String producttype);
    List<Product> findByNameIn(List<String> names);

    // 키워드 검색 메서드
    @Query("SELECT p FROM Product p WHERE " +
            "(LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            " LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
            "(:productType IS NULL OR p.productType = :productType)")
    List<Product> findProductsByKeyword(
            @Param("keyword") String keyword,
            @Param("productType") String productType
    );
}
