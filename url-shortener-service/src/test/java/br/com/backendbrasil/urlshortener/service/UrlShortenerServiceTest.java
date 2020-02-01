package br.com.backendbrasil.urlshortener.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.exception.GenerateUrlShortenedDuplicatedException;
import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.facade.impl.LoggerFacadeImpl;
import br.com.backendbrasil.urlshortener.helper.GeneratorRandomAlphanumericStringHelper;
import br.com.backendbrasil.urlshortener.helper.impl.GeneratorRandomAlphanumericStringHelperImpl;
import br.com.backendbrasil.urlshortener.service.impl.UrlShortenerServiceImpl;

public class UrlShortenerServiceTest {

	@Test
	public void urlShortenerServiceTest() throws GenerateUrlShortenedDuplicatedException {
		LoggerFacade LOGGER = new LoggerFacadeImpl();
//		UrlRepository urlRepository = Mockito.mock(UrlRepository.class);
		UrlOriginalDto urlOriginalDto = new UrlOriginalDto("http://www.google.com.br");
//		Mockito.when(urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
//				.thenReturn(Optional.empty());
//		ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService validateUrlShortenerDuplicatedToUrlOriginalAndValidToService = new ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceImpl(
//				urlRepository);
		ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService validateUrlShortenerDuplicatedToUrlOriginalAndValidToService = Mockito.mock(ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService.class);
		Mockito.when(validateUrlShortenerDuplicatedToUrlOriginalAndValidToService.isDuplicated(any(UrlShortenedDto.class))).thenReturn(false);
		GeneratorRandomAlphanumericStringHelper generatorRandomAlphanumericStringHelper = new GeneratorRandomAlphanumericStringHelperImpl();
		UrlShortenerService urlShortenerService = new UrlShortenerServiceImpl(5, 10,
				generatorRandomAlphanumericStringHelper, LOGGER,
				validateUrlShortenerDuplicatedToUrlOriginalAndValidToService, 3);
		UrlShortenedDto urlShortenedDto = urlShortenerService.shorten(urlOriginalDto);

		assertEquals(true, urlShortenedDto.getShortenedAddress().length() > 5);
		assertEquals(true, urlShortenedDto.getShortenedAddress().length() < 10);

	}

}
