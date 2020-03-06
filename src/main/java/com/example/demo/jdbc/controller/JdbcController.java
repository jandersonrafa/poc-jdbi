/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jdbc.controller;

import com.example.demo.jdbc.repository.ItemRepository;
import com.example.demo.jdbc.repository.OrderRepository;
import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author janderson
 */
@RestController
@RequestMapping("/jdbc")
public class JdbcController {

    private static final int NUMBERS_ORDERS = 5000;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderUtil orderUtil;

    @GetMapping("/insert")
    public String insert() {
       return orderRepository.save(this.createRandomOrder()).getId();
    }

    @GetMapping("/get/{id}")
    public Order getById(@PathVariable String id) {
        System.out.println("# JDBC - GET BY ID #");
        return orderRepository.findById(id);
    }

    @GetMapping("/all")
    public List<Order> findAll() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("# JDBC - ALL #");
        List<Order> all = orderRepository.findAll();
        stopWatch.stop();
        System.out.println("# JDBC - END ALL# " + +stopWatch.getTotalTimeSeconds());
        return all;
//        return jdbi.withExtension(OrderDeclarativeRepository.class, repository -> repository.findAll());
    }


    @GetMapping("/insert-batch")
    public void insertBatch() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Order> orders = new ArrayList<>();
        List<Item> itens = new ArrayList<>();
        System.out.println("# JDBC - INSERT BATCH WITH NUMBER ORDERS= " + NUMBERS_ORDERS);
        for (int i = 0; i < NUMBERS_ORDERS; i++) {
            Order order = orderUtil.createRandomOrderWith5Itens();
            orders.add(order);
            itens.addAll(order.getItens());
        }
        orderRepository.saveAll(orders);
        itemRepository.saveAll(itens);
        stopWatch.stop();
        System.out.println("# JDBC - END BATCH INSERT# " + +stopWatch.getTotalTimeSeconds());
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

    @GetMapping("/delete/all-one-by-one")
    public void deleteAllOneByOne() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("# JDBC - DELETE ALL SELECT ALL #");
        List<String> orders = this.getOrderIds();
        System.out.println("# JDBC - DELETE ALL ONE BY ONE : " + orders.size());
        orders.forEach(o -> {
            Order order = orderRepository.findById(o);
            Set<Item> itens = itemRepository.findByOrderId(o);
            itemRepository.deleteByOrderId(order.getId());
            orderRepository.deleteById(order.getId());
        });
        stopWatch.stop();
        System.out.println("# JDBC - END DELETE ALL ONE BY ONE #" + +stopWatch.getTotalTimeSeconds());
    }


    @GetMapping("/all-one-by-one")
    public void selectAllOneByOne() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("# JDBC - ALL SELECT ALL #");
        List<String> orders = this.getOrderIds();
        System.out.println("# JDBC - SELECT ALL ONE BY ONE : " + orders.size());
        orders.forEach(o -> {
            Order order = orderRepository.findById(o);
//            Set<Item> itens = itemRepository.findByOrderId(o);
        });
        stopWatch.stop();
        System.out.println("# JDBC - END SELECT ALL ONE BY ONE #" + +stopWatch.getTotalTimeSeconds());
    }


    private Order createRandomOrder() {
        double amount = ThreadLocalRandom.current().nextDouble(1000.00);
        return new Order(UUID.randomUUID().toString(), BigDecimal.valueOf(amount));
    }

    private List<String> getOrderIds() {
        return orderRepository.findAllIds();
    }
}
