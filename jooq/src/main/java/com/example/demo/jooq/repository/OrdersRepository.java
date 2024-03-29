package com.example.demo.jooq.repository;

import com.example.demo.modelgenerate.tables.daos.OrdersDao;
import com.example.demo.modelgenerate.tables.pojos.Items;
import com.example.demo.modelgenerate.tables.pojos.Orders;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import static com.example.demo.modelgenerate.Tables.ITEMS;
import static com.example.demo.modelgenerate.Tables.ORDERS;

@Repository
@RequiredArgsConstructor
public class OrdersRepository {
    private final DSLContext context;

    private final OrdersDao ordersDao;

    public Long insert (Orders orders) {
        return context.insertInto(ORDERS)
                .set(ORDERS.AMOUNT, orders.getAmount())
                .set(ORDERS.ACTIVE, orders.getActive())
                .set(ORDERS.CREATEDDATE, orders.getCreateddate())
                .set(ORDERS.CREATEDTIME, orders.getCreatedtime())
                .set(ORDERS.NUMBERORDER, orders.getNumberorder())
                .set(ORDERS.ONE, orders.getOne())
                .set(ORDERS.TWO, orders.getTwo())
                .set(ORDERS.TREE, orders.getTree())
                .set(ORDERS.FOUR, orders.getFour())
                .set(ORDERS.FIVE, orders.getFive())
                .set(ORDERS.SIX, orders.getSix())
                .set(ORDERS.SEVEN, orders.getSeven())
                .set(ORDERS.EIGHT, orders.getEight())
                .set(ORDERS.NINE, orders.getNine())
                .set(ORDERS.TEN, orders.getTen())
                .returning(ORDERS.ID).fetchOne().getId();
    }

    public Orders findById(Long id) {
        return ordersDao.findById(id);
    }
    public Orders findAllById(Long id) {
        Result<Record> fetch = context.select()
                .from(ORDERS.join(ITEMS).on(ORDERS.ID.eq(ITEMS.ORDERID)))
                .where(ORDERS.ID.eq(id))

                .fetch();
        return null;
    }

    public void update(Orders order) {
        ordersDao.update(order);
    }

    public void deleteById(Long id) {
        ordersDao.deleteById(id);
    }
}
