/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Items;
import com.example.demo.model.Orders;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author janderson
 */
@RestController
@RequestMapping("/spring-data-jdbc")
public class SpringDataJdbcController {

    private static final int NUMBERS_ORDERS = 5000;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderUtil orderUtil;

    @GetMapping("/insert-batch")
    public void insertBatch() {
        run(() -> {
            List<Orders> orders = new ArrayList<>();
            List<Items> itens = new ArrayList<>();
            System.out.println("# SPRING DATA JDBC - INSERT BATCH WITH NUMBER ORDERS= " + NUMBERS_ORDERS);
            for (int i = 0; i < NUMBERS_ORDERS; i++) {
                Orders order = orderUtil.createRandomOrderWith5Itens();
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
            System.out.println("# SPRING DATA JDBC - INSERT BATCH WITH NUMBER ORDERS= " + NUMBERS_ORDERS);
            for (int i = 0; i < NUMBERS_ORDERS; i++) {
                Orders order = orderUtil.createRandomOrderWith5Itens();
                orderRepository.save(order);
                order.getItens().forEach(it -> itemRepository.save(it));
            }
        }, "insert-all-one-by-one");
    }

    @GetMapping("/full-test")
    public void fullTest() {
        run(() -> {
            for (int i = 0; i < NUMBERS_ORDERS; i++) {
                Orders orders = orderUtil.createRandomOrderWith5Itens();
                Orders save = orderRepository.save(orders);
                save.getId();
                orders.getItens().forEach(it -> itemRepository.save(it));
                Orders ordersSaved = orderRepository.findById(orders.getId()).get();
                List<Items> itemsSaved = itemRepository.findByOrderid(orders.getId());
                itemsSaved.forEach(it -> it.getId());
                delete(ordersSaved.getId());
            }
        }, "full-test");
    }

    @GetMapping("/select-all-one-by-one")
    public void selectAllOneByOne() {
        run(() -> {
            List<String> orders = this.getOrderIds();
            orders.forEach(o -> {
                Orders order = orderRepository.findById(o).get();
                List<Items> itens = itemRepository.findByOrderid(o);
            });
        }, "select-all-one-by-one");
    }

    @GetMapping("/delete-all-one-by-one")
    public void deleteAllOneByOne() {
        List<String> orders = this.getOrderIds();
        run(() -> {
            orders.forEach(o -> {
                delete(o);
            });
        }, "delete-all-one-by-one");
    }

    public void delete(String o) {
        int count = itemRepository.deleteByOrderid(o);
        orderRepository.deleteById(o);
    }

    private List<String> getOrderIds() {
        return orderRepository.findAllIds().stream().map(OrderRepository.OrderPK::getId).collect(toList());
    }

    private void run(Runnable run, String word) {
        System.out.println("# SPRING DATA JDBC - " + word);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        run.run();
        stopWatch.stop();
        System.out.println("# SPRING DATA JDBC - END " + word + " : " + stopWatch.getTotalTimeSeconds());
    }
}
