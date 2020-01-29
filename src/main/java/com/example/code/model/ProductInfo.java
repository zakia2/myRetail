package com.example.code.model;
/**
 * Product Object which represents price info from db and title info from an internal api
 */
public class ProductInfo {

    public String product_id;
    public String title;
    public double price;

    public ProductInfo() {}

    public ProductInfo(String product_id, String title, double price) {
        this.product_id = product_id;
        this.title = title;
        this.price = price;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
