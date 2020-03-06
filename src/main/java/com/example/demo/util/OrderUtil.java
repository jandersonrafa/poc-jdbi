package com.example.demo.util;


import com.example.demo.model.Item;
import com.example.demo.model.Order;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class OrderUtil {


    public Order createRandomOrderWith5Itens() {
        double amount = ThreadLocalRandom.current().nextDouble(1000.00);
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setAmount(BigDecimal.valueOf(amount));
        RandomString randomString = new RandomString();
        String randomData = randomString.nextString();
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

    public Set<Item> getRandomItens(String orderId) {
        Set<Item> itens = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            itens.add(newRandomItem(orderId));
        }
        return itens;
    }

    public Item newRandomItem(String orderId) {
        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        RandomString randomString = new RandomString();
        String randomData = randomString.nextString();
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

}
