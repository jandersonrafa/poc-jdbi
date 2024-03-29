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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/insert/{items}")
    public Long insert(@PathVariable("items") Integer items) {
        Orders order = orderUtil.createRandomOrderWithItems(items);
        Long id = orderRepository.save(order).getId();
        order.getItems().stream().forEach(it -> it.setOrderid(id));
        itemRepository.saveAll(order.getItems());
        return id;
    }

    @GetMapping("/find/{id}")
    public Orders find(@PathVariable("id") Long id) {
        return orderRepository.findById(id).get();
    }

    @PutMapping("/update")
    public void update(@RequestBody Orders order) {
        orderRepository.save(order);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        itemRepository.deleteByOrderid(id);
        orderRepository.deleteById(id);

    }

}
