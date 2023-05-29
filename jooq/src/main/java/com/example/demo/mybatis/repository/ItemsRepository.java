package com.example.demo.mybatis.repository;

import com.example.demo.modelgenerate.tables.daos.ItemsDao;
import com.example.demo.modelgenerate.tables.pojos.Items;
import com.example.demo.modelgenerate.tables.records.ItemsRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.*;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.modelgenerate.Tables.ITEMS;

@Repository
@RequiredArgsConstructor
public class ItemsRepository {
    private final DSLContext context;

    private final ItemsDao itemsDao;

    public Long insert (Items orders) {
        return context.insertInto(ITEMS)
                .set(ITEMS.ONE, orders.getOne())
                .set(ITEMS.TWO, orders.getTwo())
                .set(ITEMS.TREE, orders.getTree())
                .set(ITEMS.FOUR, orders.getFour())
                .set(ITEMS.FIVE, orders.getFive())
                .set(ITEMS.SIX, orders.getSix())
                .set(ITEMS.SEVEN, orders.getSeven())
                .set(ITEMS.EIGHT, orders.getEight())
                .set(ITEMS.NINE, orders.getNine())
                .set(ITEMS.TEN, orders.getTen())
                .set(ITEMS.ORDERID, orders.getOrderid())
                .returning(ITEMS.ID).fetchOne().getId();
    }

    public Items findById(Long id) {
        return itemsDao.findById(id);
    }

    public void update(Items order) {
        itemsDao.update(order);
    }

    public void deleteById(Long id) {
        itemsDao.deleteById(id);
    }

    public void deleteByOrderId(Long id) {
        context.deleteFrom(ITEMS).where(ITEMS.ORDERID.eq(id)).execute();
    }

    public void insert(List<Items> items) {
        List<ItemsRecord> list = items.stream().map(it -> {
            ItemsRecord itemsRecord = context.newRecord(ITEMS);
            itemsRecord.set(ITEMS.ONE, it.getOne());
            itemsRecord.set(ITEMS.ORDERID, it.getOrderid());
            itemsRecord.set(ITEMS.TWO, it.getTwo());
            itemsRecord.set(ITEMS.TREE, it.getTree());
            itemsRecord.set(ITEMS.FOUR, it.getFour());
            itemsRecord.set(ITEMS.FIVE, it.getFive());
            itemsRecord.set(ITEMS.SIX, it.getSix());
            itemsRecord.set(ITEMS.SEVEN, it.getSeven());
            itemsRecord.set(ITEMS.EIGHT, it.getEight());
            itemsRecord.set(ITEMS.NINE, it.getNine());
            itemsRecord.set(ITEMS.TEN, it.getTen());
            return itemsRecord;
        }).toList();
        context.batchInsert(list).execute();
    }

    public List<Items> findByOrderId(Long orderId) {
        return context.selectFrom(ITEMS)
                .where(ITEMS.ORDERID.eq(orderId)).fetchInto(Items.class);
    }
}
