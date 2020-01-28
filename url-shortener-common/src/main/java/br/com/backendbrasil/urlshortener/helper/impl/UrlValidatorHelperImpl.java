package br.com.backendbrasil.urlshortener.helper.impl;

import org.apache.commons.validator.routines.UrlValidator;

import br.com.backendbrasil.urlshortener.helper.UrlValidatorHelper;

public class UrlValidatorHelperImpl implements UrlValidatorHelper {

	private static final long serialVersionUID = 1225316733997335025L;

	public boolean validate(String url) {
		UrlValidator urlValidator = new UrlValidator();
		return urlValidator.isValid(url);
	}

}
