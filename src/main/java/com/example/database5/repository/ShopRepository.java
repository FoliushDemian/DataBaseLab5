
package com.example.database5.repository;

import com.example.database5.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer>{

    @Query(value = "CALL CalcAverageMinOrderAmount();", nativeQuery = true)
    Integer getAverageMinOrderAmount();

    @Procedure("AddShopGoodsRelationship")
    void addShopGoodsRelationship(String shopName, String goodsName);

    @Procedure("ShopParamInsert")
    Shop addShopWithProcedure(String name, Integer minOrderAmount, Integer parentCompanyId);
}
