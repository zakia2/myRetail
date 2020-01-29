package com.example.code.service;


import com.example.code.exception.ProductInfoNotFoundException;
import com.example.code.model.ProductInfo;
import com.example.code.model.Product;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductInfoService {
    @Autowired
    ProductService productService;
    String api = "https://redsky.target.com/v2/pdp/tcin/";

    // Method to return productInfo given an id
    public ProductInfo getProductInfo(String id) throws IOException {
     ProductInfo productInfo = new ProductInfo();
     Product product = productService.getProduct(id);
     productInfo.setProduct_id(id);
     productInfo.setPrice(product.getPrice());
     productInfo.setTitle(getTitle(id));
     return productInfo;
    }

    // Method to call a resource for the title of product with given id
    public String getTitle(String id) throws IOException {
        HttpGet request = new HttpGet(api+id);
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            CloseableHttpResponse response = client.execute(request);
            String jsonString = EntityUtils.toString(response.getEntity());
            EntityUtils.consume(response.getEntity());
                JSONObject json = new JSONObject(jsonString);
                String title = json.getJSONObject("product").getJSONObject("item").getJSONObject("product_description").get("title").toString();
                return title;
        }
        catch(Exception e) {
            throw new ProductInfoNotFoundException();
        }
    }
}

