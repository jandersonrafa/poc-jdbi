/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.datajdbc.repository.ItemRepository;
import com.example.demo.datajdbc.repository.OrderRepository;
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
    public void findAll() {
        run(() -> {
            List<Order> all = orderRepository.findAll();
        }, "all");
    }

    @GetMapping("/insert-batch")
    public void insertBatch() {
        run(() -> {
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
        }, "insert-batch");
    }

    @GetMapping("/insert-all-one-by-one")
    public void insertBatchAllOneByOne() {
        run(() -> {
            System.out.println("# JDBC - INSERT BATCH WITH NUMBER ORDERS= " + NUMBERS_ORDERS);
            for (int i = 0; i < NUMBERS_ORDERS; i++) {
                Order order = orderUtil.createRandomOrderWith5Itens();
                orderRepository.save(order);
                order.getItens().forEach(itemRepository::save);
            }
        }, "insert-all-one-by-one");
    }

    @GetMapping("/full-test")
    public void fullTest() {
        run(() -> {
            for (int i = 0; i < NUMBERS_ORDERS; i++) {
                Order order = orderUtil.createRandomOrderWith5Itens();
                orderRepository.save(order);
//                itemRepository.saveAll(order.getItens());
                itemRepository.saveAllOneByOne(order.getItens());
                Order oSaved = orderRepository.findById(order.getId());
                oSaved.getId();
                Set<Item> items = itemRepository.findByOrderId(order.getId());
                items.forEach(it -> it.getOrderid());
                int count = itemRepository.deleteByOrderId(oSaved.getId());
                int counto = orderRepository.deleteById(oSaved.getId());
            }
        }, "full-test");
    }

    @GetMapping("/select-all-one-by-one")
    public void selectAllOneByOne() {
        run(() -> {
            List<String> orders = this.getOrderIds();
            orders.forEach(o -> {
                Order order = orderRepository.findById(o);
                Set<Item> itens = itemRepository.findByOrderId(o);
            });
        }, "select-all-one-by-one");
    }

    @GetMapping("/delete-all-one-by-one")
    public void deleteAllOneByOne() {
        List<String> orders = this.getOrderIds();
        run(() -> {
            orders.forEach(o -> {
                itemRepository.deleteByOrderId(o);
                orderRepository.deleteById(o);
            });
        }, "delete-all-one-by-one");
    }

    private Order createRandomOrder() {
        double amount = ThreadLocalRandom.current().nextDouble(1000.00);
        return new Order(UUID.randomUUID().toString(), BigDecimal.valueOf(amount));
    }

    private List<String> getOrderIds() {
        return orderRepository.findAllIds();
    }

    private void run(Runnable run, String word) {
        System.out.println("# JDBC - " + word);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        run.run();
        stopWatch.stop();
        System.out.println("# JDBC - END " + word + " : " + stopWatch.getTotalTimeSeconds());
    }
}
