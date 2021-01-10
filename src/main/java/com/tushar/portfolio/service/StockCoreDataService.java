package com.tushar.portfolio.service;

import java.util.List;

import com.tushar.portfolio.model.StockCoreDataEntity;

public interface StockCoreDataService {
	List<StockCoreDataEntity> findAll();
	StockCoreDataEntity save(StockCoreDataEntity stockCoreDataEntity);
	StockCoreDataEntity findById(Long id);
	void delete(Long id);
	

}
