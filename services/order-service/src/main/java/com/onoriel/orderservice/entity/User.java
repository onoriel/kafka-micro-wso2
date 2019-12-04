package com.onoriel.orderservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "All details about the user. ")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class User {
		@ApiModelProperty(notes = "The generated user ID")
        private Long id;
		@ApiModelProperty(notes = "The user first name")
        private String firstname;
		@ApiModelProperty(notes = "The user last name")
        private String lastname;
		@ApiModelProperty(notes = "The user email")
        private String email;

        
}
