/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jdbi.controller;

import com.example.demo.jdbi.repository.ItemDeclarativeRepository;
import com.example.demo.jdbi.repository.OrderDeclarativeRepository;
import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.util.OrderUtil;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author janderson
 */
@RestController
@RequestMapping("/declarative")
public class DeclarativeController {

    @Autowired
    private Jdbi jdbi;

    @Autowired
    private OrderUtil orderUtil;

    private static final int NUMBERS_ORDERS = 5000;

    @GetMapping("/insert")
    public String insert() {
        System.out.println("# JDBI - START INSERT #");
        Order order = orderUtil.createRandomOrderWith5Itens();
        jdbi.withExtension(OrderDeclarativeRepository.class, repository -> repository.insertNamed(order.getId(), order.getAmount()));
        System.out.println("# JDBI - END INSERT #");
        return order.getId();
    }

    @GetMapping("/get/{id}")
    public Order getById(@PathVariable String id) {
        System.out.println("# JDBI - GET BY ID #");
        return jdbi.withExtension(OrderDeclarativeRepository.class, repository -> repository.findById(id));
    }


    @GetMapping("/insert-batch")
    public void insertBatch() {
        run(() -> {
            List<Order> orders = new ArrayList<>();
            List<Item> itens = new ArrayList<>();
            for (int i = 0; i < NUMBERS_ORDERS; i++) {
                Order order = orderUtil.createRandomOrderWith5Itens();
                orders.add(order);
                itens.addAll(order.getItens());
            }
            PreparedBatch batch = jdbi.open().prepareBatch("INSERT INTO orders(id, amount, one, two, tree, four, five, six, seven, eight, nine, ten)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            orders.forEach(o -> {
                batch.bind(0, o.getId())
                        .bind(1, o.getAmount())
                        .bind(2, o.getOne())
                        .bind(3, o.getTwo())
                        .bind(4, o.getTree())
                        .bind(5, o.getFour())
                        .bind(6, o.getFive())
                        .bind(7, o.getSix())
                        .bind(8, o.getSeven())
                        .bind(9, o.getEight())
                        .bind(10, o.getNine())
                        .bind(11, o.getTen())
                        .add();
            });
            batch.execute();
            insertListItem(jdbi.open(), itens);
        }, "insert-batch");
    }

    @GetMapping("/insert-all-one-by-one")
    public void insertBatchAllOneByOne() {
        run(() -> {
            System.out.println("# JDBC - INSERT BATCH WITH NUMBER ORDERS= " + NUMBERS_ORDERS);
            for (int i = 0; i < NUMBERS_ORDERS; i++) {
                Order order = orderUtil.createRandomOrderWith5Itens();
                jdbi.useExtension(OrderDeclarativeRepository.class, repository -> repository.insertBean(order));
                order.getItens().forEach(it -> jdbi.useExtension(ItemDeclarativeRepository.class, repository -> repository.insertBean(it)));
            }
        }, "insert-all-one-by-one");
    }

    private void insertListItem(Handle open, List<Item> itens) {
        PreparedBatch batch = open.prepareBatch("INSERT INTO items(id, orderid, one, two, tree, four, five, six, seven, eight, nine, ten)  VALUES (:id, :orderid, :one, :two, :tree, :four, :five, :six, :seven, :eight, :nine, :ten)");
        itens.forEach(o -> {
            batch.bind("id", o.getId())
                    .bind("orderid", o.getOrderid())
                    .bind("one", o.getOne())
                    .bind("two", o.getTwo())
                    .bind("tree", o.getTree())
                    .bind("four", o.getFour())
                    .bind("five", o.getFive())
                    .bind("six", o.getSix())
                    .bind("seven", o.getSeven())
                    .bind("eight", o.getEight())
                    .bind("nine", o.getNine())
                    .bind("ten", o.getTen())
                    .add();
        });
        batch.execute();
    }

    @GetMapping("/full-test")
    public void fullTest() {
        Handle open = jdbi.open();
        run(() -> {
            for (int i = 0; i < NUMBERS_ORDERS; i++) {
                Order order = orderUtil.createRandomOrderWith5Itens();
                jdbi.useExtension(OrderDeclarativeRepository.class, repository -> repository.insertBean(order));
                insertListItemOneByOne(order);
//                insertListItem(open, order.getItens());
//                jdbi.useExtension(ItemDeclarativeRepository.class, repository -> repository.insertList(order.getItens()));
                Order orderSaved = jdbi.withExtension(OrderDeclarativeRepository.class, repository -> repository.findById(order.getId()));
                orderSaved.getId();
                Set<Item> items = jdbi.withExtension(ItemDeclarativeRepository.class, repository -> repository.findByOrderId(order.getId()));
                items.forEach(it-> it.getOrderid());
                int count = jdbi.withExtension(ItemDeclarativeRepository.class, repository -> repository.deleteByOrderId(order.getId()));
                int counto = jdbi.withExtension(OrderDeclarativeRepository.class, repository -> repository.deleteById(order.getId()));
            }
        }, "full-test");
        open.close();
    }

    private void insertListItemOneByOne(Order order) {
        order.getItens().forEach(it -> {
            jdbi.useExtension(ItemDeclarativeRepository.class, repository -> repository.insertBean(it));
        });
    }

    @GetMapping("/select-all-one-by-one")
    public void selectAllOneByOne() {
        run(() -> {
            List<String> orders = this.getOrderIds();
            orders.forEach(o -> {
                jdbi.withExtension(OrderDeclarativeRepository.class, repository -> repository.findById(o));
                jdbi.withExtension(ItemDeclarativeRepository.class, repository -> repository.findByOrderId(o));
            });
        }, "select-all-one-by-one");
    }

    @GetMapping("/delete-all-one-by-one")
    public void deleteAllOneByOne() {
        List<String> orders = this.getOrderIds();
        run(() -> {
            orders.forEach(o -> {
                jdbi.useExtension(ItemDeclarativeRepository.class, repository -> repository.deleteByOrderId(o));
                jdbi.useExtension(OrderDeclarativeRepository.class, repository -> repository.deleteById(o));
            });
        }, "delete-all-one-by-one");
    }

    @GetMapping("/all")
    public List<Order> findAll() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("# JDBI - ALL #");
        List<Order> orders = jdbi.withExtension(OrderDeclarativeRepository.class, repository -> repository.findAll());
        stopWatch.stop();
        System.out.println("# JDBI - END ALL# " + +stopWatch.getTotalTimeSeconds());
        return orders;
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        System.out.println("# JDBI - DELETE BY ID #");
        jdbi.useExtension(OrderDeclarativeRepository.class, repository -> repository.deleteById(id));
    }

    private List<String> getOrderIds() {
        return jdbi.withExtension(OrderDeclarativeRepository.class, repository -> repository.findAllIds());
    }

    private void run(Runnable run, String word) {
        System.out.println("# JDBI DECLARATIVE - " + word);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        run.run();
        stopWatch.stop();
        System.out.println("# JDBI DECLARATIVE - END " + word + " : " + stopWatch.getTotalTimeSeconds());
    }
}