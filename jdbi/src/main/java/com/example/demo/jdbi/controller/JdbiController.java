/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jdbi.controller;

import com.example.demo.jdbi.repository.ItemRepository;
import com.example.demo.jdbi.repository.OrderRepository;
import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.util.OrderUtil;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author janderson
 */
@RestController
@RequestMapping("/jdbi")
public class JdbiController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderUtil orderUtil;

    @GetMapping("/insert/{items}")
    public Long insert(@PathVariable("items") Integer items) {
        Order order = orderUtil.createRandomOrderWithItems(items);
        Long id = orderRepository.insertBean(order);
        order.getItems().stream().forEach(it -> it.setOrderid(id));
        itemRepository.insertList(order.getItems());
        return id;
    }

    @GetMapping("/find/{id}")
    public Order find(@PathVariable("id") Long id) {
        return orderRepository.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Order order) {
        orderRepository.updateBean(order);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        itemRepository.deleteByOrderId(id);
        orderRepository.deleteById(id);
    }

}