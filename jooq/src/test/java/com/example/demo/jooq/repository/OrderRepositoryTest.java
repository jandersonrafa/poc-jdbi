package com.example.demo.jooq.repository;

import com.example.demo.dto.OrderDto;
import com.example.demo.modelgenerate.tables.daos.OrdersDao;
import com.example.demo.modelgenerate.tables.pojos.Orders;
import com.example.demo.util.OrderUtil;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderUtil orderUtil;

    @Test
    public void insertBean() {
        // Arramge
        OrderDto order = orderUtil.createRandomOrderWithItems(1);

        // Act
        Long id = ordersRepository.insert(order.getOrder());

        // Assert
        assertNotNull(ordersRepository.findById(id));
    }
    @Test
    public void delete() {
        // Arramge
        OrderDto order = orderUtil.createRandomOrderWithItems(1);
        Long id = ordersRepository.insert(order.getOrder());

        // Act
        ordersRepository.deleteById(id);

        // Assert
        assertNull(ordersRepository.findById(id));
    }
    @Test
    public void update() {
        // Arramge
        OrderDto order = orderUtil.createRandomOrderWithItems(1);
        Long id = ordersRepository.insert(order.getOrder());
        order.getOrder().setFive("teste");
        order.getOrder().setId(id);

        // Act
        ordersRepository.update(order.getOrder());

        // Assert
        Orders result = ordersRepository.findById(id);
        assertEquals("teste", result.getFive());
    }
}