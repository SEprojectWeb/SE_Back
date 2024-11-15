package com.CompareElec.CompareElec.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewid;

    private Float rating;
    private String contents;

    @ManyToOne
    @JoinColumn(name="productid")
    private Product product;

    @ManyToOne
    @JoinColumn(name="userid")
    private User user;


    public Review(Float rating, String contents, User user,Product product) {
        this.rating = rating;
        this.contents = contents;
        this.user = user;
        this.product = product;
    }
}
