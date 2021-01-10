package com.tushar.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tushar.portfolio.model.StockCoreDataEntity;
import com.tushar.portfolio.repository.StockCoreDataRepository;


@Service
public class StockCoreDataImpl implements StockCoreDataService {
	@Autowired
	StockCoreDataRepository stockCoreDataRepository;
	
	@Override
	public List<StockCoreDataEntity> findAll() {
		return stockCoreDataRepository.findAll();
	}
	@Override
	public StockCoreDataEntity save(StockCoreDataEntity stockCoreDataEntity) {
		stockCoreDataRepository.save(stockCoreDataEntity);
		return stockCoreDataEntity;
	}
	@Override
	public StockCoreDataEntity findById(Long id) {
		if(stockCoreDataRepository.findById(id).isPresent()){
			return stockCoreDataRepository.findById(id).get();
		}
		return null;
	}
	@Override
	public void delete(Long id) {
		StockCoreDataEntity stockCoreDataEntity = findById(id);
		stockCoreDataRepository.delete(stockCoreDataEntity);
	}
}
