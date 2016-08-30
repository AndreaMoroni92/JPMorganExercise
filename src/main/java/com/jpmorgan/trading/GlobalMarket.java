package com.jpmorgan.trading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jpmorgan.bean.Stock;
import com.jpmorgan.bean.Trade;
import com.jpmorgan.bean.enums.StockSymbol;
import com.jpmorgan.bean.enums.StockType;
import com.jpmorgan.exception.BusinessLogicException;
import com.jpmorgan.utils.Constants;

public class GlobalMarket {
	
	private static GlobalMarket instance = null;
	private HashMap<StockSymbol, Stock> stocksDB;
	private HashMap<StockSymbol, List<Trade>> tradesDB;
	
	private GlobalMarket(){
		stocksDB=new HashMap<StockSymbol, Stock>();
		populateStocksDB();
		tradesDB=new HashMap<StockSymbol, List<Trade>>();
		populateTradesDB();
	}

	public static GlobalMarket getInstance(){
		if(instance==null)
			return new GlobalMarket();
		return instance;
	}
	
	private void populateStocksDB() {
		insertStockInsideDB(new Stock(StockSymbol.TEA, StockType.COMMON, new Double(0.0),
												new Double(0.0), new Integer(100)));
		insertStockInsideDB(new Stock(StockSymbol.POP, StockType.COMMON, new Double(8.0),
				new Double(0.0), new Integer(100)));
		insertStockInsideDB(new Stock(StockSymbol.ALE, StockType.COMMON, new Double(23.0),
				new Double(0.0), new Integer(60)));
		insertStockInsideDB(new Stock(StockSymbol.GIN, StockType.PREFERRED, new Double(8.0),
				new Double(0.02), new Integer(100)));
		insertStockInsideDB(new Stock(StockSymbol.JOE, StockType.COMMON, new Double(13.0),
				new Double(0.0), new Integer(250)));
	}
	
	private void populateTradesDB() {
		tradesDB.put(StockSymbol.TEA, new ArrayList<Trade>());
		tradesDB.put(StockSymbol.POP, new ArrayList<Trade>());
		tradesDB.put(StockSymbol.ALE, new ArrayList<Trade>());
		tradesDB.put(StockSymbol.GIN, new ArrayList<Trade>());
		tradesDB.put(StockSymbol.JOE, new ArrayList<Trade>());
	}
	
	private void insertStockInsideDB(Stock stock){
		stocksDB.put(stock.getSymbol(), stock);
	}

	public HashMap<StockSymbol, Stock> getStocksDB(){
		return stocksDB;
	}

	public HashMap<StockSymbol, List<Trade>> getTradesDB(){
		return tradesDB;
	}
	
	public Double calculateDividendYield(Stock stock,Double tickerPrice) throws BusinessLogicException,IllegalArgumentException{
		if(tickerPrice<=0)
			throw new IllegalArgumentException(Constants.NEGATIVE_PRICE);
		switch (stock.getType()) {
			case COMMON: return stock.getLastDividend()/tickerPrice;
			case PREFERRED: return stock.getFixedDividend()*stock.getParValue()/tickerPrice;
			default: throw new BusinessLogicException(Constants.DIVIDEND_YIELD_MSG);
		}
	}
	
	public void recordTrade(Stock stock,Trade trade) throws BusinessLogicException{
		if(!tradesDB.get(stock.getSymbol()).add(trade))
			throw new BusinessLogicException(Constants.TRADE_NOT_SAVED);
	}
	
	public Double updateStockPrice(Stock stock, Integer minutes) throws BusinessLogicException,IllegalArgumentException{
		if(minutes<0)
			throw new IllegalArgumentException(Constants.NEGATIVE_TIME);
		Long time = System.currentTimeMillis();
		Double totalPrice = new Double(0.0);
		Integer totalQuantity = new Integer(0);
		for(Trade trade : tradesDB.get(stock.getSymbol())){
			if( time-trade.getTime().getTime() <= minutes*60*1000 ){
				totalPrice+=trade.getPrice()*trade.getQuantity();
				totalQuantity+=trade.getQuantity();
			}
		}
		if(totalQuantity==0)
			throw new BusinessLogicException(Constants.STOCK_PRICE_MSG);
		stocksDB.get(stock.getSymbol()).setPrice(totalPrice/totalQuantity);
		return totalPrice/totalQuantity;
	}

	public Double calculatePERatio(Stock stock,Double tickerPrice) throws IllegalArgumentException{
		if(tickerPrice<0)
			throw new IllegalArgumentException(Constants.NEGATIVE_PRICE);
		if(stock.getLastDividend()==0.0)
			throw new IllegalArgumentException(Constants.DIVISION_BY_ZERO);
		return tickerPrice/stock.getLastDividend();	
	}
	
	public Double calculateGBCEAllShareIndex() throws BusinessLogicException{
		Double priceProduct = new Double(1.0);
		for(Stock stock : stocksDB.values()){
			if(stock.getPrice()==0.0)
				throw new BusinessLogicException(Constants.GBCE_ALL_SHARE_INDEX_MSG);
			priceProduct *= stock.getPrice();
		}
		return Math.pow(priceProduct, 1.0/stocksDB.values().size());
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GlobalMarket [stocksDB=");
		builder.append(stocksDB);
		builder.append(", tradesDB=");
		builder.append(tradesDB);
		builder.append("]");
		return builder.toString();
	}

}
