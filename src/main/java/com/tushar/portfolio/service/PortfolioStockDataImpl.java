package com.tushar.portfolio.service;

import com.tushar.portfolio.model.PortfolioStock;
import com.tushar.portfolio.repository.PortfolioStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PortfolioStockDataImpl implements PortfolioStockDataService{

    @Autowired
    PortfolioStockRepository portfolioStockRepository;

    @Override
    public List<PortfolioStock> findAll() {
        return portfolioStockRepository.findAll();
    }
    @Override
    public PortfolioStock save(PortfolioStock portfolioStock) {
        portfolioStockRepository.save(portfolioStock);
        return portfolioStock;
    }
    @Override
    public PortfolioStock findById(Long id) {
        if(portfolioStockRepository.findById(id).isPresent()){
            return portfolioStockRepository.findById(id).get();
        }
        return null;
    }
    @Override
    public void delete(Long id) {
        PortfolioStock portfolioStock = findById(id);
        portfolioStockRepository.delete(portfolioStock);
    }
}
