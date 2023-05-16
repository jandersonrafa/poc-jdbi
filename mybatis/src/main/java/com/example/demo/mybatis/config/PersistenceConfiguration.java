/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.mybatis.config;

import com.example.demo.model.Item;
import com.example.demo.model.Order;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

/**
 * @author janderson
 */
@Configuration
@MapperScan("com.example.demo.mybatis")
public class PersistenceConfiguration {

    @Autowired
    private DataSource dataSource;

//    @Bean
//    public Jdbi jdbi(DataSource dataSource) {
//        // JDBI wants to control the Connection wrap the datasource in a proxy
//        // That is aware of the Spring managed transaction
//        TransactionAwareDataSourceProxy dataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
//        Jdbi jdbi = Jdbi.create(dataSourceProxy);
//        jdbi.installPlugins();
//        jdbi.registerRowMapper(Order.class, ConstructorMapper.of(Order.class));
//        jdbi.registerRowMapper(Item.class, ConstructorMapper.of(Item.class));
//
//        return jdbi;
//    }

}
