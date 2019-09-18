package com.example.demo.jdbc.repository;

import com.example.demo.jdbc.model.Order;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderRepository {

    private static final String INSERT_ORDER_QUERY = "INSERT INTO orders(id, amount) VALUES (?, ?);";
    private static final String SELECT_ORDERS_QUERY = "SELECT id, amount FROM orders";
    private static final String SELECT_ORDER_QUERY = "SELECT id, amount FROM orders WHERE id=";
    private static final String DELETE_BY_ID = "DELETE FROM orders WHERE id=:id";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Order> findAll() {
        return jdbcTemplate.query(SELECT_ORDERS_QUERY, new BeanPropertyRowMapper(Order.class));
    }

    public Order findById(String id) {
        final RowMapper<Order> mapper = mapper();
        List<Order> orders = jdbcTemplate.query(SELECT_ORDER_QUERY + "'" + id + "'", mapper);
        return orders.get(0);
    }

    public Order save(Order order) {
        jdbcTemplate.update(INSERT_ORDER_QUERY,
                new Object[]{order.getId(), order.getAmount()}
        );

        return order;
    }

//    public List<Order> saveAll(List<Order> orders) {
//        jdbi.useHandle(handle -> {
//            PreparedBatch preparedBatch =
//              handle.prepareBatch(INSERT_ORDER_QUERY);
//            orders.forEach(order -> preparedBatch
//                .bind("id", order.getId())
//                .bind("amount", order.getAmount()).add());
//            preparedBatch.execute();
//        });
//        return orders;
//    }
//    
//    public void deleteById(String id) {
//        jdbi.withHandle(handle ->
//                handle.createUpdate(DELETE_BY_ID)
//                        .bind("id", id))
//                        .execute();
//    }
    private RowMapper<Order> mapper() {
        return (ResultSet rs, int i) -> {
            Order order = new Order();
            order.setId(rs.getString(1));
            order.setAmount(rs.getBigDecimal(2));
            return order;
        };
    }
}
