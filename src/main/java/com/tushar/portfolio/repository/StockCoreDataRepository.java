package com.tushar.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tushar.portfolio.model.StockCoreDataEntity;

@Repository
public interface StockCoreDataRepository extends JpaRepository<StockCoreDataEntity, Long> {

}
