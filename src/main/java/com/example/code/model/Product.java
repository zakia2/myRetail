package com.example.code.model;
/**
 * Product Object which represents product as it is in the db
 */
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "products")
public class Product {

    @DynamoDBHashKey
    private String product_id;

    @DynamoDBAttribute
    private double price;

    @DynamoDBAttribute
    private String currency;

    //Empty constructor required for mapping class to dynamodb table by amazon aws sdk
    public Product() {}

    public Product(String product_id, double price, String currency) {
        this.product_id = product_id;
        this.price = price;
        this.currency = currency;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String toString() {
        return this.product_id + ", " + this.price + ", " + this.currency;
    }
}
