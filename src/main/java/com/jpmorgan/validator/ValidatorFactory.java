package com.jpmorgan.validator;

import com.jpmorgan.bean.AbstractBean;
import com.jpmorgan.bean.Stock;
import com.jpmorgan.bean.Trade;
import com.jpmorgan.exception.ValidatorException;
import com.jpmorgan.utils.Constants;

public class ValidatorFactory {

	public static AbstractValidator getValidator(AbstractBean bean) throws ValidatorException{
		if(bean instanceof Stock)
			return new StockValidator((Stock) bean);
		else if(bean instanceof Trade)
			return new TradeValidator((Trade) bean);
		else
			throw new ValidatorException(Constants.NO_VALIDATOR);
	}
}
