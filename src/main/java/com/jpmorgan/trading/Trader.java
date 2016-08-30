package com.jpmorgan.trading;

import java.util.Date;

import com.jpmorgan.bean.Stock;
import com.jpmorgan.bean.Trade;
import com.jpmorgan.bean.enums.TradeType;
import com.jpmorgan.exception.BusinessLogicException;
import com.jpmorgan.exception.ValidatorException;
import com.jpmorgan.validator.ValidatorFactory;

public class Trader {
	
	public void buy(Stock stock,Integer quantity,Double tradePrice) throws BusinessLogicException, ValidatorException{
		Trade trade = new Trade(new Date(System.currentTimeMillis()), quantity, TradeType.BUY, tradePrice*quantity);
		ValidatorFactory.getValidator(trade).validate();
		GlobalMarket.getInstance().recordTrade(stock,trade);
	}
	public void sell(Stock stock,Integer quantity,Double tradePrice) throws BusinessLogicException, ValidatorException{
		Trade trade = new Trade(new Date(System.currentTimeMillis()), quantity, TradeType.BUY, tradePrice*quantity);
		ValidatorFactory.getValidator(trade).validate();
		GlobalMarket.getInstance().recordTrade(stock,trade);
	}

}
