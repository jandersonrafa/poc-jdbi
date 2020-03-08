package com.example.demo.datajdbc.repository;

import com.example.demo.model.Item;
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
import java.util.Set;
import java.util.stream.Collectors;

@Repository
//@Transactional
public class ItemRepository {

    private static final String INSERT_FULL = "INSERT INTO items(id,  orderid, one, two, tree, four, five, six, seven, eight, nine, ten) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_QUERY = "SELECT id FROM items";
    private static final String SELECT_QUERY_BY_ID = "SELECT * FROM items WHERE id=";
    private static final String SELECT_QUERY_BY_ORDER_ID = "SELECT * FROM items WHERE orderid=";

    private static final String DELETE_BY_ORDER_ID = "DELETE FROM Items WHERE orderid=";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Item> findAll() {
        return jdbcTemplate.query(SELECT_QUERY, new BeanPropertyRowMapper(Item.class));
    }

    public Item findById(String id) {
        final RowMapper<Item> mapper = mapper();
        List<Item> Items = jdbcTemplate.query(SELECT_QUERY_BY_ID + "'" + id + "'", mapper);
        return Items.get(0);
    }

    public Set<Item> findByOrderId(String id) {
        final RowMapper<Item> mapper = mapper();
        List<Item> Items = jdbcTemplate.query(SELECT_QUERY_BY_ORDER_ID + "'" + id + "'", mapper);
        return Items.stream().collect(Collectors.toSet());
    }
    private RowMapper<Item> mapper() {
        return (ResultSet rs, int i) -> {
            Item Item = new Item();
            Item.setId(rs.getString(1));
            Item.setOrderid(rs.getString(1));
            Item.setOne(rs.getString(1));
            Item.setTwo(rs.getString(1));
            Item.setTree(rs.getString(1));
            Item.setFour(rs.getString(1));
            Item.setFive(rs.getString(1));
            Item.setSix(rs.getString(1));
            Item.setSeven(rs.getString(1));
            Item.setEight(rs.getString(1));
            Item.setNine(rs.getString(1));
            Item.setTen(rs.getString(1));
            return Item;
        };
    }

    public void saveAll(List<Item> Items) {
        jdbcTemplate.batchUpdate(INSERT_FULL,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, Items.get(i).getId());
                        ps.setString(2, Items.get(i).getOrderid());
                        ps.setString(3, Items.get(i).getOne());
                        ps.setString(4, Items.get(i).getTwo());
                        ps.setString(5, Items.get(i).getTree());
                        ps.setString(6, Items.get(i).getFour());
                        ps.setString(7, Items.get(i).getFive());
                        ps.setString(8, Items.get(i).getSix());
                        ps.setString(9, Items.get(i).getSeven());
                        ps.setString(10, Items.get(i).getEight());
                        ps.setString(11, Items.get(i).getNine());
                        ps.setString(12, Items.get(i).getTen());
                    }

                    @Override
                    public int getBatchSize() {
                        return Items.size();
                    }
                }
        );

    }

    public void deleteByOrderId(String id) {
            jdbcTemplate.update(DELETE_BY_ORDER_ID + "'" + id + "'" );
    }
}
