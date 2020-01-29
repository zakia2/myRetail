package com.example.code.service;

import com.example.code.exception.ProductInfoNotFoundException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsInfoServiceTest {

    @Autowired
    @InjectMocks
    ProductInfoService productInfoService;

    @Mock
    ProductService productService;

    @Test
    public void testGetTitle() throws IOException {
     String title = productInfoService.getTitle("13860428");
     assertThat(title, is("The Big Lebowski (Blu-ray)") );
    }

    @Test(expected = ProductInfoNotFoundException.class)
    public void testProductInfoNotFound() throws IOException {
        String title = productInfoService.getTitle("123");
    }
}
