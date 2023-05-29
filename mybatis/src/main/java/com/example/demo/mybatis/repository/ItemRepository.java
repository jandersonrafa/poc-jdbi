/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.mybatis.repository;

import com.example.demo.model.Item;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author janderson
 */
//@LogSqlFactory
@Mapper

public interface ItemRepository {

    @Insert({
            "<script>",
            "INSERT INTO items",
            "(orderid, one, two, tree, four, five, six, seven, eight, nine, ten)",
            "VALUES" +
                    "<foreach item='each_item_name' collection='orders' open='' separator=',' close=''>" +
                    "(" +
                    "#{each_item_name.orderid,jdbcType=INTEGER},",
                     "#{each_item_name.one,jdbcType=VARCHAR},",
                     "#{each_item_name.two,jdbcType=VARCHAR},",
                     "#{each_item_name.tree,jdbcType=VARCHAR},",
                     "#{each_item_name.four,jdbcType=VARCHAR},",
                     "#{each_item_name.five,jdbcType=VARCHAR},",
                     "#{each_item_name.six,jdbcType=VARCHAR},",
                     "#{each_item_name.seven,jdbcType=VARCHAR},",
                     "#{each_item_name.eight,jdbcType=VARCHAR},",
                     "#{each_item_name.nine,jdbcType=VARCHAR},",
                     "#{each_item_name.ten,jdbcType=VARCHAR}" +
                    ")" +
                    "</foreach>",
            "</script>"})
    void insertList(@Param("orders") List<Item> orders);


    @Delete("DELETE FROM items WHERE orderid = #{orderid}")
    int deleteByOrderId(@Param("orderid") Long orderid);

}
