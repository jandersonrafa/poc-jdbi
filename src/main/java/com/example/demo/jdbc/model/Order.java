package com.example.demo.jdbc.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Order {

    private String id;
    private BigDecimal amount;

    @JdbiConstructor
    public Order(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }
}
