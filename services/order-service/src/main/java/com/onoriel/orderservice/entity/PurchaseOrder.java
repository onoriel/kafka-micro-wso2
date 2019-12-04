package com.onoriel.orderservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Document
@ApiModel(description = "All details about the order. ")
public class PurchaseOrder {

    @Id
    @ApiModelProperty(notes = "The generated order ID")
    private String id;
    @ApiModelProperty(notes = "The order user")
    private User user;
    @ApiModelProperty(notes = "The order product")
    private Product product;
    @ApiModelProperty(notes = "The order price")
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
