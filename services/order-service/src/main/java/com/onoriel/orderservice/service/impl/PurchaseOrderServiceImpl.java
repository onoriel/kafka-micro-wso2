package com.onoriel.orderservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onoriel.orderservice.entity.PurchaseOrder;
import com.onoriel.orderservice.repository.PurchaseOrderRepository;
import com.onoriel.orderservice.service.PurchaseOrderService;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public List<PurchaseOrder> getPurchaseOrders() {
        return this.purchaseOrderRepository.findAll();
    }

    @Override
    public void createPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrderRepository.save(purchaseOrder);
    }

}
