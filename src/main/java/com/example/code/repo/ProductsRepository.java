package com.example.code.repo;

import com.example.code.model.Product;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductsRepository extends CrudRepository<Product, String> {
}
