package br.com.backendbrasil.urlshortener.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.helper.UrlValidatorHelper;
import br.com.backendbrasil.urlshortener.helper.impl.UrlValidatorHelperImpl;
import br.com.backendbrasil.urlshortener.service.impl.ValidateUrlOriginalServiceImpl;

public class ValidateUrlOriginalServiceTest {

	@Test
	public void validateCorrectHttpUrl() {
		UrlValidatorHelper urlValidatorHelper = new UrlValidatorHelperImpl();
		ValidateUrlOriginalService validateUrlOriginalService = new ValidateUrlOriginalServiceImpl(urlValidatorHelper);
		UrlOriginalDto urlOriginalDto = new UrlOriginalDto("http://www.google.com.br");
		assertEquals(true, validateUrlOriginalService.isValid(urlOriginalDto));
	}
	
	@Test
	public void validateIncorrectUrl() {
		UrlValidatorHelper urlValidatorHelper = new UrlValidatorHelperImpl();
		ValidateUrlOriginalService validateUrlOriginalService = new ValidateUrlOriginalServiceImpl(urlValidatorHelper);
		UrlOriginalDto urlOriginalDto = new UrlOriginalDto("fefefwww.google.com.br");
		assertEquals(false, validateUrlOriginalService.isValid(urlOriginalDto));
	}
	
	@Test
	public void validateCorrectHttpsUrl() {
		UrlValidatorHelper urlValidatorHelper = new UrlValidatorHelperImpl();
		ValidateUrlOriginalService validateUrlOriginalService = new ValidateUrlOriginalServiceImpl(urlValidatorHelper);
		UrlOriginalDto urlOriginalDto = new UrlOriginalDto("https://www.google.com.br");
		assertEquals(true, validateUrlOriginalService.isValid(urlOriginalDto));
	}

}
