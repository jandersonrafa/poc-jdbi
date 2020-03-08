package com.example.demo.jdbi.repository;


import com.example.demo.model.Item;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public class ItemFluentRepository {

    private static final String DELETE_BY_ORDER_ID = "DELETE FROM items WHERE orderid=";
    private static final String INSERT_QUERY = "INSERT INTO items(id, orderid, one, two, tree, four, five, six, seven, eight, nine, ten) VALUES (:id, :orderid, :one, :two, :tree, :four, :five, :six, :seven, :eight, :nine, :ten);";
    private static final String SELECT_ORDER_QUERY = "SELECT * FROM items WHERE orderid=";

    @Autowired
    private Jdbi jdbi;

    public void saveAll(List<Item> itens) {
        jdbi.useHandle(handle -> {
            PreparedBatch preparedBatch =
                    handle.prepareBatch(INSERT_QUERY);
            itens.forEach(order -> preparedBatch
                    .bind("id", order.getId())
                    .bind("orderid", order.getOrderid())
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
    }

    public List<Item> findByOrderId(String id) {
        return jdbi.withHandle(handle ->
                handle.createQuery(SELECT_ORDER_QUERY + "'" + id + "'")
                        .mapTo(Item.class)).list();
    }

    public Stream<Item> findStreamByOrderId(String id) {
        return jdbi.withHandle(handle ->
                handle.createQuery(SELECT_ORDER_QUERY + "'" + id + "'")
                        .mapTo(Item.class)).stream();
    }

    public void deleteByOrderId(String id) {
        jdbi.withHandle(handle ->
                handle.createUpdate(DELETE_BY_ORDER_ID + "'" + id + "'"))
                .execute();
    }
}