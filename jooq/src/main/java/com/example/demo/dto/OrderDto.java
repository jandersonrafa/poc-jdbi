package com.example.demo.dto;

import com.example.demo.modelgenerate.tables.pojos.Items;
import com.example.demo.modelgenerate.tables.pojos.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderDto {

    private Orders order;
    private List<Items> items;
}
