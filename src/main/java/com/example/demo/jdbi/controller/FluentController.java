/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jdbi.controller;

import com.example.demo.jdbi.model.Order;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.jdbi.repository.OrderFluentRepository;
import java.math.BigDecimal;
import java.util.Arrays;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author janderson
 */
@RestController
@RequestMapping("/fluent")
public class FluentController {

    @Autowired
    private OrderFluentRepository orderFluentRepository;

    @Autowired
    private Jdbi jdbi;

    @GetMapping("/insert")
    public String insert() {
        System.out.println("# INSERT #");
        final Order save = orderFluentRepository.save(this.createRandomOrder());
        return save.getId();
    }

    @GetMapping("/get/{id}")
    public Order getById(@PathVariable String id) {
        System.out.println("# GET BY ID #");
        return orderFluentRepository.findById(id).get();
    }

    @GetMapping("/all")
    public List<Order> findAll() {
        System.out.println("# ALL #");
        return orderFluentRepository.findAll();
    }

    @GetMapping("/insert-batch")
    public List<Order> insertBatch() {
        System.out.println("# INSERT BATCH #");
        List<Order> orders = Arrays.asList(this.createRandomOrder(), this.createRandomOrder(), this.createRandomOrder());
        orderFluentRepository.saveAll(orders);
        return orders;
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        System.out.println("# DELETE BY ID #");
        orderFluentRepository.deleteById(id);
    }

    private Order createRandomOrder() {
        double amount = ThreadLocalRandom.current().nextDouble(1000.00);
        return new Order(UUID.randomUUID().toString(), BigDecimal.valueOf(amount));
    }

}
