/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.repository.OrderRepository;
import com.example.demo.model.Orders;
import com.example.demo.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author janderson
 */
@RestController
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderUtil orderUtil;

    @GetMapping("/insert/{items}")
    public Long insert(@PathVariable("items") Integer items) {
        Orders order = orderUtil.createRandomOrderWithItems(items);
        order.getItems().stream().forEach(it -> it.setOrder(order));
        return orderRepository.save(order).getId();
    }

    @GetMapping("/find/{id}")
    public Orders find(@PathVariable("id") Long id) {
       return orderRepository.findById(id).get();
    }

    @PutMapping("/update")
    public void update(@RequestBody Orders order) {
        order.setItems(null);
       orderRepository.save(order);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderRepository.deleteById(id);
    }

}
