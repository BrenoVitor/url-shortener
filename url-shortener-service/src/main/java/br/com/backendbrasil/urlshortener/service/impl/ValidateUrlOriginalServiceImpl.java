package br.com.backendbrasil.urlshortener.service.impl;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.helper.UrlValidatorHelper;
import br.com.backendbrasil.urlshortener.service.ValidateUrlOriginalService;


public class ValidateUrlOriginalServiceImpl implements ValidateUrlOriginalService {

	private static final long serialVersionUID = 8485117276132720769L;
	
	private final UrlValidatorHelper urlValidatorHelper;

	public ValidateUrlOriginalServiceImpl(final UrlValidatorHelper urlValidatorHelper) {
		this.urlValidatorHelper = urlValidatorHelper;
	}

	public boolean isValid(UrlOriginalDto urlOriginalDto) {
		return urlValidatorHelper.validate(urlOriginalDto.getAddress());
	}

}
