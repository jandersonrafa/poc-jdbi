/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.mybatis.repository;

import com.example.demo.model.Order;
import org.apache.ibatis.annotations.*;

/**
 * @author janderson
 */
//@LogSqlFactory
@Mapper

public interface OrderRepository {

//    @Update("INSERT INTO orders(amount, one, two, tree, four, five, six, seven, eight, nine, ten, active, numberorder, createddate, createdtime) " +
//            "   VALUES (:amount, :one, :two, :tree, :four, :five, :six, :seven, :eight, :nine, :ten, :active, :numberorder, :createddate, :createdtime)")
////    @GetGeneratedKeys
//    @SelectKey(statement="call identity()", keyProperty="id", before=false, resultType=Long.class)
//    Long insertBean(@Param("order") Order user);

    @Select("INSERT INTO orders(amount, one, two, tree, four, five, six, seven, eight, nine, ten, active, numberorder, createddate, createdtime) " +
            "VALUES ( #{orders.amount}, #{orders.one}, #{orders.two}, #{orders.tree}, #{orders.four}," +
            " #{orders.five}, #{orders.six}, #{orders.seven}, #{orders.eight}, #{orders.nine}, #{orders.ten}, #{orders.active}, #{orders.numberorder}, #{orders.createddate}, #{orders.createdtime}) returning id")
    Long insertBean(@Param("orders") Order orders);


    @Update("UPDATE orders SET amount=#{orders.amount}, active=#{orders.active}, createddate=#{orders.createddate}, createdtime= #{orders.createdtime}, numberorder=#{orders.numberorder}, one=#{orders.one}, two=#{orders.two}, tree=#{orders.tree}, four=#{orders.four}, five=#{orders.five}, six=#{orders.six}, seven=#{orders.seven}, eight=#{orders.eight}, nine=#{orders.nine}, ten=#{orders.ten} WHERE id=#{orders.id}")
    void updateBean( @Param("orders") Order orders);

    @Select("SELECT * FROM orders WHERE id = #{id}")
//    @RegisterBeanMapper(Order.class)
    Order findById(@Param("id") Long id);

    @Delete("DELETE FROM orders WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

}
