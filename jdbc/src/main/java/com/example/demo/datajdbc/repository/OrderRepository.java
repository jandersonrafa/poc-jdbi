package com.example.demo.datajdbc.repository;

import com.example.demo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
//@Transactional
public class OrderRepository {

    private static final String INSERT_ORDER_FULL_QUERY = "INSERT INTO orders(id, amount, one, two, tree, four, five, six, seven, eight, nine, ten) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ORDERS_QUERY = "SELECT * FROM orders";
    private static final String SELECT_ORDER_QUERY = "SELECT * FROM orders WHERE id=";
    private static final String DELETE_BY_ID = "DELETE FROM orders WHERE id=";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Order> findAll() {
        return jdbcTemplate.query(SELECT_ORDERS_QUERY, new BeanPropertyRowMapper(Order.class));
    }

    public List<String> findAllIds() {
        return jdbcTemplate.query(SELECT_ORDERS_QUERY, (rs, rowNum) -> rs.getString(1));
    }

    public Order findById(String id) {
        final RowMapper<Order> mapper = mapper();
        List<Order> orders = jdbcTemplate.query(SELECT_ORDER_QUERY + "'" + id + "'", mapper);
        return orders.get(0);
    }

    public Order save(Order order) {
        jdbcTemplate.update(INSERT_ORDER_FULL_QUERY,
                new Object[]{
                        order.getId()
                        , order.getAmount()
                        , order.getOne()
                        , order.getTwo()
                        , order.getTree()
                        , order.getFour()
                        , order.getFive()
                        , order.getSix()
                        , order.getSeven()
                        , order.getEight()
                        , order.getNine()
                        , order.getTen()
                }
        );

        return order;
    }

    private RowMapper<Order> mapper() {
        return (ResultSet rs, int i) -> {
            Order order = new Order();
            order.setId(rs.getString(1));
            order.setAmount(rs.getBigDecimal(2));
            order.setOne(rs.getString(3));
            order.setTree(rs.getString(4));
            order.setFour(rs.getString(5));
            order.setFive(rs.getString(6));
            order.setSix(rs.getString(7));
            order.setSeven(rs.getString(8));
            order.setEight(rs.getString(9));
            order.setNine(rs.getString(10));
            order.setTen(rs.getString(11));
            return order;
        };
    }

    public void saveAll(List<Order> orders) {
        jdbcTemplate.batchUpdate(INSERT_ORDER_FULL_QUERY,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, orders.get(i).getId());
                        ps.setBigDecimal(2, orders.get(i).getAmount());
                        ps.setString(3, orders.get(i).getOne());
                        ps.setString(4, orders.get(i).getTwo());
                        ps.setString(5, orders.get(i).getTree());
                        ps.setString(6, orders.get(i).getFour());
                        ps.setString(7, orders.get(i).getFive());
                        ps.setString(8, orders.get(i).getSix());
                        ps.setString(9, orders.get(i).getSeven());
                        ps.setString(10, orders.get(i).getEight());
                        ps.setString(11, orders.get(i).getNine());
                        ps.setString(12, orders.get(i).getTen());
                    }

                    @Override
                    public int getBatchSize() {
                        return orders.size();
                    }
                }
        );

    }

    public void deleteById(String id) {
        jdbcTemplate.update(DELETE_BY_ID + "'" + id + "'");
    }
}
