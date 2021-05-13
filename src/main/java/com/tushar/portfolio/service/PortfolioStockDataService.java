package com.tushar.portfolio.service;

import com.tushar.portfolio.model.PortfolioStock;

import java.util.List;

public interface PortfolioStockDataService {
    List<PortfolioStock> findAll();
    PortfolioStock save(PortfolioStock portfolioStock);
//    PortfolioStock saveList(List<PortfolioStock> portfolioStock);

    PortfolioStock findById(Long id);
    void delete(Long id);

}
