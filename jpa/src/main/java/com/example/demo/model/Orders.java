/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author janderson
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders  implements Persistable {

    @Id
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

    @Transient
    private List<Items> itens;

    @Override
    public boolean isNew() {
        return true;
    }
}
