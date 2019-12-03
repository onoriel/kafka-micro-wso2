package com.onoriel.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.onoriel.orderservice.entity.PurchaseOrder;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends MongoRepository<PurchaseOrder, String> {

    @Query("{ 'user.id': ?0 }")
    List<PurchaseOrder> findByUserId(long userId);

}
