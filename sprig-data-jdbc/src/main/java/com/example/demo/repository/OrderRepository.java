package com.example.demo.repository;

import com.example.demo.model.Orders;
import lombok.Data;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OrderRepository extends CrudRepository<Orders, String> {

    @Query(value = "SELECT id FROM orders")
    Set<OrderPK> findAllIds();

    @Data
    public static class  OrderPK {
        String id;
    }
}
