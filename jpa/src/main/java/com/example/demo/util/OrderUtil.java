package com.example.demo.util;


import com.example.demo.model.Items;
import com.example.demo.model.Orders;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class OrderUtil {

    public Orders createRandomOrderWith5Itens() {
        double amount = ThreadLocalRandom.current().nextDouble(1000.00);
        Orders orders = new Orders();
        orders.setId(UUID.randomUUID().toString());
        orders.setAmount(BigDecimal.valueOf(amount));
        String randomData = getRandomString();
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
        orders.setItens(getRandomItens(orders.getId()));
        return orders;
    }

    private String getRandomString() {
        Random random = new Random();
        return String.valueOf(random.nextLong());
    }

    public List<Items> getRandomItens(String orderId) {
        List<Items> itens = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            itens.add(newRandomItem(orderId));
        }
        return itens;
    }

    public Items newRandomItem(String orderId) {
        Items items = new Items();
        items.setId(UUID.randomUUID().toString());
        String randomData = getRandomString();
        items.setOne(randomData);
        items.setOrderid(orderId);
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
