package com.onoriel.orderservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "All details about the product. ")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Product {
	@ApiModelProperty(notes = "The generated product ID")
    private long id;
	@ApiModelProperty(notes = "The product description")
    private String description;

    
}
