package com.CompareElec.CompareElec.Controller;


import com.CompareElec.CompareElec.DTO.Product.ProductCreateRequest;
import com.CompareElec.CompareElec.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@Tag(name = "상품 관련 API")
public class ProductController {

    @Autowired
    ProductService productService;


    //상품등록
    @PostMapping(value = "/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "상품 등록 API")
    public void addProduct(@RequestPart("product")@Validated ProductCreateRequest request, @RequestPart("images")List<MultipartFile> images){
        productService.addProduct(request,images);
    }

}
