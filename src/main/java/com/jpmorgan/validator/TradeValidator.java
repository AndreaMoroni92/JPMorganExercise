package com.jpmorgan.validator;

import com.jpmorgan.bean.Trade;
import com.jpmorgan.exception.ValidatorException;

public class TradeValidator extends AbstractValidator{

	private static final String TRADE_PRICE = "Trade price";
	private static final String TRADE_QUANTITY = "Trade quantity";
	private static final String TRADE_TYPE = "Trade type";
	private static final String TRADE_DATE = "Trade date";
	private Trade trade;
	
	public TradeValidator(Trade trade){
		this.trade=trade;
	}
	
	public void validate() throws ValidatorException{
		isNotNullObject(trade);
		mandatoryFieldsNotNull();
		isPositiveNumber(trade.getQuantity(), TRADE_QUANTITY);
		isPositiveNumber(trade.getPrice(), TRADE_PRICE);
	}
	
	private void mandatoryFieldsNotNull() throws ValidatorException{
		isNotNullField(trade.getTime(), TRADE_DATE);
		isNotNullField(trade.getType(), TRADE_TYPE);
		isNotNullField(trade.getQuantity(), TRADE_QUANTITY);
		isNotNullField(trade.getPrice(), TRADE_PRICE);
	}
}
