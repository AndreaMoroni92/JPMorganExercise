package com.jpmorgan.bean;

import com.jpmorgan.bean.enums.StockSymbol;
import com.jpmorgan.bean.enums.StockType;

public class Stock extends AbstractBean{
	
	private StockSymbol symbol;
	private StockType type;
	private Double lastDividend;
	private Double fixedDividend;
	private Integer parValue;
	private Double price;
	
	public Stock(StockSymbol symbol, StockType type, Double lastDividend, Double fixedDividend, Integer parValue) {
		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
		this.price=new Double(0.0);
	}


	public StockSymbol getSymbol() {
		return symbol;
	}


	public StockType getType() {
		return type;
	}


	public Double getLastDividend() {
		return lastDividend;
	}


	public Double getFixedDividend() {
		return fixedDividend;
	}


	public Integer getParValue() {
		return parValue;
	}

	public void setType(StockType type) {
		this.type = type;
	}

	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}


	public void setParValue(Integer parValue) {
		this.parValue = parValue;
	}
	

	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stock [symbol=");
		builder.append(symbol);
		builder.append(", type=");
		builder.append(type);
		builder.append(", lastDividend=");
		builder.append(lastDividend);
		builder.append(", fixedDividend=");
		builder.append(fixedDividend);
		builder.append(", parValue=");
		builder.append(parValue);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}
	
}
