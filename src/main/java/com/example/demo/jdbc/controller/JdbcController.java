/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jdbc.controller;

import com.example.demo.jdbc.repository.OrderRepository;
import com.example.demo.jdbc.model.Order;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author janderson
 */
@RestController
@RequestMapping("/jdbc")
public class JdbcController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/insert")
    public String insert() {
       return orderRepository.save(this.createRandomOrder()).getId();
    }

    @GetMapping("/get/{id}")
    public Order getById(@PathVariable String id) {
        System.out.println("# GET BY ID #");
        return orderRepository.findById(id);
    }

    @GetMapping("/all")
    public List<Order> findAll() {
        System.out.println("# ALL #");
        return orderRepository.findAll();
//        return jdbi.withExtension(OrderDeclarativeRepository.class, repository -> repository.findAll());
    }

//    @GetMapping("/insert-batch")
//    public void insertBatch() {
//        System.out.println("# INSERT BATCH #");
//        List<Order> orders = Arrays.asList(this.createRandomOrder(), this.createRandomOrder(), this.createRandomOrder());
//        jdbi.withExtension(OrderDeclarativeRepository.class, repository -> {
//            repository.insertList(orders);
//            return true;
//        });
//    }
//
//    @GetMapping("/delete/{id}")
//    public void delete(@PathVariable String id) {
//        System.out.println("# DELETE BY ID #");
//        jdbi.withExtension(OrderDeclarativeRepository.class, repository -> repository.deleteById(id));
//    }

    private Order createRandomOrder() {
        double amount = ThreadLocalRandom.current().nextDouble(1000.00);
        return new Order(UUID.randomUUID().toString(), BigDecimal.valueOf(amount));
    }

}
