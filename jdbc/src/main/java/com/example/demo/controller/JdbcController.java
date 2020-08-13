/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.model.Order;
import com.example.demo.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/insert/{items}")
    public Long insert(@PathVariable("items") Integer items) {
        Order order = orderUtil.createRandomOrderWithItems(items);
        Long id = orderRepository.save(order).getId();
        order.getItems().stream().forEach(it -> it.setOrderid(id));
        itemRepository.saveAll(order.getItems());
        return id;
    }

    @GetMapping("/find/{id}")
    public Order find(@PathVariable("id") Long id) {
        return orderRepository.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Order order) {
        orderRepository.update(order);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        itemRepository.deleteByOrderId(id);
        orderRepository.deleteById(id);
    }
}
