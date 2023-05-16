/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.mybatis.repository;

import com.example.demo.model.Item;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 * @author janderson
 */
//@LogSqlFactory
public interface ItemRepository {

    @SqlBatch("INSERT INTO items( orderid, one, two, tree, four, five, six, seven, eight, nine, ten) " +
            "   VALUES (:orderid, :one, :two, :tree, :four, :five, :six, :seven, :eight, :nine, :ten)")
    void insertList(@BindBean List<Item> orders);

    @SqlUpdate("DELETE FROM items WHERE orderid = :orderid")
    int deleteByOrderId(@Bind("orderid") Long orderid);

}
