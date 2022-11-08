package com.example.database5.repository;


import com.example.database5.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Integer> {

    @Procedure("CreateTablesWithCursor")
    void createTablesWithCursor();
}
