package com.example.database5.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shop", schema = "database5", catalog = "")
public class Shop {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "min_order_amount")
    private Integer minOrderAmount;

    @Basic
    @Column(name = "parent_company_id")
    private Integer parentCompanyId;

    @ManyToMany
    @JoinTable(name = "shop_goods", schema = "database5", joinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "goods_id", referencedColumnName = "id", nullable = false))
    private List<Goods> goodss;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinOrderAmount() {
        return minOrderAmount;
    }

    public void setMinOrderAmount(Integer minOrderAmount) {
        this.minOrderAmount = minOrderAmount;
    }

    public Integer getParentCompanyId() {
        return parentCompanyId;
    }

    public void setParentCompanyId(Integer parentCompanyId) {
        this.parentCompanyId = parentCompanyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop that = (Shop) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(minOrderAmount, that.minOrderAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, minOrderAmount);
    }

    public List<Goods> getGoodss() {
        return goodss;
    }

    public void setGoodss(List<Goods> goodss) {
        this.goodss = goodss;
    }
}
