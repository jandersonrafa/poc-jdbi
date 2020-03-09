/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.datajdbc.repository.ItemRepository;
import com.example.demo.datajdbc.repository.OrderRepository;
import com.example.demo.model.Items;
import com.example.demo.model.Orders;
import com.example.demo.service.OrderService;
import com.example.demo.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author janderson
 */
@RestController
@RequestMapping("/jpa")
public class JpaController {

    private static final int NUMBERS_ORDERS = 5000;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderUtil orderUtil;

    @Autowired
    private OrderService orderService;



    @GetMapping("/insert")
    public String insert() {
        return orderRepository.save(orderUtil.createRandomOrderWith5Itens()).getId();
    }

    @GetMapping("/get/{id}")
    public Orders getById(@PathVariable String id) {
        System.out.println("# JPA - GET BY ID #");
        return orderRepository.findById(id).get();
    }

    @GetMapping("/all")
    public void findAll() {
        run(() -> {
            Iterable<Orders> all = orderRepository.findAll();
        }, "all");
    }

    @GetMapping("/insert-batch")
    public void insertBatch() {
        run(() -> {
            List<Orders> orders = new ArrayList<>();
            List<Items> itens = new ArrayList<>();
            System.out.println("# JPA - INSERT BATCH WITH NUMBER ORDERS= " + NUMBERS_ORDERS);
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
            System.out.println("# JPA - INSERT BATCH WITH NUMBER ORDERS= " + NUMBERS_ORDERS);
            for (int i = 0; i < NUMBERS_ORDERS; i++) {
                Orders order = orderUtil.createRandomOrderWith5Itens();
                orderRepository.save(order);
                order.getItens().forEach(itemRepository::save);
            }
        }, "insert-all-one-by-one");
    }

    @GetMapping("/full-test")
    public void fullTest() {
        run(() -> {
            for (int i = 0; i < NUMBERS_ORDERS; i++) {
                Orders orders = orderUtil.createRandomOrderWith5Itens();
                orderRepository.save(orders);
                List<Items> items = itemRepository.saveAll(orders.getItens());
                Orders ordersSaved = orderRepository.findById(orders.getId()).get();
                ordersSaved.getId();
                List<Items> byOrderid = itemRepository.findByOrderid(orders.getId());
                byOrderid.forEach(Items::getId);
//                entityManager.flush();
//                entityManager.clear();
//                int count = itemRepository.deleteByOrderid(orders.getId());
//                orderRepository.deleteById(orders.getId());
//                entityManager.flush();
//                entityManager.clear();
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
                orderService.deleteAllOneByOne(o);
            });
        }, "delete-all-one-by-one");
    }

    private List<String> getOrderIds() {
        return orderRepository.findAllIds().stream().map(OrderRepository.OrderPK::getId).collect(toList());
    }

    private void run(Runnable run, String word) {
        System.out.println("# JPA - " + word);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        run.run();
        stopWatch.stop();
        System.out.println("# JPA - END " + word + " : " + stopWatch.getTotalTimeSeconds());
    }
}
