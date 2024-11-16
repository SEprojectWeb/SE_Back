package com.CompareElec.CompareElec.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductid(Long productid);
    List<Product> findAllByProductType(String producttype);
}
