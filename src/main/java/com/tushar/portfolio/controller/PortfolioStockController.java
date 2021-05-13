package com.tushar.portfolio.controller;

import com.tushar.portfolio.model.PortfolioStock;
import com.tushar.portfolio.model.StockCoreDataEntity;
import com.tushar.portfolio.service.PortfolioStockDataService;
import com.tushar.portfolio.service.StockCoreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PortfolioStockController {

    @Autowired
    PortfolioStockDataService portfolioStockDataService;
    @Autowired
    StockCoreDataService stockCoreDataService;

    @GetMapping("/PortfolioStock")
    public ResponseEntity<List<PortfolioStock>> get() {
        List<StockCoreDataEntity> objStockData = stockCoreDataService.findAll();
        List<PortfolioStock> objPortfolioStockData = portfolioStockDataService.findAll();

        for (PortfolioStock prtData : objPortfolioStockData) {
            for (StockCoreDataEntity stk : objStockData) {
                if (stk.getId() == prtData.getStock_id()) {
                    prtData.setStockName(stk.getCompany_name());
                    prtData.setCurrent_price(stk.getCurrent_price());
                    prtData.setInvested_amount(prtData.getAvg_buy_price() * prtData.getAvailable_quantity());
                    System.out.println("portfolio stock:" + prtData.getStockName() + " " + prtData.getCurrent_price());
                }
            }
            PortfolioStock objPortfolioStockDataSave = portfolioStockDataService.save(prtData);
        }
        objPortfolioStockData.stream().map(PortfolioStock::getStockName).forEach(System.out::println);
        System.out.println("get method called");
        return new ResponseEntity<List<PortfolioStock>>(objPortfolioStockData, HttpStatus.OK);
    }

    @PostMapping("/PortfolioStock")
    public ResponseEntity<PortfolioStock> save(@RequestBody PortfolioStock stockCoreDataEntity) {
        PortfolioStock objPortfolioStockData = portfolioStockDataService.save(stockCoreDataEntity);
        return new ResponseEntity<PortfolioStock>(objPortfolioStockData, HttpStatus.OK);
    }

    @GetMapping("/PortfolioStock/{id}")
    public ResponseEntity<PortfolioStock> get(@PathVariable("id") Long id) {
        PortfolioStock objPortfolioStockData = portfolioStockDataService.findById(id);
        return new ResponseEntity<PortfolioStock>(objPortfolioStockData, HttpStatus.OK);
    }

    @DeleteMapping("/PortfolioStock/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        portfolioStockDataService.delete(id);
        return new ResponseEntity<String>("Stock entry is deleted successfully.!", HttpStatus.OK);
    }
}
