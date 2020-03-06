/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jdbi.controller;

import com.example.demo.jdbi.repository.ItemFluentRepository;
import com.example.demo.jdbi.repository.OrderDeclarativeRepository;
import com.example.demo.jdbi.repository.OrderFluentRepository;
import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.util.OrderUtil;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 *
 * @author janderson
 */
@RestController
@RequestMapping("/fluent")
public class FluentController {
    private static final int NUMBERS_ORDERS = 5000;

    @Autowired
    private OrderFluentRepository orderFluentRepository;

    @Autowired
    private ItemFluentRepository itemFluentRepository;

    @Autowired
    private Jdbi jdbi;

    @Autowired
    private OrderUtil orderUtil;

    @GetMapping("/insert")
    public String insert() {
        System.out.println("# INSERT #");
        final Order save = orderFluentRepository.save(this.createRandomOrder());
        return save.getId();
    }

    @GetMapping("/get/{id}")
    public Order getById(@PathVariable String id) {
        System.out.println("# GET BY ID #");
        return orderFluentRepository.findById(id);
    }

    @GetMapping("/all")
    public List<Order> findAll() {
        System.out.println("# ALL #");
        return orderFluentRepository.findAll();
    }

//    @GetMapping("/insert-batch")
//    public List<Order> insertBatch() {
//        System.out.println("# INSERT BATCH #");
//        List<Order> orders = Arrays.asList(this.createRandomOrder(), this.createRandomOrder(), this.createRandomOrder());
//        orderFluentRepository.saveAll(orders);
//        return orders;
//    }


    @GetMapping("/insert-batch")
    public void insertBatch() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Order> orders = new ArrayList<>();
        List<Item> itens = new ArrayList<>();
        System.out.println("# JDBI FLUENT- INSERT BATCH WITH NUMBER ORDERS= " + NUMBERS_ORDERS);
        for (int i = 0; i < NUMBERS_ORDERS; i++) {
            Order order = orderUtil.createRandomOrderWith5Itens();
            orders.add(order);
            itens.addAll(order.getItens());
        }
        orderFluentRepository.saveAll(orders);
        itemFluentRepository.saveAll(itens);
        stopWatch.stop();
        System.out.println("# JDBI FLUENT- END BATCH INSERT# " + +stopWatch.getTotalTimeSeconds());
    }


    @GetMapping("/delete/all-one-by-one")
    public void deleteAllOneByOne() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("# JDBI FLUENT - DELETE ALL SELECT ALL #");
        List<String> orders = this.getOrderIds();
        System.out.println("# JDBI FLUENT- DELETE ALL ONE BY ONE : " + orders.size());
        orders.forEach(o -> {
            Order order = orderFluentRepository.findById(o);
            itemFluentRepository.findByOrderId(o);
            itemFluentRepository.deleteByOrderId(order.getId());
            orderFluentRepository.deleteById(order.getId());
        });
        stopWatch.stop();
        System.out.println("# JDBI FLUENT- END DELETE ALL ONE BY ONE #" + +stopWatch.getTotalTimeSeconds());
    }

    @GetMapping("/delete/stream-all-one-by-one")
    @Transactional
    public void streamDeleteAllOneByOne() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("# JDBI FLUENT STREAM - DELETE ALL SELECT ALL #");
        Stream<String> orders = this.getStreamOrderIds();
        System.out.println("# JDBI FLUENT STREAM- DELETE ALL ONE BY ONE : ");
        orders.forEach(o -> {
            Order order = orderFluentRepository.findById(o);
            itemFluentRepository.findStreamByOrderId(o);
            itemFluentRepository.deleteByOrderId(order.getId());
            orderFluentRepository.deleteById(order.getId());
        });
        stopWatch.stop();
        System.out.println("# JDBI FLUENT- END DELETE ALL ONE BY ONE #" + +stopWatch.getTotalTimeSeconds());
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

    private List<String> getOrderIds() {
        return jdbi.withExtension(OrderDeclarativeRepository.class, repository -> repository.findAllIds());
    }
    private Stream<String> getStreamOrderIds() {
        return jdbi.withExtension(OrderDeclarativeRepository.class, repository -> repository.findStreamAllIds());
    }
}
