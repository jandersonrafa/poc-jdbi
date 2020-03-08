package com.example.demo.jdbi.repository;


import com.example.demo.model.Order;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderFluentRepository {

    private static final String INSERT_ORDER_QUERY = "INSERT INTO orders(id, amount, one, two, tree, four, five, six, seven, eight, nine, ten) VALUES (:id, :amount, :one, :two, :tree, :four, :five, :six, :seven, :eight, :nine, :ten);";
    private static final String SELECT_ORDERS_QUERY = "SELECT * FROM orders";
    private static final String SELECT_ORDER_QUERY = "SELECT * FROM orders WHERE id=";
    private static final String DELETE_BY_ID = "DELETE FROM orders WHERE id=";

    @Autowired
    private Jdbi jdbi;

    public List<Order> findAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery(SELECT_ORDERS_QUERY)
                        .mapTo(Order.class)).list();
    }

    public Order findById(String id) {
        return jdbi.withHandle(handle ->
                handle.createQuery(SELECT_ORDER_QUERY + "'" + id + "'")
                        .mapTo(Order.class)).one();
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
                    .bind("amount", order.getAmount())
                    .bind("one", order.getOne())
                    .bind("two", order.getTwo())
                    .bind("tree", order.getTree())
                    .bind("four", order.getFour())
                    .bind("five", order.getFive())
                    .bind("six", order.getSix())
                    .bind("seven", order.getSeven())
                    .bind("eight", order.getEight())
                    .bind("nine", order.getNine())
                    .bind("ten", order.getTen())

                    .add());
            preparedBatch.execute();
        });
        return orders;
    }

    public void deleteById(String id) {
        jdbi.withHandle(handle ->
                handle.createUpdate(DELETE_BY_ID + "'" + id + "'"))
                .execute();
    }
}