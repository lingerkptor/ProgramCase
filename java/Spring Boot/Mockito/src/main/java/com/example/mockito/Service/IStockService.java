package com.example.mockito.Service;

import com.example.mockito.Dao.Do.Stock;

import java.util.Map;

public interface IStockService {
    public double getPrice(Stock stock);

    public Map<Stock, Double> getStockPriceMap();
}