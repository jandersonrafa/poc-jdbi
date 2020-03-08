package com.example.demo.repository;

import com.example.demo.model.Items;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Items, String > {

    @Query("select * from Items i where i.orderid = :orderid")
    List<Items> findByOrderid(@Param("orderid") String orderid);

    @Modifying
    @Transactional
    @Query("delete from Items u where u.orderid = :orderid")
    void deleteByOrderid(@Param("orderid") String orderid);

}
