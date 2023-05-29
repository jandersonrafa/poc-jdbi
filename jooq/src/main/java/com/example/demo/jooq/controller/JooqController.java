/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jooq.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.jooq.repository.ItemsRepository;
import com.example.demo.jooq.repository.OrdersRepository;
import com.example.demo.modelgenerate.tables.daos.ItemsDao;
import com.example.demo.modelgenerate.tables.daos.OrdersDao;
import com.example.demo.modelgenerate.tables.pojos.Orders;
import com.example.demo.util.OrderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author janderson
 */
@RestController
@RequestMapping("/jooq")
@RequiredArgsConstructor
public class JooqController {


    private final OrdersRepository ordersRepository;

    private final ItemsRepository itemsRepository;

    @Autowired
    private OrderUtil orderUtil;

    @GetMapping("/insert/{items}")
    public Long insert(@PathVariable("items") Integer items) {
        OrderDto order = orderUtil.createRandomOrderWithItems(items);
        Long id = ordersRepository.insert(order.getOrder());
        order.getItems().stream().forEach(it -> it.setOrderid(id));
        itemsRepository.insert(order.getItems());
        return id;
    }

    @GetMapping("/find/{id}")
    public Orders find(@PathVariable("id") Long id) {
        return ordersRepository.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Orders order) {
        ordersRepository.update(order);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        itemsRepository.deleteByOrderId(id);
        ordersRepository.deleteById(id);
    }

}