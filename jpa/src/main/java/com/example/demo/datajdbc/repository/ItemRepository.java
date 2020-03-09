package com.example.demo.datajdbc.repository;

import com.example.demo.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Items, String > {

    List<Items> findByOrderid(String id);

    int deleteByOrderid(String id);

}
