/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jdbi.repository;

import com.example.demo.model.Item;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Set;

/**
 *
 * @author janderson
 */
//@LogSqlFactory
public interface ItemDeclarativeRepository {

    @SqlBatch("INSERT INTO items(id, orderid, one, two, tree, four, five, six, seven, eight, nine, ten) " +
            "   VALUES (:id, :orderid, :one, :two, :tree, :four, :five, :six, :seven, :eight, :nine, :ten)")
//    @BatchChunkSize(1000)
    void insertList(@BindBean List<Item> orders);

    @SqlQuery("SELECT * FROM items ORDER BY amount")
    @RegisterBeanMapper(Item.class)
    List<Item> findAll();

    @SqlUpdate("DELETE FROM items WHERE id = :id")
    void deleteById(@Bind("id") String id);

    @SqlUpdate("DELETE FROM items WHERE orderid = :orderid")
    int deleteByOrderId(@Bind("orderid") String orderid);

    @SqlQuery("SELECT * FROM items WHERE orderid = :orderid")
    @RegisterBeanMapper(Item.class)
    Set<Item> findByOrderId(@Bind("orderid") String orderid);

    @SqlUpdate("INSERT INTO items(id, orderid, one, two, tree, four, five, six, seven, eight, nine, ten) " +
            "   VALUES (:id, :orderid, :one, :two, :tree, :four, :five, :six, :seven, :eight, :nine, :ten)")
    int insertBean(@BindBean Item user);

}
