package com.example.demo.datajdbc.repository;

import com.example.demo.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Orders, String> {

    @Query(value = "SELECT id FROM orders", nativeQuery = true)
    Set<OrderPK> findAllIds();

//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
    public static interface OrderPK {
        String getId();
    }
}
