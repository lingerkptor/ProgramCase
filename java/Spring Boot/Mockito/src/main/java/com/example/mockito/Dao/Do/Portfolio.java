package com.example.mockito.Dao.Do;

import com.example.mockito.Service.IStockService;

import java.util.List;

public class Portfolio {
    private IStockService stockService;
    private List<Stock> stocks;

    public Portfolio() {
    }

    public Portfolio(IStockService stockService) {
        this.stockService = stockService;
    }

    public IStockService getStockService() {
        return stockService;
    }

    public void setStockService(IStockService stockService) {
        this.stockService = stockService;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public double getMarketValue() {
        double marketValue = 0.0;

        for (Stock stock : stocks) {
            double price = stockService.getPrice(stock);
            marketValue += price * stock.getQuantity();
        }
        return marketValue;
    }
}
