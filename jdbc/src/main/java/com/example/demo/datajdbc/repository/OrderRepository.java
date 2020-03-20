package com.example.demo.datajdbc.repository;

import com.example.demo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    private static final String INSERT_ORDER_FULL_QUERY = "INSERT INTO orders(id, amount, one, two, tree, four, five, six, seven, eight, nine, ten) VALUES (:id, :amount, :one, :two, :tree, :four, :five,:six, :seven, :eight, :nine, :ten);";
    private static final String SELECT_ORDERS_QUERY = "SELECT * FROM orders";
    private static final String SELECT_ORDER_QUERY = "SELECT * FROM orders WHERE id=:id";
    private static final String DELETE_BY_ID = "DELETE FROM orders WHERE id=:id";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<String> findAllIds() {
        return namedParameterJdbcTemplate.query(SELECT_ORDERS_QUERY, (rs, rowNum) -> rs.getString(1));
    }

    public Order findById(String id) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", id);
        List<Order> query = namedParameterJdbcTemplate.query(SELECT_ORDER_QUERY, parametros, new BeanPropertyRowMapper(Order.class));
        return query.size() > 0 ? query.get(0) : null;
    }

    public Order save(Order order) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", order.getId());
        parametros.put("amount", order.getAmount());
        parametros.put("one", order.getOne());
        parametros.put("two", order.getTwo());
        parametros.put("tree", order.getTree());
        parametros.put("four", order.getFour());
        parametros.put("five", order.getFive());
        parametros.put("six", order.getSix());
        parametros.put("seven", order.getSeven());
        parametros.put("eight", order.getEight());
        parametros.put("nine", order.getNine());
        parametros.put("ten", order.getTen());

        namedParameterJdbcTemplate.update(INSERT_ORDER_FULL_QUERY, parametros);

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
        List<MapSqlParameterSource> parametros = new ArrayList<>();

        orders.forEach(order -> {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("id", order.getId())
                    .addValue("amount", order.getAmount())
                    .addValue("one", order.getOne())
                    .addValue("two", order.getTwo())
                    .addValue("tree", order.getTree())
                    .addValue("four", order.getFour())
                    .addValue("five", order.getFive())
                    .addValue("six", order.getSix())
                    .addValue("seven", order.getSeven())
                    .addValue("eight", order.getEight())
                    .addValue("nine", order.getNine())
                    .addValue("ten", order.getTen());

            parametros.add(parameterSource);
        });

        namedParameterJdbcTemplate.batchUpdate(INSERT_ORDER_FULL_QUERY, parametros.toArray(new MapSqlParameterSource[parametros.size()]));

    }

    public int deleteById(String id) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", id);
        return namedParameterJdbcTemplate.update(DELETE_BY_ID, parametros);
    }
}
