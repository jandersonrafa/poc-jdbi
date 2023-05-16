package com.example.demo.mybatis.repository;

import com.example.demo.model.Order;
import com.example.demo.util.OrderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired

    private OrderUtil orderUtil;

    @Test
    public void insertBean() {
        // Arramge
        Order order = orderUtil.createRandomOrderWithItems(1);

        // Act
        Long id = orderRepository.insertBean(order);

        // Assert
        assertNotNull(orderRepository.findById(id));
    }
    @Test
    public void delete() {
        // Arramge
        Order order = orderUtil.createRandomOrderWithItems(1);
        Long id = orderRepository.insertBean(order);

        // Act
        orderRepository.deleteById(id);

        // Assert
        assertNull(orderRepository.findById(id));
    }
    @Test
    public void update() {
        // Arramge
        Order order = orderUtil.createRandomOrderWithItems(1);
        Long id = orderRepository.insertBean(order);
        order.setFive("teste");
        order.setId(id);

        // Act
        orderRepository.updateBean(order);

        // Assert
        Order result = orderRepository.findById(id);
        assertEquals("teste", result.getFive());
    }
}