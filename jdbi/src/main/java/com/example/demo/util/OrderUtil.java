package com.example.demo.util;


import com.example.demo.model.Item;
import com.example.demo.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class OrderUtil {

    public Order createRandomOrderWithItems(Integer items) {
        Order orders = new Order();
        orders.setAmount(BigDecimal.TEN);
        orders.setActive(true);
        orders.setNumberorder(100);
        orders.setCreateddate(LocalDate.now());
        orders.setCreatedtime(LocalDateTime.now());
        String randomData = "testedatatestedatatestedata";
        orders.setOne(randomData);
        orders.setTwo(randomData);
        orders.setTree(randomData);
        orders.setFour(randomData);
        orders.setFive(randomData);
        orders.setSix(randomData);
        orders.setSeven(randomData);
        orders.setEight(randomData);
        orders.setNine(randomData);
        orders.setTen(randomData);
        orders.setItems(getRandomItems(items));
        return orders;
    }

    public List<Item> getRandomItems(Integer items) {
        List<Item> itens = new ArrayList<>();
        for (int i = 0; i < items; i++) {
            itens.add(newRandomItem());
        }
        return itens;
    }

    public Item newRandomItem() {
        Item items = new Item();
        String randomData = "testedatatestedatatestedata";
        items.setOne(randomData);
        items.setTwo(randomData);
        items.setTree(randomData);
        items.setFour(randomData);
        items.setFive(randomData);
        items.setSix(randomData);
        items.setSeven(randomData);
        items.setEight(randomData);
        items.setNine(randomData);
        items.setTen(randomData);
        return items;
    }

}
