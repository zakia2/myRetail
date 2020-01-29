package com.example.code.controller;

import com.example.code.exception.ProductInfoNotFoundException;
import com.example.code.exception.ProductNotFoundException;
import com.example.code.model.Product;
import com.example.code.model.ProductInfo;
import com.example.code.repo.ProductsRepository;
import com.example.code.service.ProductInfoService;
import com.example.code.service.ProductService;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {

        @Autowired
        MockMvc mvc;

        @MockBean
    ProductService productService;

        @MockBean
    ProductInfoService productInfoService;

        @MockBean
    ProductsRepository  repo;

    @Test
    public void testHealth() throws Exception {
        String uri = "/products/health";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
               .accept(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(mvcResult.getResponse().getStatus(),is(200));
    }

    @Test
    public void testGetProduct() throws Exception {
        String uri = "/products/123";
        Product product = new Product("123",100,"USD");
        when(productService.getProduct(anyString())).thenReturn(product);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(mvcResult.getResponse().getStatus(),is(200));
       assertThat(mvcResult.getResponse().getContentAsString(),is("{\"product_id\":\"123\",\"price\":100.0,\"currency\":\"USD\"}"));
    }

    @Test
    public void testGetProductException() throws Exception {
        String uri = "/products/123";
        when(productService.getProduct(anyString())).thenThrow(ProductNotFoundException.class);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(mvcResult.getResponse().getStatus(),is(404));
    }

    @Test
    public void testGetProductInfo() throws Exception {
        String uri = "/products/info/123";
        ProductInfo productInfo = new ProductInfo("123","title1", 200);
        when(productInfoService.getProductInfo(anyString())).thenReturn(productInfo);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(mvcResult.getResponse().getStatus(),is(200));
        assertThat(mvcResult.getResponse().getContentAsString(),is("{\"product_id\":\"123\",\"title\":\"title1\",\"price\":200.0}"));
    }

    @Test
    public void testGetProductInfoException() throws Exception {
        String uri = "/products/info/123";
        when(productInfoService.getProductInfo(anyString())).thenThrow(ProductInfoNotFoundException.class);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(mvcResult.getResponse().getStatus(),is(404));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        String uri = "/products/13860428";
        String requestBody = "{\"product_id\":\"13860428\",\"price\":250.0,\"currency\":\"USD\"}";
        Product product = new Product("13860428",250,"USD");
        when(productService.updateProduct(any(Product.class))).thenReturn(product);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                                .put(uri)
                                .content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                                 .andReturn();
        assertThat(mvcResult.getResponse().getStatus(), is(200));

    }
}
