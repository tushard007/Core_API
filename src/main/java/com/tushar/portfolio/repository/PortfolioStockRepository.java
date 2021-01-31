package com.tushar.portfolio.repository;

import com.tushar.portfolio.model.PortfolioStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioStockRepository extends JpaRepository<PortfolioStock, Long> {

}
