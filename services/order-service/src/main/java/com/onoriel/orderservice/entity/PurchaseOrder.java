package com.onoriel.orderservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Document
@ApiModel(description = "All details about the order. ")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
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

    
}
