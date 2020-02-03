package br.com.backendbrasil.urlshortener.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.backendbrasil.urlshortener.dto.UrlOriginalDto;
import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.exception.GenerateUrlShortenedDuplicatedException;
import br.com.backendbrasil.urlshortener.facade.LoggerFacade;
import br.com.backendbrasil.urlshortener.helper.GeneratorRandomAlphanumericStringHelper;
import br.com.backendbrasil.urlshortener.helper.impl.GeneratorRandomAlphanumericStringHelperImpl;
import br.com.backendbrasil.urlshortener.service.impl.UrlShortenerServiceImpl;

public class UrlShortenerServiceTest {

	private LoggerFacade logger;
	private UrlOriginalDto urlOriginalDto;
	private ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService validateUrlShortenerDuplicatedToUrlOriginalAndValidToService;

	@BeforeEach
	public void loadLogger() {
		this.logger = mock(LoggerFacade.class);
	}

	@BeforeEach
	public void loadUrlOriginalDto() {
		this.urlOriginalDto = new UrlOriginalDto("http://www.google.com.br");
	}

	@BeforeEach
	public void loadValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService() {
		this.validateUrlShortenerDuplicatedToUrlOriginalAndValidToService = Mockito
				.mock(ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService.class);

	}

	@Test
	public void urlShortenerServiceTest() throws GenerateUrlShortenedDuplicatedException {
//		UrlRepository urlRepository = Mockito.mock(UrlRepository.class);
//		Mockito.when(urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
//				.thenReturn(Optional.empty());
//		ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService validateUrlShortenerDuplicatedToUrlOriginalAndValidToService = new ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceImpl(
//				urlRepository);
		when(validateUrlShortenerDuplicatedToUrlOriginalAndValidToService.isDuplicated(any(UrlShortenedDto.class)))
				.thenReturn(false);
		GeneratorRandomAlphanumericStringHelper generatorRandomAlphanumericStringHelper = new GeneratorRandomAlphanumericStringHelperImpl();
		UrlShortenerService urlShortenerService = new UrlShortenerServiceImpl(5, 10,
				generatorRandomAlphanumericStringHelper, logger,
				validateUrlShortenerDuplicatedToUrlOriginalAndValidToService, 3);
		UrlShortenedDto urlShortenedDto = urlShortenerService.shorten(urlOriginalDto);

		assertEquals(true, urlShortenedDto.getShortenedAddress().length() > 5);
		assertEquals(true, urlShortenedDto.getShortenedAddress().length() < 10);

	}

	@Test
	public void urlShortenerServiceDuplicatedUrlExceptionTest() throws GenerateUrlShortenedDuplicatedException {
//		UrlRepository urlRepository = Mockito.mock(UrlRepository.class);
//		Mockito.when(urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
//				.thenReturn(Optional.empty());
//		ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService validateUrlShortenerDuplicatedToUrlOriginalAndValidToService = new ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceImpl(
//				urlRepository);
		when(validateUrlShortenerDuplicatedToUrlOriginalAndValidToService.isDuplicated(any(UrlShortenedDto.class)))
				.thenReturn(true);
		GeneratorRandomAlphanumericStringHelper generatorRandomAlphanumericStringHelper = new GeneratorRandomAlphanumericStringHelperImpl();
		UrlShortenerService urlShortenerService = new UrlShortenerServiceImpl(5, 10,
				generatorRandomAlphanumericStringHelper, logger,
				validateUrlShortenerDuplicatedToUrlOriginalAndValidToService, 3);

		assertThrows(GenerateUrlShortenedDuplicatedException.class, () -> {
			urlShortenerService.shorten(urlOriginalDto);
		});
		verify(validateUrlShortenerDuplicatedToUrlOriginalAndValidToService, times(3))
				.isDuplicated(any(UrlShortenedDto.class));
	}

}
