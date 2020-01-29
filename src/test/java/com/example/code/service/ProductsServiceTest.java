package com.example.code.service;

import com.example.code.exception.ProductNotFoundException;
import com.example.code.model.Product;
import com.example.code.repo.ProductsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsServiceTest {

    @MockBean
    ProductsRepository repo;

    @Autowired
    ProductService productService;

    @Test
    public void testGetProduct() {
        Product expected = new Product("123", 200, "USD");
        when(repo.findById(anyString())).thenReturn(Optional.of(expected));
        Product actual = productService.getProduct("123");
        assertThat(actual.getProduct_id(), is(expected.getProduct_id()));
        assertThat(actual.getCurrency(), is(expected.getCurrency()));
        assertThat(actual.getPrice(), is(expected.getPrice()));
    }

    @Test(expected = ProductNotFoundException.class)
    public void testProductNotFoundException() {
        when(repo.findById(anyString())).thenReturn(Optional.empty());
        Product actual = productService.getProduct("123");
    }
}
