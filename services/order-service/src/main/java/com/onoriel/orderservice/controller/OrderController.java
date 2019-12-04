package com.onoriel.orderservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onoriel.orderservice.entity.PurchaseOrder;
import com.onoriel.orderservice.service.PurchaseOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/order-service")
@Api(value="Order Management System",produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping("/all")
    @ApiOperation(value = "Retrieve a list of all the orders", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public List<PurchaseOrder> getAllOrders(){
        return this.purchaseOrderService.getPurchaseOrders();
    }
    
    @ApiOperation(value = "Add an order")
    @PostMapping("/create")
    public void createOrder(
    		@ApiParam(value = "Order object to store", required = true) 
    		@Valid @RequestBody PurchaseOrder purchaseOrder){
        this.purchaseOrderService.createPurchaseOrder(purchaseOrder);
    }

}
