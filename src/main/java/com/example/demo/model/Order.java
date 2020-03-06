/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * @author janderson
 */
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class Order {

    private String id;
    private BigDecimal amount;
    private String one;
    private String two;
    private String tree;
    private String four;
    private String five;
    private String six;
    private String seven;
    private String eight;
    private String nine;
    private String ten;
    private Set<Item> itens;

//    @JdbiConstructor
    public Order(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    @JdbiConstructor
    public Order(String id, BigDecimal amount, String one, String two, String tree, String four, String five, String six, String seven, String eight, String nine, String ten) {
        this.id = id;
        this.amount = amount;
        this.one = one;
        this.two = two;
        this.tree = tree;
        this.four = four;
        this.five = five;
        this.six = six;
        this.seven = seven;
        this.eight = eight;
        this.nine = nine;
        this.ten = ten;
    }

}
