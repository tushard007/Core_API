package com.tushar.portfolio.controller;


import com.tushar.portfolio.model.PortfolioStock;
import com.tushar.portfolio.service.PortfolioStockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class PortfolioStockController {

    @Autowired
    PortfolioStockDataService portfolioStockDataService;

    @GetMapping("/PortfolioStock")
    public ResponseEntity<List<PortfolioStock>> get() {
        List<PortfolioStock> objPortfolioStockData = portfolioStockDataService.findAll();
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
