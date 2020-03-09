package com.example.demo.service;


import com.example.demo.datajdbc.repository.ItemRepository;
import com.example.demo.datajdbc.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class OrderService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void deleteAllOneByOne(String id) {
        int count = itemRepository.deleteByOrderid(id);
        orderRepository.deleteById(id);
        entityManager.flush();
        entityManager.clear();
    }

}
