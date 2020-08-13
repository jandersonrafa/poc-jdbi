/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.jdbi.config;

import com.example.demo.jdbi.repository.ItemRepository;
import com.example.demo.jdbi.repository.OrderRepository;
import com.example.demo.model.Item;
import com.example.demo.model.Order;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

/**
 * @author janderson
 */
@Configuration
public class RepositoryConfiguration {

    @Bean
    public OrderRepository orderRepository(Jdbi jdbi) {
        return jdbi.onDemand(OrderRepository.class);
    }

    @Bean
    public ItemRepository itemRepository(Jdbi jdbi) {
        return jdbi.onDemand(ItemRepository.class);
    }

}
