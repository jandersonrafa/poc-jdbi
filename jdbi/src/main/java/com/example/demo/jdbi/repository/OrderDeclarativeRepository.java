/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jdbi.repository;

import com.example.demo.model.Order;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author janderson
 */
//@LogSqlFactory
public interface OrderDeclarativeRepository {

    @SqlUpdate("INSERT INTO orders(id, amount) VALUES (:id, :amount)")
    int insertNamed(@Bind("id") String id, @Bind("amount") BigDecimal amount);

    @SqlUpdate("INSERT INTO orders(id, amount, one, two, tree, four, five, six, seven, eight, nine, ten) " +
            "   VALUES (:id, :amount, :one, :two, :tree, :four, :five, :six, :seven, :eight, :nine, :ten)")
    int insertBean(@BindBean Order user);

    @SqlQuery("SELECT * FROM orders ORDER BY amount")
    @RegisterBeanMapper(Order.class)
    List<Order> findAll();
    
    @SqlQuery("SELECT * FROM orders WHERE id = :id")
    @RegisterBeanMapper(Order.class)
    Order findById(@Bind("id") String id);

    @SqlUpdate("DELETE FROM orders WHERE id = :id")
    int deleteById(@Bind("id") String id);

    @SqlQuery("SELECT id FROM orders")
    List<String> findAllIds();

}
