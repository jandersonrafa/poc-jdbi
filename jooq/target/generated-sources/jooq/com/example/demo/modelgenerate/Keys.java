/*
 * This file is generated by jOOQ.
 */
package com.example.demo.modelgenerate;


import com.example.demo.modelgenerate.tables.Items;
import com.example.demo.modelgenerate.tables.Orders;
import com.example.demo.modelgenerate.tables.records.ItemsRecord;
import com.example.demo.modelgenerate.tables.records.OrdersRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * jdbi.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ItemsRecord> ITEMS_PKEY = Internal.createUniqueKey(Items.ITEMS, DSL.name("items_pkey"), new TableField[] { Items.ITEMS.ID }, true);
    public static final UniqueKey<OrdersRecord> ORDERS_PKEY = Internal.createUniqueKey(Orders.ORDERS, DSL.name("orders_pkey"), new TableField[] { Orders.ORDERS.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<ItemsRecord, OrdersRecord> ITEMS__ITEMS_FKEY = Internal.createForeignKey(Items.ITEMS, DSL.name("items_fkey"), new TableField[] { Items.ITEMS.ORDERID }, Keys.ORDERS_PKEY, new TableField[] { Orders.ORDERS.ID }, true);
}