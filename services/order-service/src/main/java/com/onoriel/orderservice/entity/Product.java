package com.onoriel.orderservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the product. ")
public class Product {
	@ApiModelProperty(notes = "The generated product ID")
    private long id;
	@ApiModelProperty(notes = "The product description")
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
