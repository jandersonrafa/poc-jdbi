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

/**
 *
 * @author janderson
 */
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class Item {

    private String id;
    private String orderid;
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

    @JdbiConstructor
    public Item(String id, String orderid, String one, String two, String tree, String four, String five, String six, String seven, String eight, String nine, String ten) {
        this.id = id;
        this.orderid = orderid;
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
