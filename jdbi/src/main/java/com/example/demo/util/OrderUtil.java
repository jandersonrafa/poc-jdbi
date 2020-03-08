package com.example.demo.util;


import com.example.demo.model.Item;
import com.example.demo.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class OrderUtil {


    public Order createRandomOrderWith5Itens() {
        double amount = ThreadLocalRandom.current().nextDouble(1000.00);
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setAmount(BigDecimal.valueOf(amount));
        String randomData = getRandomString();
        order.setOne(randomData);
        order.setTwo(randomData);
        order.setTree(randomData);
        order.setFour(randomData);
        order.setFive(randomData);
        order.setSix(randomData);
        order.setSeven(randomData);
        order.setEight(randomData);
        order.setNine(randomData);
        order.setTen(randomData);
        order.setItens(getRandomItens(order.getId()));
        return order;
    }

    public List<Item> getRandomItens(String orderId) {
        List<Item> itens = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            itens.add(newRandomItem(orderId));
        }
        return itens;
    }

    public Item newRandomItem(String orderId) {
        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        String randomData = getRandomString();
        item.setOne(randomData);
        item.setOrderid(orderId);
        item.setTwo(randomData);
        item.setTree(randomData);
        item.setFour(randomData);
        item.setFive(randomData);
        item.setSix(randomData);
        item.setSeven(randomData);
        item.setEight(randomData);
        item.setNine(randomData);
        item.setTen(randomData);
        return item;
    }

    private String getRandomString() {
        Random random = new Random();
        return String.valueOf(random.nextLong());
    }

}
