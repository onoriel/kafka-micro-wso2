package com.onoriel.orderservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the user. ")
public class User {
		@ApiModelProperty(notes = "The generated user ID")
        private Long id;
		@ApiModelProperty(notes = "The user first name")
        private String firstname;
		@ApiModelProperty(notes = "The user last name")
        private String lastname;
		@ApiModelProperty(notes = "The user email")
        private String email;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
}
