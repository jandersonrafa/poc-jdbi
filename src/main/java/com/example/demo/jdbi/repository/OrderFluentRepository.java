package com.example.demo.jdbi.repository;


import com.example.demo.jdbi.model.Order;
import java.util.List;
import java.util.Optional;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderFluentRepository {

    private static final String INSERT_ORDER_QUERY = "INSERT INTO orders(id, amount) VALUES (:id, :amount);";
    private static final String SELECT_ORDERS_QUERY = "SELECT id, amount FROM orders";
    private static final String SELECT_ORDER_QUERY = "SELECT id, amount FROM orders WHERE id=:id";
    private static final String DELETE_BY_ID = "DELETE FROM orders WHERE id=:id";
    
    @Autowired
    private Jdbi jdbi;

    public List<Order> findAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery(SELECT_ORDERS_QUERY)
                        .mapTo(Order.class)).list();
    }

    public Optional<Order> findById(String id) {
        return jdbi.withHandle(handle ->
                handle.createQuery(SELECT_ORDER_QUERY)
                        .bind("id", id)
                        .mapTo(Order.class)).findFirst();
    }

    public Order save(Order order) {
        jdbi.useHandle(handle ->
                handle.createUpdate(INSERT_ORDER_QUERY)
                        .bind("id", order.getId())
                        .bind("amount", order.getAmount()).execute());
        return order;
    }

    public List<Order> saveAll(List<Order> orders) {
        jdbi.useHandle(handle -> {
            PreparedBatch preparedBatch =
              handle.prepareBatch(INSERT_ORDER_QUERY);
            orders.forEach(order -> preparedBatch
                .bind("id", order.getId())
                .bind("amount", order.getAmount()).add());
            preparedBatch.execute();
        });
        return orders;
    }
    
    public void deleteById(String id) {
        jdbi.withHandle(handle ->
                handle.createUpdate(DELETE_BY_ID)
                        .bind("id", id))
                        .execute();
    }
}