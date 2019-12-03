package com.onoriel.orderservice.service;


import java.util.List;

import com.onoriel.orderservice.entity.PurchaseOrder;

public interface PurchaseOrderService {
    List<PurchaseOrder> getPurchaseOrders();
    void createPurchaseOrder(PurchaseOrder purchaseOrder);
}
