package br.com.backendbrasil.urlshortener.helper.impl;

import java.util.Calendar;
import java.util.Date;

import br.com.backendbrasil.urlshortener.helper.SumDateHelper;

public class SumDateHelperImpl implements SumDateHelper {

	private static final long serialVersionUID = -7873885499197362769L;

	public Date sum(Date date, int typeOfSum, int value) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(typeOfSum, value);
		return calendar.getTime();
	}

}
