package com.tushar.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.portfolio.model.StockCoreDataEntity;
import com.tushar.portfolio.service.StockCoreDataService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class StockCoreDataController {
	
	@Autowired
	StockCoreDataService stockCoreDataService;
	
	@GetMapping("/StockCoreData")
	public ResponseEntity<List<StockCoreDataEntity>> get() {
		List<StockCoreDataEntity> objStockData = stockCoreDataService.findAll();
		return new ResponseEntity<List<StockCoreDataEntity>>(objStockData, HttpStatus.OK);
	}
	@PostMapping("/StockCoreData")
	public ResponseEntity<StockCoreDataEntity> save(@RequestBody StockCoreDataEntity stockCoreDataEntity) {
		StockCoreDataEntity objStockData = stockCoreDataService.save(stockCoreDataEntity);
		return new ResponseEntity<StockCoreDataEntity>(objStockData, HttpStatus.OK);
	}
	@GetMapping("/StockCoreData/{id}")
	public ResponseEntity<StockCoreDataEntity> get(@PathVariable("id") Long id) {
		StockCoreDataEntity objStockData = stockCoreDataService.findById(id);
		return new ResponseEntity<StockCoreDataEntity>(objStockData, HttpStatus.OK);
	}
	@DeleteMapping("/StockCoreData/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		stockCoreDataService.delete(id);
		return new ResponseEntity<String>("Stock entry is deleted successfully.!", HttpStatus.OK);
	}

}
