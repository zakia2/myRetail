package com.example.code.controller;

import com.example.code.model.ProductInfo;
import com.example.code.model.Product;
import com.example.code.service.ProductService;
import com.example.code.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductInfoService productInfoService;

    // Controller method to return product
    @GetMapping(path ="/products/{id}", produces = "application/json")
    public Product getProduct(@PathVariable String id) {
       return productService.getProduct(id);
    }

    // Controller method to return product info
    @GetMapping(path ="/products/info/{id}", produces = "application/json")
    public ProductInfo getProductInfo(@PathVariable String id) throws IOException {
        return productInfoService.getProductInfo(id);
    }

    // Controller method to update product
    @PutMapping(path ="/products/{id}", consumes = "application/json", produces = "application/json")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        System.out.println("Inside PUT Method");
            System.out.println("PathVariable: " + id);
            System.out.println("Request Body : " + product.toString());
            return productService.updateProduct(product);
    }

    // Health end point
    @GetMapping(path ="/products/health", produces = "application/json")
    public void checkHealth() {
        System.out.println("Inside HEALTh Method");
        System.out.println("Hi");
    }
}
