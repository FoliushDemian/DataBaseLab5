package com.example.database5.repository;

import com.example.database5.domain.ParentCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentCompanyRepository extends JpaRepository<ParentCompany, Integer> {
}
