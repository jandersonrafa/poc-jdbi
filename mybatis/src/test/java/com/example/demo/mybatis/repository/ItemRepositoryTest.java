package com.example.demo.mybatis.repository;

import com.example.demo.model.Order;
import com.example.demo.util.OrderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired

    private OrderUtil orderUtil;

    @Test
    public void insertBean() {
        // Arramge
        Order order = orderUtil.createRandomOrderWithItems(1);
        Long id = orderRepository.insertBean(order);
        order.getItems().stream().forEach(it -> it.setOrderid(id));

        // Act
        itemRepository.insertList(order.getItems());

        // Assert
        assertNotNull("asd");
    }

    @Test
    public void deleteByOrderId() {
        // Arramge
        Order order = orderUtil.createRandomOrderWithItems(1);
        Long id = orderRepository.insertBean(order);
        order.getItems().stream().forEach(it -> it.setOrderid(id));
        itemRepository.insertList(order.getItems());

        // Act
        itemRepository.deleteByOrderId(id);

        // Assert
        assertNotNull("asd");
    }
}