package com.example.code.service;

import com.example.code.exception.ProductNotFoundException;
import com.example.code.model.Product;
import com.example.code.repo.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductsRepository repo;

    /** Service method to get product object
     *
     * @param id of product
     * @return product object
     */
    public Product getProduct(String id) {
        Optional<Product> product = repo.findById(id);
        if (product.isPresent())
            return product.get();
        else
            throw new ProductNotFoundException();
    }

    /** Service method to update product  object
     *
     * @param product with values to be updated
     * @return updated product object
     */
    public Product updateProduct(Product product) {
      return repo.save(product);
    }
}
