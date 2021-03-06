package com.example.demo.datajdbc.repository;

import com.example.demo.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {

    private static final String INSERT_FULL = "INSERT INTO items(id,  orderid, one, two, tree, four, five, six, seven, eight, nine, ten) VALUES (:id,  :orderid, :one, :two, :tree, :four, :five, :six, :seven, :eight, :nine, :ten);";
    private static final String SELECT_QUERY = "SELECT id FROM items";
    private static final String SELECT_QUERY_BY_ORDER_ID = "SELECT * FROM items WHERE orderid=:orderid";
    private static final String DELETE_BY_ORDER_ID = "DELETE FROM Items WHERE orderid=:orderid";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Set<Item> findByOrderId(String orderid) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("orderid", orderid);
        List<Item> query = namedParameterJdbcTemplate.query(SELECT_QUERY_BY_ORDER_ID, parametros, new BeanPropertyRowMapper(Item.class));
        return query.stream().collect(Collectors.toSet());
    }

    public void saveAll(List<Item> Items) {
        List<MapSqlParameterSource> parametros = new ArrayList<>();
        Items.forEach(item -> {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("id", item.getId())
                    .addValue("orderid", item.getOrderid())
                    .addValue("one", item.getOne())
                    .addValue("two", item.getTwo())
                    .addValue("tree", item.getTree())
                    .addValue("four", item.getFour())
                    .addValue("five", item.getFive())
                    .addValue("six", item.getSix())
                    .addValue("seven", item.getSeven())
                    .addValue("eight", item.getEight())
                    .addValue("nine", item.getNine())
                    .addValue("ten", item.getTen());

            parametros.add(parameterSource);
        });

        namedParameterJdbcTemplate.batchUpdate(INSERT_FULL, parametros.toArray(new MapSqlParameterSource[parametros.size()]));
    }

    public int deleteByOrderId(String orderid) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("orderid", orderid);
        return namedParameterJdbcTemplate.update(DELETE_BY_ORDER_ID, parametros);
    }

    public void saveAllOneByOne(List<Item> Items) {
        Items.forEach(item -> {
            save(item);
        });
    }

    public void save(Item item) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("id", item.getId());
        parametros.put("orderid", item.getOrderid());
        parametros.put("one", item.getOne());
        parametros.put("two", item.getTwo());
        parametros.put("tree", item.getTree());
        parametros.put("four", item.getFour());
        parametros.put("five", item.getFive());
        parametros.put("six", item.getSix());
        parametros.put("seven", item.getSeven());
        parametros.put("eight", item.getEight());
        parametros.put("nine", item.getNine());
        parametros.put("ten", item.getTen());

        namedParameterJdbcTemplate.update(INSERT_FULL,parametros);
    }

}
