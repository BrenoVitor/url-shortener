package br.com.backendbrasil.urlshortener.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.backendbrasil.urlshortener.dto.UrlShortenedDto;
import br.com.backendbrasil.urlshortener.model.Url;
import br.com.backendbrasil.urlshortener.respository.UrlRepository;
import br.com.backendbrasil.urlshortener.service.impl.ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceImpl;

public class ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceTest {
	
	private UrlRepository urlRepository;
	private Url url;
	private UrlShortenedDto urlShortenedDto;

	@BeforeEach
	public void loadUrlRepository() {
		this.urlRepository = Mockito.mock(UrlRepository.class);
	}
	
	@BeforeEach
	public void loadUrl() {
		this.url = new Url("http://www.google.com.br", "abcdef", new Date());
	}
	
	@BeforeEach
	public void loadUrlShortenedDto() {
		 this.urlShortenedDto = new UrlShortenedDto("adcsv");
	}

	@Test
	public void urlNotDuplicatedTest() {
		when(this.urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
				.thenReturn(Optional.empty());
		ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService validateUrlShortenerDuplicatedToUrlOriginalAndValidToService = new ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceImpl(
				this.urlRepository);
		assertEquals(false, validateUrlShortenerDuplicatedToUrlOriginalAndValidToService.isDuplicated(urlShortenedDto));
	}

	@Test
	public void urlDuplicatedTest() {
		UrlRepository urlRepository = Mockito.mock(UrlRepository.class);
		when(urlRepository.findByShortAddressAndValidToAfter(any(String.class), any(Date.class)))
				.thenReturn(Optional.of(this.url));
		ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToService validateUrlShortenerDuplicatedToUrlOriginalAndValidToService = new ValidateUrlShortenerDuplicatedToUrlOriginalAndValidToServiceImpl(
				urlRepository);
		assertEquals(true, validateUrlShortenerDuplicatedToUrlOriginalAndValidToService.isDuplicated(urlShortenedDto));
	}

}
