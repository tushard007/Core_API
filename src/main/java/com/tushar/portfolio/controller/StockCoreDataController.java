package com.tushar.portfolio.controller;

import com.tushar.portfolio.model.StockCoreDataEntity;
import com.tushar.portfolio.service.StockCoreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class StockCoreDataController {
	
	@Autowired
	StockCoreDataService stockCoreDataService;

	
	@GetMapping("/StockCoreData")
	@Cacheable("StockCoreDataEntity")
	public ResponseEntity<List<StockCoreDataEntity>> get() throws IOException {

		List<StockCoreDataEntity> objStockData = stockCoreDataService.findAll();

		objStockData.forEach(e->{

			try {
				if(e.getId()>1083) {
					String nseCode = e.getNse_code();
					Stock stock = YahooFinance.get(nseCode + ".NS");

					if (stock.isValid()) {
						BigDecimal price = stock.getQuote().getPrice();
						BigDecimal marketCap;
						Integer divide = 10000000;
						marketCap = stock.getStats().getMarketCap().divideToIntegralValue(BigDecimal.valueOf(divide));
						BigDecimal dividend = stock.getDividend().getAnnualYield();
						if (!(nseCode.isEmpty() && nseCode.equals(""))) {
							e.setCurrent_price(price);
							e.setMarketCap_in_CR(marketCap);
							e.setDividend(dividend);
							stockCoreDataService.save(e);
							System.out.println("Company: " + e.getCompany_name());
							System.out.println("Current price: " + e.getCurrent_price());
							System.out.println("Dividend: " + e.getDividend());

						}
					}
				}
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		});

		return new ResponseEntity<List<StockCoreDataEntity>>(objStockData, HttpStatus.OK);
	}

	@PostMapping("/StockCoreData")
	public ResponseEntity<StockCoreDataEntity> save(@RequestBody StockCoreDataEntity stockCoreDataEntity) {
		StockCoreDataEntity objStockData = stockCoreDataService.save(stockCoreDataEntity);
		return new ResponseEntity<StockCoreDataEntity>(objStockData, HttpStatus.OK);
	}
	@GetMapping("/updateQuote")
	public List<StockCoreDataEntity> updatePrice() throws IOException {
		List<StockCoreDataEntity> lstStockCore= stockCoreDataService.findAll();
		System.out.println("Inside stock quote");
		lstStockCore.forEach(e->{
			try {
				String nseCode=e.getNse_code();
				if(!(nseCode.isEmpty()&&nseCode.equals(""))){
			BigDecimal stockPrice=	price(nseCode);
			e.setCurrent_price(stockPrice);}
			System.out.println(e.getCompany_name());
				stockCoreDataService.save(e);
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		});
		lstStockCore.forEach(System.out::println);
		for (StockCoreDataEntity lst:lstStockCore) {
			System.out.println(lst.getCompany_name());
		lst.setCurrent_price(price(lst.getNse_code())) ;
		}
		return lstStockCore;
	}
public BigDecimal price(String NSE_ID) throws IOException {
	Stock stock = YahooFinance.get(NSE_ID+".NS");
	System.out.println(NSE_ID);
if(	stock.isValid()) {
	BigDecimal price = stock.getQuote().getPrice();
	if (!price.equals(""))
		System.out.println(price);
	System.out.println("MarketCap:"+stock.getStats().getMarketCap());
	System.out.println("Dividend:"+stock.getDividend().getAnnualYield());

	return price;}
else
	return BigDecimal.ZERO;
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
