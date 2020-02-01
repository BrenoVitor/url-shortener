package br.com.backendbrasil.urlshortener.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.model.Url;
import br.com.backendbrasil.urlshortener.respository.UrlRepository;
import br.com.backendbrasil.urlshortener.service.impl.ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceImpl;

public class ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceTest {

	@Test
	public void urlNotDuplicatedTest() {
		UrlRepository urlRepository = Mockito.mock(UrlRepository.class);
		Mockito.when(urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
				.thenReturn(Optional.empty());
		ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService validateUrlShortenerDuplicatedToUrlOriginalAndValidToService = new ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceImpl(
				urlRepository);
		UrlShortenedDto urlShortenedDto = new UrlShortenedDto("http://www.google.com.br");
		assertEquals(false, validateUrlShortenerDuplicatedToUrlOriginalAndValidToService.isDuplicated(urlShortenedDto));
	}

	@Test
	public void urlDuplicatedTest() {
		UrlRepository urlRepository = Mockito.mock(UrlRepository.class);
		Url url = new Url("http://www.google.com.br", "abcdef", new Date());
		Mockito.when(urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
				.thenReturn(Optional.of(url));
		ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService validateUrlShortenerDuplicatedToUrlOriginalAndValidToService = new ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceImpl(
				urlRepository);
		UrlShortenedDto urlShortenedDto = new UrlShortenedDto("adcsv");
		assertEquals(true, validateUrlShortenerDuplicatedToUrlOriginalAndValidToService.isDuplicated(urlShortenedDto));
	}

}
