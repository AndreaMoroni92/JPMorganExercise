package com.jpmorgan.validator;

import com.jpmorgan.bean.Stock;
import com.jpmorgan.exception.ValidatorException;

public class StockValidator extends AbstractValidator{

	private static final String STOCK_TYPE = "Stock type";
	private static final String STOCK_SYMBOL = "Stock symbol";
	private static final String STOCK_PAR = "Stock par";
	private static final String STOCK_FIXED_DIVIDEND = "Stock fixed dividend";
	private static final String STOCK_LAST_DIVIDEND = "Stock last dividend";
	
	
	private Stock stock;
	
	public StockValidator(Stock stock){
		this.stock=stock;
	}
	
	public void validate() throws ValidatorException{
		isNotNullObject(stock);
		mandatoryFieldsNotNull();
		isNotNegativeNumber(stock.getLastDividend(), STOCK_LAST_DIVIDEND);
		isNotNegativeNumber(stock.getFixedDividend(), STOCK_FIXED_DIVIDEND);
		isPositiveNumber(stock.getParValue(), STOCK_PAR);
	}
	
	private void mandatoryFieldsNotNull() throws ValidatorException{
		isNotNullField(stock.getSymbol(), STOCK_SYMBOL);
		isNotNullField(stock.getType(), STOCK_TYPE);
		isNotNullField(stock.getLastDividend(), STOCK_LAST_DIVIDEND);
		isNotNullField(stock.getParValue(), STOCK_PAR);
	}
}
