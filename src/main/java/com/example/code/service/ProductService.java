package com.example.code.service;

import com.example.code.exception.ProductNotFoundException;
import com.example.code.model.Product;
import com.example.code.repo.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductsRepository repo;

    // Method to return product from db given id of the product
    public Product getProduct(String id) {
        Optional<Product> product = repo.findById(id);
        if (product.isPresent())
            return product.get();
        else
            throw new ProductNotFoundException();
    }

    // Method to update product with new values
    public Product updateProduct(Product product) {
      return repo.save(product);
    }
}
