package com.jpmorgan.validator;

import com.jpmorgan.exception.ValidatorException;
import com.jpmorgan.utils.Constants;

public abstract class AbstractValidator {
	
	public abstract void validate() throws ValidatorException;
	
	public void isNotNullObject(Object object) throws ValidatorException{
		if(object==null)
			throw new ValidatorException(Constants.NULL_OBJECT);
	}

	public void isNotNullField(Object object, String field) throws ValidatorException{
		if(object==null)
			throw new ValidatorException(field + Constants.NULL_FIELD);
	}
	
	public void isPositiveNumber(Double number, String field) throws ValidatorException{
		if(number!=null && number<=0.0)
			throw new ValidatorException(field + Constants.NOT_POSITIVE_NUMBER);
	}
	
	public void isPositiveNumber(Integer number, String field) throws ValidatorException{
		if(number!=null && number<=0)
			throw new ValidatorException(field + Constants.NOT_POSITIVE_NUMBER);
	}
	
	public void isNotNegativeNumber(Double number, String field) throws ValidatorException{
		if(number!=null && number<0.0)
			throw new ValidatorException(field + Constants.NEGATIVE_NUMBER);
	}
}
