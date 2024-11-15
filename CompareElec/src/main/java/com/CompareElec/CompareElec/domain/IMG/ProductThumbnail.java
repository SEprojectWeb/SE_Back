package com.CompareElec.CompareElec.domain.IMG;


import com.CompareElec.CompareElec.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "product_thumbnails")
public class ProductThumbnail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long thumbnailid;

    @ManyToOne
    @JoinColumn(name="productid")
    private Product product;

    private String img_path;

    public ProductThumbnail(Product product, String img_path) {
        this.product = product;
        this.img_path = img_path;
    }
}
