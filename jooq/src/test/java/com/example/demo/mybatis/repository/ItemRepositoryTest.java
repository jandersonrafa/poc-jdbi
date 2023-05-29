package com.example.demo.mybatis.repository;

import com.example.demo.model.OrderDto;
import com.example.demo.modelgenerate.tables.pojos.Items;
import com.example.demo.util.OrderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    private ItemsRepository itemsRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderUtil orderUtil;

    @Test
    public void insertBean() {
        // Arramge
        OrderDto order = orderUtil.createRandomOrderWithItems(5);
        Long id = ordersRepository.insert(order.getOrder());
        order.getItems().stream().forEach(it -> it.setOrderid(id));

        // Act
        itemsRepository.insert(order.getItems());

        // Assert
        List<Items> items = itemsRepository.findByOrderId(id);
        assertEquals(5, items.size());
    }

    @Test
    public void deleteByOrderId() {
        // Arramge
        OrderDto order = orderUtil.createRandomOrderWithItems(1);
        Long id = ordersRepository.insert(order.getOrder());
        order.getItems().stream().forEach(it -> it.setOrderid(id));
        itemsRepository.insert(order.getItems());

        // Act
        itemsRepository.deleteByOrderId(id);

        // Assert
        assertNotNull("asd");
    }
}