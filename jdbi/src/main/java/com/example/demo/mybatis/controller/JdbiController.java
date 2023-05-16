/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.mybatis.controller;

import com.example.demo.mybatis.repository.ItemRepository;
import com.example.demo.mybatis.repository.OrderRepository;
import com.example.demo.model.Order;
import com.example.demo.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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