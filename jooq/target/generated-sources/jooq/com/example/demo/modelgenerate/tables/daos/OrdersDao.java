/*
 * This file is generated by jOOQ.
 */
package com.example.demo.modelgenerate.tables.daos;


import com.example.demo.modelgenerate.AbstractSpringDAOImpl;
import com.example.demo.modelgenerate.tables.Orders;
import com.example.demo.modelgenerate.tables.records.OrdersRecord;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Repository
public class OrdersDao extends AbstractSpringDAOImpl<OrdersRecord, com.example.demo.modelgenerate.tables.pojos.Orders, Long> {

    /**
     * Create a new OrdersDao without any configuration
     */
    public OrdersDao() {
        super(Orders.ORDERS, com.example.demo.modelgenerate.tables.pojos.Orders.class);
    }

    /**
     * Create a new OrdersDao with an attached configuration
     */
    @Autowired
    public OrdersDao(Configuration configuration) {
        super(Orders.ORDERS, com.example.demo.modelgenerate.tables.pojos.Orders.class, configuration);
    }

    @Override
    public Long getId(com.example.demo.modelgenerate.tables.pojos.Orders object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Orders.ORDERS.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchById(Long... values) {
        return fetch(Orders.ORDERS.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.example.demo.modelgenerate.tables.pojos.Orders fetchOneById(Long value) {
        return fetchOne(Orders.ORDERS.ID, value);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public Optional<com.example.demo.modelgenerate.tables.pojos.Orders> fetchOptionalById(Long value) {
        return fetchOptional(Orders.ORDERS.ID, value);
    }

    /**
     * Fetch records that have <code>amount BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfAmount(BigDecimal lowerInclusive, BigDecimal upperInclusive) {
        return fetchRange(Orders.ORDERS.AMOUNT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>amount IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchByAmount(BigDecimal... values) {
        return fetch(Orders.ORDERS.AMOUNT, values);
    }

    /**
     * Fetch records that have <code>active BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfActive(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(Orders.ORDERS.ACTIVE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>active IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchByActive(Boolean... values) {
        return fetch(Orders.ORDERS.ACTIVE, values);
    }

    /**
     * Fetch records that have <code>createddate BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfCreateddate(LocalDate lowerInclusive, LocalDate upperInclusive) {
        return fetchRange(Orders.ORDERS.CREATEDDATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>createddate IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchByCreateddate(LocalDate... values) {
        return fetch(Orders.ORDERS.CREATEDDATE, values);
    }

    /**
     * Fetch records that have <code>createdtime BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfCreatedtime(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Orders.ORDERS.CREATEDTIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>createdtime IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchByCreatedtime(LocalDateTime... values) {
        return fetch(Orders.ORDERS.CREATEDTIME, values);
    }

    /**
     * Fetch records that have <code>numberorder BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfNumberorder(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Orders.ORDERS.NUMBERORDER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>numberorder IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchByNumberorder(Integer... values) {
        return fetch(Orders.ORDERS.NUMBERORDER, values);
    }

    /**
     * Fetch records that have <code>one BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfOne(String lowerInclusive, String upperInclusive) {
        return fetchRange(Orders.ORDERS.ONE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>one IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchByOne(String... values) {
        return fetch(Orders.ORDERS.ONE, values);
    }

    /**
     * Fetch records that have <code>two BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfTwo(String lowerInclusive, String upperInclusive) {
        return fetchRange(Orders.ORDERS.TWO, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>two IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchByTwo(String... values) {
        return fetch(Orders.ORDERS.TWO, values);
    }

    /**
     * Fetch records that have <code>tree BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfTree(String lowerInclusive, String upperInclusive) {
        return fetchRange(Orders.ORDERS.TREE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>tree IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchByTree(String... values) {
        return fetch(Orders.ORDERS.TREE, values);
    }

    /**
     * Fetch records that have <code>four BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfFour(String lowerInclusive, String upperInclusive) {
        return fetchRange(Orders.ORDERS.FOUR, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>four IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchByFour(String... values) {
        return fetch(Orders.ORDERS.FOUR, values);
    }

    /**
     * Fetch records that have <code>five BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfFive(String lowerInclusive, String upperInclusive) {
        return fetchRange(Orders.ORDERS.FIVE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>five IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchByFive(String... values) {
        return fetch(Orders.ORDERS.FIVE, values);
    }

    /**
     * Fetch records that have <code>six BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfSix(String lowerInclusive, String upperInclusive) {
        return fetchRange(Orders.ORDERS.SIX, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>six IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchBySix(String... values) {
        return fetch(Orders.ORDERS.SIX, values);
    }

    /**
     * Fetch records that have <code>seven BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfSeven(String lowerInclusive, String upperInclusive) {
        return fetchRange(Orders.ORDERS.SEVEN, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>seven IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchBySeven(String... values) {
        return fetch(Orders.ORDERS.SEVEN, values);
    }

    /**
     * Fetch records that have <code>eight BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfEight(String lowerInclusive, String upperInclusive) {
        return fetchRange(Orders.ORDERS.EIGHT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>eight IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchByEight(String... values) {
        return fetch(Orders.ORDERS.EIGHT, values);
    }

    /**
     * Fetch records that have <code>nine BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfNine(String lowerInclusive, String upperInclusive) {
        return fetchRange(Orders.ORDERS.NINE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>nine IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchByNine(String... values) {
        return fetch(Orders.ORDERS.NINE, values);
    }

    /**
     * Fetch records that have <code>ten BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchRangeOfTen(String lowerInclusive, String upperInclusive) {
        return fetchRange(Orders.ORDERS.TEN, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>ten IN (values)</code>
     */
    public List<com.example.demo.modelgenerate.tables.pojos.Orders> fetchByTen(String... values) {
        return fetch(Orders.ORDERS.TEN, values);
    }
}