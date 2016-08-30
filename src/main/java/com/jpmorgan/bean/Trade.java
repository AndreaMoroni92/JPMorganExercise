package com.jpmorgan.bean;

import java.util.Date;

import com.jpmorgan.bean.enums.TradeType;

public class Trade extends AbstractBean{
	
	private Date time;
	private Integer quantity;
	private TradeType type;
	private Double price;
	
	
	public Trade(Date time, Integer quantity, TradeType type, Double price) {
		super();
		this.time = time;
		this.quantity = quantity;
		this.type = type;
		this.price = price;
	}


	public Date getTime() {
		return time;
	}

	public Integer getQuantity() {
		return quantity;
	}


	public TradeType getType() {
		return type;
	}


	public Double getPrice() {
		return price;
	}

	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}

	public void setType(TradeType type){
		this.type = type;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Trade [time=");
		builder.append(time);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", type=");
		builder.append(type);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

}
